package com.contentfarm.core.controller.account;

import com.contentfarm.adapter.in.web.command.account.LoginCommand;
import com.contentfarm.adapter.out.web.response.DataResponseResult;
import com.contentfarm.adapter.out.web.response.dto.LoginResponseDto;
import com.contentfarm.core.service.account.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/account/login")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/mono")
    public Mono<String> getMonoResult() {
        return Mono.just("Result from Mono");
    }

    @PostMapping(value = "/login")
    public Mono<DataResponseResult<LoginResponseDto>> login(@RequestBody LoginCommand loginCommand) {
        log.info("login command:{}", loginCommand);
        return loginService.login(loginCommand)
                .map(appUserDomainModelMono -> {
                    LoginResponseDto loginResponseDto = new LoginResponseDto();
                    DataResponseResult<LoginResponseDto> dataResponseResult = new DataResponseResult();
                    dataResponseResult.setData(loginResponseDto);
                    dataResponseResult.setCode(200);
                    dataResponseResult.setMessage("success");
                    return dataResponseResult;
                });
    }
}
