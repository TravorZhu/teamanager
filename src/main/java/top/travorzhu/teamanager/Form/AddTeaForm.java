package top.travorzhu.teamanager.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddTeaForm {
    String name;

    String makeName;

    String detail;

    MultipartFile imagine;

    public MultipartFile getImagine() {
        return imagine;
    }

    public void setImagine(MultipartFile imagine) {
        this.imagine = imagine;
    }

    public AddTeaForm(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }

    public AddTeaForm() {
    }

}
