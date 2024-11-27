package com.contentfarm.adapter.out.persistence.entity.account;

import com.contentfarm.domain.appuser.AppUserStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class AppUserStatusConverterToValueConverter implements Converter<AppUserStatus, String> {
    @Override
    public String convert(AppUserStatus source) {
        if (null == source) {
            return null;
        }
        return source.getCodeInDb();
    }
}
