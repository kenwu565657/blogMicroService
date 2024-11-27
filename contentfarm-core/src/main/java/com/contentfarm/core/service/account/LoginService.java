package com.contentfarm.core.service.account;

import com.contentfarm.adapter.in.web.command.account.LoginCommand;
import com.contentfarm.application.usecase.account.LoginUseCase;
import com.contentfarm.core.repository.appuser.AppUserRepository;
import com.contentfarm.core.repository.appuserpassword.AppUserPasswordRepository;
import com.contentfarm.domain.appuser.AppUserDomainModel;
import com.contentfarm.domain.appuser.AppUserDomainModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class LoginService implements LoginUseCase {
    private final AppUserRepository appUserRepository;
    private final AppUserPasswordRepository appUserPasswordRepository;

    @Override
    public Mono<AppUserDomainModel> login(LoginCommand loginCommand) {
        return appUserRepository
                .getAppUserDOByUsername(loginCommand.getUsername())
                .flatMap(appUserDO -> Mono.zip(
                        Mono.just(appUserDO),
                        appUserPasswordRepository.getAppUserPasswordDOByAppUserId(appUserDO.getId())
                ))
                .map(tuple2Flux -> AppUserDomainModelRepository.retrieveDomainModel(tuple2Flux.getT1(), tuple2Flux.getT2()));
    }
}