package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.SommDAO;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.repository.UserRepository;
import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSommDAO implements SommDAO {
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);
    private final UserRepository repository;

    public DefaultSommDAO(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean setRole(User user, Role role) {
        UserEntity entity = repository.getOne(user.getId());
        entity.setRole(role.toString());
        repository.saveAndFlush(entity);
        return (repository.getOne(user.getId()).getRole().equals(role.toString()));
    }
}
