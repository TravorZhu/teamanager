package top.travorzhu.teamanager.Table;

import lombok.Data;
import top.travorzhu.teamanager.Entity.Tea.TeaBig;
import top.travorzhu.teamanager.Entity.Tea.TeaSmall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
public class SearchForm {
    boolean error;
    boolean success;

    String bigId;
    String smallId;
    String name;
    String makerName;
    String detail;
    String picUrl;
    int searchTimes;
    String lastSearchDate;

    public SearchForm(TeaBig teaBig, TeaSmall teaSmall) {
        this.bigId = teaBig.getId();
        this.smallId = teaSmall.getId();
        this.name = teaBig.getName();
        this.makerName = teaBig.getMakeName();
        this.detail = teaBig.getDetail();
        this.picUrl = teaBig.getImgPath();
        this.searchTimes = teaSmall.getCheckedTime();
        if (teaSmall.getLastCheckDate() != null) {
            DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            this.lastSearchDate = df.format(teaSmall.getLastCheckDate());
        } else {
            this.lastSearchDate = "该茶叶未被查询过";
        }
    }
}
