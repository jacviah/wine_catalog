package by.jacviah.winery.dao.impl;

import by.jacviah.winery.dao.UserDAO;
import by.jacviah.winery.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MapUserDAO implements UserDAO {

    private HashMap<String, User> storage = new HashMap<>();
    private HashMap<String, UUID> security = new HashMap<>();

    public void init() {
        storage.put("player", new User("player","123"));
        System.out.println("player");
        storage.put("user1", new User("user1","pass1"));
        System.out.println("user1");
        System.out.println("---------------");
    }

    public User addUser(User user) {

        storage.put(user.getUsername(), user);
        UUID uuid = UUID.randomUUID();
        security.put(user.getUsername(), uuid);
        return user;

    }

    public UUID getUUID(User user) {
        return security.get(user.getUsername());
    }

    public List<User> getAll() throws IOException {

        ArrayList<User> all = new ArrayList<>();
        for (User value : storage.values()) {
           all.add(value);
        }
        return all;
    }

}
