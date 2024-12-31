package com.contentfarm.core.repository.appuser;

import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AppUserRepository extends ReactiveCrudRepository<AppUserDO, String> {
    Mono<AppUserDO> getAppUserDOByUsername(String username);
}
