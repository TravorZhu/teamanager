package top.travorzhu.teamanager.Entity.Tea;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class TeaSmall {
    @Id
    private String id;

    private String teaBigId;

    private boolean saled;

    private int checkedTime;

    private Date lastCheckDate;

    private Date saleDate;

    private int saleUserDetailId;

    public TeaSmall(String id,String teaBig) {
        this.id = id;
        this.saled = false;
        this.checkedTime = 0;
        this.lastCheckDate = null;
        this.teaBigId = teaBig;
        this.saleUserDetailId = 0;
    }
}
