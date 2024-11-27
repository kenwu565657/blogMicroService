package com.contentfarm.adapter.in.web.command.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginCommand {
    private String username;
    private String password;
}
