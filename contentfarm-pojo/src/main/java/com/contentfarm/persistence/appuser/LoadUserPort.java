package com.contentfarm.persistence.appuser;

import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import reactor.core.publisher.Mono;

public interface LoadUserPort {
    Mono<AppUserDO> loadUserByUsername(String username);
}
