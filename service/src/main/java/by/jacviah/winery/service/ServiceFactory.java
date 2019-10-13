package by.jacviah.winery.service;

import by.jacviah.winery.service.impl.DefaultUserService;
import by.jacviah.winery.service.impl.DefaultWineService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    private UserService userService = new DefaultUserService();
    private WineService wineService = new DefaultWineService();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public WineService getWineService() {
        return wineService;
    }

}
