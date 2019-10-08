package by.jacviah.winery.dao;

import by.jacviah.winery.dao.impl.DefaultUserDAO;
import by.jacviah.winery.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class Run {

    private static final UserDAO userDAO = DefaultUserDAO.getInstance();
    private static final Logger log = LoggerFactory.getLogger(Run.class);

    public static void main(String args[]) {
        User user = new User("aaa", "ddd");
        try {
            User sevedUser = userDAO.addUser(user);
        } catch (IOException e) {
            log.error("fail to save user:{}", user.getUsername(), e);
            throw new RuntimeException(e);
        }
    }


}
