package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.dao.entity.UserEntity;
import by.jacviah.winery.dao.repository.UserRepository;
import by.jacviah.winery.dao.util.mapper.UserMapper;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDAO implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserDAO.class);

    private final UserRepository repository;

    public DefaultUserDAO(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findUser(String login) {
        final UserEntity entity = repository.findByUsername(login);
        return (entity != null) ? UserMapper.toDTO(entity) : null;
    }

    @Override
    public List<User> getUsersByRole(String role) {
        final List<User> users = repository.getByRole(role).stream().map(entity -> UserMapper.toDTO(entity))
                .collect(Collectors.toList());
        return (users != null) ? users : null;
    }

    @Override
    public List<User> getUsersBySommelier(Long id) {
        UserEntity sommelier = repository.findById(id).get();
        final List<User> users = repository.getBySommelier(sommelier).stream().map(entity -> UserMapper.toDTO(entity))
                .collect(Collectors.toList());
        return (users != null) ? users : null;
    }

    @Override
    public User getUsersSommelier(Long userId) {
        final UserEntity entity = repository.findById(userId).get();
        return UserMapper.toDTO(entity.getSommelier());
    }

    @Override
    public boolean addUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        repository.save(entity);
        return (entity.getId() != null);
    }

    @Override
    public boolean removeUser(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        repository.delete(entity);
        return (repository.findById(entity.getId()) == null);
    }

    @Override
    public void setSommelier(Long userId, Long sommelierId) {
        UserEntity userEntity = repository.findById(userId).get();
        UserEntity sommelierEntity = repository.findById(sommelierId).get();
        userEntity.setSommelier(sommelierEntity);
        repository.save(userEntity);
    }

}
