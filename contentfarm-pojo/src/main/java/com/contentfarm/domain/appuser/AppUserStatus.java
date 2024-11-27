package com.contentfarm.domain.appuser;

import com.contentfarm.persistence.ConverterBase;
import com.contentfarm.persistence.EnumBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppUserStatus implements EnumBase<String> {
    ACTIVE("A"),
    INACTIVE("I");

    private final String codeInDb;

    public static class Converter extends ConverterBase<AppUserStatus, String> {
        public Converter() {
            super(AppUserStatus.class);
        }
    }
}
