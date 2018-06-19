package top.travorzhu.teamanager.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
