package top.travorzhu.teamanager.Form;

import javax.validation.constraints.NotNull;

public class EditFactoryForm {
    @NotNull
    String name;
    @NotNull
    String address;

    public EditFactoryForm() {
    }

    public EditFactoryForm(@NotNull String name, @NotNull String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
