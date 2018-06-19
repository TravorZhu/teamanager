package top.travorzhu.teamanager.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm {
    @NotNull
    String username;

    @Size(max = 24,min = 8)
    @NotNull
    String password;

    @NotNull
    String passworda;
}

