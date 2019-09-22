package by.jacviah.winery.dao;

import by.jacviah.winery.model.User;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserDAO {

    public void init();
    public List<User> getAll() throws IOException;
    public User addUser(User user) throws IOException;
    public UUID getUUID(User user);
}
