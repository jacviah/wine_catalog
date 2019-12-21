package by.jacviah.winery.dao.util.mapper;

import by.jacviah.winery.dao.entity.UserDetailEntity;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import by.jacviah.winery.model.UserDetail;

public class UserMapper {

    public static UserEntity toEntity(User dto) {
        UserEntity user = UserEntity.UserEntityBuilder.anUserEntity()
                .withId(dto.getId())
                .withUsername(dto.getUsername())
                .withPassword(dto.getPassword())
                .withRole(dto.getRole().toString().toLowerCase())
                .withUuid(dto.getUuid())
                .build();
        if (dto.getDetail()!=null) {
            UserDetailEntity detail = UserDetailEntity.UserDetailEntityBuilder.anUserDetailEntity()
                    .withDescription(dto.getDetail().getDescription())
                    .withUser(user)
                    .build();
            user.setDetail(detail);
        }
        return user;
    }

    public static User toDTO(UserEntity entity) {
        User dto = User.UserBuilder.anUser()
                .withId(entity.getId())
                .withUsername(entity.getUsername())
                .withPassword(entity.getPassword())
                .withRole(Role.asRole(entity.getRole()))
                .withUuid(entity.getUuid())
                .build();
        if (entity.getDetail()!=null) {
            UserDetail detail = new UserDetail(entity.getDetail().getId(), entity.getDetail().getDescription());
            dto.setDetail(detail);
        }
        return dto;
    }
}
