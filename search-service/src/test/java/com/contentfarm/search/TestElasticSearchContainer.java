package com.contentfarm.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

@Testcontainers
public class TestElasticSearchContainer {

    private final Logger logger = LoggerFactory.getLogger(TestElasticSearchContainer.class);

    @ServiceConnection
    @Container //
    public static ElasticsearchContainer container = new ElasticsearchContainer(
            DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.17.28"))
            //.withPassword("foobar")
            .withReuse(false)
            .withExposedPorts(9200)
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("/testcontainer/init.sh", 744),
                    "/init.sh"
            );

    /*
    @Configuration
    class TestConfiguration extends ElasticsearchConfiguration {
        @Override
        public ClientConfiguration clientConfiguration() {

            Assert.notNull(container, "TestContainer is not initialized!");

            logger.info("TestContainer initialized!");

            try {
                container.copyFileToContainer(
                        MountableFile.forClasspathResource("/testcontainer/init.sh", 744),
                        "/scripts/init.sh"
                );
                var result = container.execInContainer("ls");
                logger.info(result.getStdout());
                var result2 = container.execInContainer("sh", "/scripts/init.sh", container.getHost() + ":" + container.getMappedPort(9200));
                logger.info(result2.getStdout());
                logger.info(String.valueOf(result2.getExitCode()));
                logger.info(result2.getStdout());
                logger.info(result2.toString());
            } catch (IOException | InterruptedException e) {
                logger.error(e.getMessage());
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
