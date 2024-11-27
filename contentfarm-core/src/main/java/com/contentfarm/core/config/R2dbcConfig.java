package com.contentfarm.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

import java.util.List;

@Slf4j
@Configuration
public class R2dbcConfig {

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions(List<Converter<?, ?>> converters) {
        log.info("Converter List: {}", converters);
        return new R2dbcCustomConversions(CustomConversions.StoreConversions.NONE, converters);
    }
}
