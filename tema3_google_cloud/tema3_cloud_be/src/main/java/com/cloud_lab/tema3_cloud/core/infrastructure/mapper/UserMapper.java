package com.cloud_lab.tema3_cloud.core.infrastructure.mapper;

import com.cloud_lab.tema3_cloud.core.domain.model.User;
import com.cloud_lab.tema3_cloud.core.infrastructure.entity.UserEntity;

public class UserMapper {
    public static  UserEntity toEntity(User user) {
        UserEntity result = new UserEntity();
        result.id = user.getId();
        result.email = user.getEmail();
        result.firstName = user.getFirstName();
        result.lastName = user.getLastName();
        result.password = user.getPassword();

        return result;
    }

    public static  User fromEntity(UserEntity entity) {
        return new User(entity.id, entity.firstName, entity.lastName, entity.email, entity.password);
    }
}
