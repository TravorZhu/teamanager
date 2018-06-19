package top.travorzhu.teamanager.Entity.Tea;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.travorzhu.teamanager.Entity.Factory.Factory;
import top.travorzhu.teamanager.Form.AddTeaForm;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class TeaBig {
    @Id
    String id;
    String name;
    String makeName;
    String detail;

    String imgPath;

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    List<TeaSmall> teaSmalls;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    Factory factory;

    @Temporal(TemporalType.DATE)
    Date date;

    public TeaBig(AddTeaForm addTeaForm, Factory factory){
        this.name=addTeaForm.getName();
        this.detail=addTeaForm.getDetail();
        this.factory=factory;
        this.makeName = addTeaForm.getMakeName();

        SimpleDateFormat df = new SimpleDateFormat("YYYYMMDd");
        String id=df.format(new Date());
        id+=String.format("%04d",factory.getId());
        id+=String.format("%04d",factory.getLastTeaNum());
        this.id=id;
        teaSmalls=new ArrayList<>();
        for (int i=0;i<10;i++) {
            teaSmalls.add(new TeaSmall(id + i, id));
        }

    }
}
