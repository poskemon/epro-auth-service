package com.poskemon.epro.authservice.common.util;

import com.poskemon.epro.authservice.common.constants.UserRole;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * UserRole enum의 converter
 */
@Converter
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRole attribute) {
        return attribute.getRoleCode(); // enum -> db data
    }

    @Override
    public UserRole convertToEntityAttribute(Integer dbData) {
        return UserRole.ofRoleCode(dbData); // db data -> enum
    }
}
