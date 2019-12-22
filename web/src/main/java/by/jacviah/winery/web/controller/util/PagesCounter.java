package by.jacviah.winery.web.controller.util;

public class PagesCounter {

    public static int countPages(int elements, int pageSize) {
        return elements % pageSize == 0 ? elements / pageSize : elements / pageSize + 1;
    }
}
