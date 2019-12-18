package by.jacviah.winery.web.rq;

import by.jacviah.winery.web.validator.EqualFields;

import javax.validation.constraints.NotNull;

@EqualFields(baseField = "password", matchField = "confirmedPassword")
public class CreateUser {

    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    private String confirmedPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
