package by.jacviah.winery.dao;

import by.jacviah.winery.model.Role;
import by.jacviah.winery.model.User;

public interface SommDAO {
    public boolean setRole(User user, Role role);
}
