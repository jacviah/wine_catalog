package by.jacviah.winery.dao;

import by.jacviah.winery.model.User;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserDAO {

    public User findUser(String login) throws IOException;
    public User addUser(User user) throws IOException;
    public UUID getUUID(User user);
}
