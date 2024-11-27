package com.contentfarm.core.repository.appuser;

import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import io.r2dbc.spi.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class AppUserReactiveDao {
    private final DatabaseClient databaseClient;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    public AppUserReactiveDao(DatabaseClient databaseClient, R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.databaseClient = databaseClient;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

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
