package top.travorzhu.teamanager.Entity.Tea;

import org.aspectj.weaver.ast.Var;
import org.springframework.web.multipart.MultipartFile;
import top.travorzhu.teamanager.Entity.Factory.Factory;
import top.travorzhu.teamanager.Form.AddTeaForm;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TeaBig {
    @Id
    String id;
    String name;
    String detail;

    String imgPath;

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    List<TeaSmall> teaSmalls;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    Factory factory;

    @Temporal(TemporalType.DATE)
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TeaBig(String id, String name, String detail, List<TeaSmall> teaSmalls, Factory factory) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.teaSmalls = teaSmalls;
        this.factory = factory;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public TeaBig(AddTeaForm addTeaForm, Factory factory){
        this.name=addTeaForm.getName();
        this.detail=addTeaForm.getDetail();
        this.factory=factory;

        SimpleDateFormat df = new SimpleDateFormat("YYYYMMDD");
        String id=df.format(new Date());
        id+=String.format("%04d",factory.getId());
        id+=String.format("%04d",factory.getLastTeaNum());
        this.id=id;
        teaSmalls=new ArrayList<>();
        for (int i=0;i<10;i++) {
            teaSmalls.add(new TeaSmall(id + i, id));
        }
    }

    public TeaBig() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<TeaSmall> getTeaSmalls() {
        return teaSmalls;
    }

    public void setTeaSmalls(List<TeaSmall> teaSmalls) {
        this.teaSmalls = teaSmalls;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
