package top.travorzhu.teamanager.Entity.Tea;

import org.springframework.security.core.context.SecurityContextHolder;
import top.travorzhu.teamanager.Entity.Factory.Factory;

import javax.persistence.*;

@Entity
public class TeaSmall {
    @Id
    String id;

    String teaBigId;

    boolean isSaled;

    public TeaSmall(String id,String teaBig) {
        this.id = id;
        this.isSaled=false;
        this.teaBigId=teaBig;
    }

    public TeaSmall() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeaBig() {
        return teaBigId;
    }

    public void setTeaBig(String teaBig) {
        this.teaBigId = teaBig;
    }
}
