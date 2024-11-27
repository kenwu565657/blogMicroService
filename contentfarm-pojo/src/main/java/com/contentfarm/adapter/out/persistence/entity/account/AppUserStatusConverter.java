package com.contentfarm.adapter.out.persistence.entity.account;

import com.contentfarm.domain.appuser.AppUserStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AppUserStatusConverter implements AttributeConverter<AppUserStatus, String> {

    @Override
    public String convertToDatabaseColumn(AppUserStatus appUserStatus) {
        return appUserStatus.getCodeInDb();
    }

    @Override
    public AppUserStatus convertToEntityAttribute(String dbData) {
        return AppUserStatus.ACTIVE;
    }
}
