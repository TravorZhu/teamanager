package top.travorzhu.teamanager.Form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public class ChangePasswordForm {
    @NotNull
    String username;

    @Size(max = 24,min = 8)
    @NotNull
    String password;

    @NotNull
    String passworda;

    public ChangePasswordForm(@NotNull String username, @Size(max = 24, min = 8) @NotNull String password, @NotNull String passworda) {
        this.username = username;
        this.password = password;
        this.passworda = passworda;
    }

    public ChangePasswordForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassworda() {
        return passworda;
    }

    public void setPassworda(String passworda) {
        this.passworda = passworda;
    }
}

