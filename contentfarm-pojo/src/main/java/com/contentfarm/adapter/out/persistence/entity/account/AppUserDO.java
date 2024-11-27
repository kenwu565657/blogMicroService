package com.contentfarm.adapter.out.persistence.entity.account;

import com.contentfarm.domain.appuser.AppUserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "APP_USER")
@org.springframework.data.relational.core.mapping.Table(name = "APP_USER")
@Getter
@Setter
@ToString
public class AppUserDO {
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Enumerated
    @Convert(converter = AppUserStatusConverter.class)
    @Column(name = "STATUS")
    private AppUserStatus status;
}