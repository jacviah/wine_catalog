package by.jacviah.winery.dao;

import by.jacviah.winery.model.User;

import java.util.List;

public interface UserDAO {

   public User findUser(String name);
   public User findUser(Long id);
   public List<User> getUsersByRole(String role);
   public List<User> getUsersBySommelier(Long sommelierId);
   public boolean addUser(User user);
   public boolean removeUser(User user);
   public void setSommelier(Long userId, Long sommelierId);
   public User getUsersSommelier(Long userId);
}
