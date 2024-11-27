package com.contentfarm.core.repository.appuserpassword;

import com.contentfarm.adapter.out.persistence.entity.appuserpassword.AppUserPasswordDO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AppUserPasswordRepository extends ReactiveCrudRepository<AppUserPasswordDO, String> {
    Mono<AppUserPasswordDO> getAppUserPasswordDOByAppUserId(String appUserId);
}
