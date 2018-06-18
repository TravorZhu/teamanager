package top.travorzhu.teamanager.Form;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.validation.Valid;

@Valid
public class AddTeaForm {
    String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
