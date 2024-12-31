package com.contentfarm.core.service.account;

import com.contentfarm.adapter.in.web.command.account.LoginCommand;
import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import com.contentfarm.adapter.out.persistence.entity.appuserpassword.AppUserPasswordDO;
import com.contentfarm.core.repository.appuser.AppUserReactiveDao;
import com.contentfarm.core.repository.appuser.AppUserRepository;
import com.contentfarm.core.repository.appuserpassword.AppUserPasswordRepository;
import com.contentfarm.domain.appuser.AppUserDomainModel;
import com.contentfarm.domain.appuser.AppUserStatus;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.Closeable;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private AppUserPasswordRepository appUserPasswordRepository;

    private AutoCloseable autoCloseable;

    private final String TESTING_CORRECT_USERNAME = "testingCorrectUsername";
    private final String TESTING_INCORRECT_USERNAME = "testingInCorrectUsername";
    private final String TESTING_CORRECT_USER_ID = "testingCorrectUserId";

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        when(appUserRepository.getAppUserDOByUsername(anyString()))
                .thenReturn(Mono.fromCallable(() -> createTestingAppUserDO(TESTING_CORRECT_USERNAME)));

        when(appUserRepository.getAppUserDOByUsername(TESTING_CORRECT_USERNAME))
                .thenReturn(Mono.fromCallable(() -> createTestingAppUserDO(TESTING_CORRECT_USERNAME)));

        when(appUserRepository.getAppUserDOByUsername(anyString()))
                .thenReturn(Mono.fromCallable(() -> createTestingAppUserDO(TESTING_CORRECT_USERNAME)));

        when(appUserPasswordRepository.getAppUserPasswordDOByAppUserId(anyString()))
                .thenReturn(Mono.fromCallable(this::createTestingAppUserPasswordDO));

        when(appUserPasswordRepository.getAppUserPasswordDOByAppUserId(TESTING_CORRECT_USER_ID))
                .thenReturn(Mono.fromCallable(this::createTestingAppUserPasswordDO));

        when(appUserPasswordRepository.getAppUserPasswordDOByAppUserId(anyString()))
                .thenReturn(Mono.fromCallable(this::createTestingAppUserPasswordDO));
    }

    @AfterEach
    void close() throws Exception {
        autoCloseable.close();
    }

    @Nested
    class testLogin {

        @DisplayName("Happy Case Can Login")
        @Test
        void testLoginHappyCase() {
            var loginCommand = new LoginCommand();
            loginCommand.setUsername(TESTING_CORRECT_USERNAME);
            loginCommand.setPassword("password");
            loginService.login(loginCommand)
                    //.doOnNext(appUserDomainModel -> log.info(appUserDomainModel.toString()))
                    .as(StepVerifier::create)
                    //.expectNextCount(1L)
                    .assertNext(x -> assertEquals("testing_username", x.getUsername()))
                    .verifyComplete();
        }

        @DisplayName("UnHappy Case Cannot Login")
        @Test
        void testLoginUnhappyCase() {
            var loginCommand = new LoginCommand();
            loginCommand.setUsername(TESTING_INCORRECT_USERNAME);
            loginCommand.setPassword("password");
            Mono<AppUserDomainModel> testingMono = loginService.login(loginCommand);

            StepVerifier
                    .create(testingMono)
                    .expectError()
                    .verify();
        }
    }

    private AppUserDO createTestingAppUserDO(String username) {
        AppUserDO appUserDO = new AppUserDO();
        appUserDO.setId(TESTING_CORRECT_USER_ID);
        appUserDO.setUsername(username);
        appUserDO.setEmail(username);
        appUserDO.setStatus(AppUserStatus.ACTIVE);
        return appUserDO;
    }

    private AppUserPasswordDO createTestingAppUserPasswordDO() {
        AppUserPasswordDO appUserPasswordDO = new AppUserPasswordDO();
        appUserPasswordDO.setId(UUID.randomUUID().toString());
        appUserPasswordDO.setAppUserId(TESTING_CORRECT_USER_ID);
        return appUserPasswordDO;
    }

}