package com.contentfarm.adapter.out.persistence.entity.account;

import com.contentfarm.domain.appuser.AppUserStatus;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ReadingConverter
public class ValueToAppUserStatusConverter implements Converter<String, AppUserStatus> {
    @Override
    public AppUserStatus convert(String source) {
        if (null == source) {
            return null;
        }
        return Arrays.stream(AppUserStatus.values())
                .filter(x -> source.equals(x.getCodeInDb()))
                .findAny()
                .orElse(null);
    }
}
