package by.jacviah.winery.web.rq;

import by.jacviah.winery.model.User;

public class SimpleUserForm {
    private long id;
    private String username;
    private String description;

    public SimpleUserForm() {
    }


    public SimpleUserForm(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.description = user.getDetail().getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
