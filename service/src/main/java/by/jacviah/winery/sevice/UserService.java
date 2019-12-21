package by.jacviah.winery.sevice;

import by.jacviah.winery.model.User;
import by.jacviah.winery.dao.exception.DaoException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public User findUser(String name);
    public List<User> getUsersByRole(String role);
    public List<User> getUsersBySommelier(Long id);
    public boolean createUser(String name, String password);
    public void subscribe(Long userId, Long sommelierId);
}
