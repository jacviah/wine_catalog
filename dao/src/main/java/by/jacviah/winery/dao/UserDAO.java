package by.jacviah.winery.dao;

import by.jacviah.winery.model.User;

public interface UserDAO {

   public User findUser(String name);
   public boolean addUser(User user);
   public boolean removeUser(User user);
}
