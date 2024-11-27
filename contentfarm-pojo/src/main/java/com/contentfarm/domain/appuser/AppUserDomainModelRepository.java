package com.contentfarm.domain.appuser;

import com.contentfarm.adapter.out.persistence.entity.account.AppUserDO;
import com.contentfarm.adapter.out.persistence.entity.appuserpassword.AppUserPasswordDO;
import org.springframework.stereotype.Component;

@Component
public class AppUserDomainModelRepository {
    public static AppUserDomainModel retrieveDomainModel(AppUserDO appUserDO, AppUserPasswordDO appUserPasswordDO) {
        AppUserDomainModel appUserDomainModel = new AppUserDomainModel();
        if (null != appUserDO) {
            appUserDomainModel.setUsername(appUserDO.getUsername());
        }
        if (null == appUserPasswordDO) {
            throw new NullPointerException("appUserPasswordDO is null");
        }
        appUserDomainModel.setHashedPassword(appUserPasswordDO.getHashedValue());
        return appUserDomainModel;
    }
}
