package com.contentfarm.application.usecase.account;

import com.contentfarm.adapter.in.web.command.account.LoginCommand;
import com.contentfarm.domain.appuser.AppUserDomainModel;
import reactor.core.publisher.Mono;

public interface LoginUseCase {
    Mono<AppUserDomainModel> login(LoginCommand loginCommand);
}
