package com.contentfarm.core.repository.appuser;

import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import io.r2dbc.spi.Row;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.repository.query.RelationalEntityInformation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Repository
public class AppUserReactiveDao implements ReactiveCrudRepository<AppUserDO, String> {
    private final AppUserRepository appUserRepository;
    private final DatabaseClient databaseClient;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Mono<AppUserDO> getAppUserDOByUsername(String username) {
        return appUserRepository.getAppUserDOByUsername(username);
    }

    @Override
    public <S extends AppUserDO> Mono<S> save(S entity) {
        return r2dbcEntityTemplate.insert(entity);
    }

    @Override
    public <S extends AppUserDO> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends AppUserDO> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }

    @Override
    public Mono<AppUserDO> findById(String s) {
        return null;
    }

    @Override
    public Mono<AppUserDO> findById(Publisher<String> id) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(String s) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(Publisher<String> id) {
        return null;
    }

    @Override
    public Flux<AppUserDO> findAll() {
        return null;
    }

    @Override
    public Flux<AppUserDO> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public Flux<AppUserDO> findAllById(Publisher<String> idStream) {
        return null;
    }

    @Override
    public Mono<Long> count() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(String s) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Publisher<String> id) {
        return null;
    }

    @Override
    public Mono<Void> delete(AppUserDO entity) {
        return null;
    }

    @Override
    public Mono<Void> deleteAllById(Iterable<? extends String> strings) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Iterable<? extends AppUserDO> entities) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll(Publisher<? extends AppUserDO> entityStream) {
        return null;
    }

    @Override
    public Mono<Void> deleteAll() {
        return null;
    }

    /*
    @Autowired
    public AppUserReactiveDao(ReactiveCrudRepository<AppUserDO, String> simpleR2dbcRepository, DatabaseClient databaseClient, R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.simpleR2dbcRepository = simpleR2dbcRepository;
        this.databaseClient = databaseClient;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

     */

    /*
    public Flux<AppUserDO> getLikePostInteractionWithUserId(Integer postId, Integer userId) {
        return databaseClient.sql("SELECT * FROM postinteraction WHERE post_id = :post_id " +
                        "AND user_id = :user_id AND (interaction_type = 0 OR interaction_type = 1)")
                .bind("post_id", postId)
                .bind("user_id", userId)
                .map((row, rowMetadata) -> mapRowToPostInteraction(row))
                .all();
    }

    public Flux<AppUserDO> getLikePostInteractionWithUserId(Integer postId) {
        return r2dbcEntityTemplate.
    }

    private AppUserDO mapRowToPostInteraction(Row row) {
        // Map the row data to a PostInteraction object
        // Example:
        return PostInteraction.builder()
                .id(row.get("id", Integer.class))
                .post(row.get("post", Post.class))
                .user(row.get("user", User.class))
                .interactionType(row.get("interactionType", InteractionType.class))
                .comment(row.get("comment", String.class))
                // Map other columns as needed
                .build();
    }

     */
}
