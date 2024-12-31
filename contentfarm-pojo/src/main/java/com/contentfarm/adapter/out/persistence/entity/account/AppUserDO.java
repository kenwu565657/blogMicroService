package com.contentfarm.adapter.out.persistence.entity.account;

import com.contentfarm.domain.appuser.AppUserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "APP_USER")
@Getter
@Setter
@ToString
public class AppUserDO {
    @Id
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