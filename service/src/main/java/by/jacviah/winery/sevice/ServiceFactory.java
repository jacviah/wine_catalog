package by.jacviah.winery.sevice;

import by.jacviah.winery.sevice.impl.DefaultUserService;
import by.jacviah.winery.sevice.impl.DefaultWineService;
import by.jacviah.winery.sevice.impl.DefaultSommService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {}

    private UserService userService = new DefaultUserService();
    private WineService wineService = new DefaultWineService();
    private SommService sommService = new DefaultSommService();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public WineService getWineService() {
        return wineService;
    }

    public SommService getSommService() {
        return sommService;
    }
}
