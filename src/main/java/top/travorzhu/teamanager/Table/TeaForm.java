package top.travorzhu.teamanager.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeaForm {
    private String id;
    private String name;
    private String madeName;
    private int selled;
    private String picUrl;
}
