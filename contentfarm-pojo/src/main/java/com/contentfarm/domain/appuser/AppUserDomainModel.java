package com.contentfarm.domain.appuser;

import com.contentfarm.utils.ContentFarmStringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppUserDomainModel {
    private String username;
    private String hashedPassword;

    public boolean isValidModel() {
        return ContentFarmStringUtils.isBlank(username);
    }

}
