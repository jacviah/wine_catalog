package by.jacviah.winery.sevice;

import by.jacviah.winery.model.User;

public interface SecurityService {
    User login(String login, String password);
}
