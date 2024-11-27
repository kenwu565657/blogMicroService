package com.contentfarm.adapter.out.persistence.entity.appuserpassword;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * This class
 *
 * @since 11-25-2024
 *
 *
 */

@ToString
@Getter
@Setter
@Table(name = "APP_USER_PASSWORD")
public class AppUserPasswordDO {
    @Id
    @Column(value = "ID")
    private String id;

    @Column(value = "APP_USER_ID")
    private String appUserId;

    @Column(value = "HASHED_PASSWORD")
    private String hashedValue;

    @Column(value = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;
}
