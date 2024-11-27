package com.contentfarm.core.repository.appuser;

import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import io.r2dbc.spi.Parameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class AppUserRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(AppUserRepositoryTest.class);

    private String id;
    private Integer count;

    @Autowired
    AppUserRepository appUserRepository;

    @Order(1)
    @Test
    void count() {
        appUserRepository.count()
                .doOnNext(count -> this.count = Integer.valueOf(String.valueOf(count)))
                .as(StepVerifier::create)
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    void getAppUserDOByUsername() {
        appUserRepository.findAll()
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    public void findById() {
        appUserRepository.findById("testing_id")
                .doOnNext(c -> logger.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("testing_username", c.getUsername().trim()))
                .expectComplete()
                .verify();
    }

    @Test
    public void insertAndDeleteCustomer() {
        // insert
        var appUser = new AppUserDO();
        appUser.setUsername("marshal");
        appUser.setUsername("marshal@gmail.com");
        appUserRepository.save(appUser)
                .doOnNext(c -> {
                    logger.info("{}", c);
                    this.id = c.getId();
                })
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .expectComplete()
                .verify();
        // delete
        appUserRepository.deleteById(this.id)
                .then(appUserRepository.count())
                .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();
    }

    /*
    @Test
    public void updateCustomer() {
        this.repository.findByName("ethan")
                .doOnNext(c -> c.setName("noel")) // It is for mutating!
                .flatMap(c -> this.repository.save(c))
                .doOnNext(c -> log.info("{}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("noel", c.getName()))
                .expectComplete()
                .verify();
    }

     */
}