package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.repository.UserRepository;
import by.jacviah.winery.dao.util.mapper.UserMapper;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultUserDAO implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private final UserRepository repository;

    public DefaultUserDAO(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findUser(String login) {
        final UserEntity entity = repository.findByUsername(login);
        return UserMapper.toDTO(entity);
    }

    @Override
    public boolean addUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        repository.saveAndFlush(entity);
        return (entity.getId()!=null);
    }

    @Override
    public boolean removeUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        repository.delete(entity);
        return (repository.findById(entity.getId())==null);
    }

}
