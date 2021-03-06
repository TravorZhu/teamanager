package top.travorzhu.teamanager.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditFactoryForm {
    @NotNull
    String name;
    @NotNull
    String address;
}
