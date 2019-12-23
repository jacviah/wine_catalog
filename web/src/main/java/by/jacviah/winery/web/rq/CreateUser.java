package by.jacviah.winery.web.rq;


import by.jacviah.winery.web.constraint.Matches;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Matches(field = "password", fieldMatch = "confirmedPassword", message = "The password fields must match")
public class CreateUser {

    @NotBlank @Size(min=1, message = "Login may not be empty")
    private String login;

    @NotBlank @Size(min=1, message = "Password may not be empty")
    private String password;

    @NotBlank @Size(min=1, message = "Confirm password may not be empty")
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
