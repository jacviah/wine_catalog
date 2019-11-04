package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;

public class UserMapper {

    public static UserEntity toEntity(User dto) {

        return UserEntity.UserEntityBuilder.anUserEntity()
                .withId(dto.getId())
                .withUsername(dto.getUsername())
                .withPassword(dto.getPassword())
                .withRole(dto.getRole().toString())
                .withUuid(dto.getUuid())
                .build();
    }


    public static User toDTO(UserEntity entity) {
        return User.UserBuilder.anUser()
                .withId(entity.getId())
                .withUsername(entity.getUsername())
                .withPassword(entity.getPassword())
                .withRole(Role.asRole(entity.getRole()))
                .withUuid(entity.getUuid())
                .build();
    }
}
