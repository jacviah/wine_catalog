package by.jacviah.winery.service;

import by.jacviah.winery.model.User;

import java.io.IOException;
import java.util.UUID;

public interface UserService {

    public void init();
    public User findUser(String name) throws IOException;
    public UUID getUserUUID(String name) throws IOException;
    public User createUser(String name, String password) throws IOException;
}
