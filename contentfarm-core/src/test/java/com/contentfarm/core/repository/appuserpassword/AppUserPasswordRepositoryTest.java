package com.contentfarm.core.repository.appuserpassword;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AppUserPasswordRepositoryTest {
    @Autowired
    private AppUserPasswordRepository appUserPasswordRepository;

    @Test
    void testGetAppUserPasswordDOByAppUserIdAndIsValid() {
        String appUserId = "testing_id";
        Boolean isValid = true;
        appUserPasswordRepository.getAppUserPasswordDOByAppUserId(appUserId)
                .doOnNext(System.out::println)
                .as(StepVerifier::create)
                .expectNextCount(1L)
                .expectComplete()
                .verify();
    }

}