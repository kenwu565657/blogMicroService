package com.contentfarm.search.service.blogpost.impl;

import com.contentfarm.search.SearchServiceApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Service;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.Assert;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.images.builder.Transferable;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.io.IOException;
import java.text.MessageFormat;

//@Testcontainers
@SpringBootTest(classes = {
        SearchServiceApplication.class,
        //BlogPostSearchServiceTest.TestConfiguration.class
}
)
class BlogPostSearchServiceTest {

    protected static final Logger log = LoggerFactory.getLogger(BlogPostSearchServiceTest.class);

    @Autowired BlogPostSearchService blogPostSearchService;

    @Test
    void searchBlogPostByKeyword() {
        var result = blogPostSearchService.searchBlogPostByKeyword("testing");
        Assertions.assertEquals(1, result.getSearchHits().size());
        log.info(() -> MessageFormat.format("The First Reslut: {0}", result.getSearchHits().getFirst().getContent().toString()));
        log.info(() -> MessageFormat.format("Total Search Hits: {0}", result.getTotalHits()));
        log.info(() -> MessageFormat.format("SearchShardStatistics: {0}", result.getSearchShardStatistics()));
        log.info(() -> MessageFormat.format("ExecutionDuration: {0}", result.getExecutionDuration()));
        log.info(() -> MessageFormat.format("MaxScore: {0}", result.getMaxScore()));
        log.info(() -> MessageFormat.format("PointInTimeId: {0}", result.getPointInTimeId()));
        log.info(() -> MessageFormat.format("TotalHitsRelation: {0}", result.getTotalHitsRelation()));
    }

    /*
    @ServiceConnection
    @Container //
    private static final ElasticsearchContainer container = new ElasticsearchContainer(
            DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.16.2")) //
            .withPassword("foobar") //
            .withReuse(false)
            .withExposedPorts(9200)
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("/testcontainer/init.sh", 744),
                    "/init.sh"
            );

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        //registry.add("spring.redis.host", () -> "localhost");
        //registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
    }

    @Configuration
    static class TestConfiguration extends ElasticsearchConfiguration {
        @Override
        public ClientConfiguration clientConfiguration() {

            Assert.notNull(container, "TestContainer is not initialized!");

            log.info(() -> "TestContainer initialized!");

            try {
                container.copyFileToContainer(
                        MountableFile.forClasspathResource("/testcontainer/init.sh", 744),
                        "/scripts/init.sh"
                );
                var result = container.execInContainer("ls");
                log.info(result::getStdout);
                var result2 = container.execInContainer("sh", "/scripts/init.sh", container.getHost() + ":" + container.getMappedPort(9200));
                log.info(result2::getStdout);
                log.info(() -> String.valueOf(result2.getExitCode()));
                log.info(result2::getStderr);
                log.info(result2::toString);
            } catch (IOException | InterruptedException e) {
                log.error(e::getMessage);
            }


            return ClientConfiguration.builder() //
                    .connectedTo(container.getHost() + ":" + container.getMappedPort(9200)) //
                    //.usingSsl(container.createSslContextFromCa()) //
                    .withBasicAuth("elastic", "foobar") //
                    .build();
        }
    }

     */
}