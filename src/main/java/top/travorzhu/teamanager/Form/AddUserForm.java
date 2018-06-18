package top.travorzhu.teamanager.Form;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public class AddUserForm {

    @Size(max = 12,min = 6)
//    @Pattern(regexp = "/w")
    @NotNull
    String username;

    @Size(max = 24,min = 8)
//    @Pattern(regexp = "/w")
    @NotNull
    String password;

    @NotNull
    String passworda;

    @Email
    @NotNull
    String email;

    @NotNull
    String role;

    public AddUserForm(@Size(max = 12, min = 6) @NotNull String username, @Size(max = 24, min = 8) @NotNull String password, @NotNull String passworda, @Email @NotNull String email, @NotNull String role) {
        this.username = username;
        this.password = password;
        this.passworda = passworda;
        this.email = email;
        this.role = role;
    }

    public String getPassworda() {
        return passworda;
    }

    public void setPassworda(String passworda) {
        this.passworda = passworda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
