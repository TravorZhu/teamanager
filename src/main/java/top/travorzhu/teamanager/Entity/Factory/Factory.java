package top.travorzhu.teamanager.Entity.Factory;

import top.travorzhu.teamanager.Entity.User.MyUserDetail;

import javax.persistence.*;

@Entity
public class Factory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;

    String name;
    String addr;
    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    MyUserDetail myUserDetail;

    int lastTeaNum;

    public Factory(String name, String addr, MyUserDetail myUserDetail) {
        this.name = name;
        this.addr = addr;
        this.lastTeaNum=0;
        this.myUserDetail=myUserDetail;
    }

    public Factory() {
    }

    public int getLastTeaNum() {
        return lastTeaNum;
    }

    public void setLastTeaNum(int lastTeaNum) {
        this.lastTeaNum = lastTeaNum;
    }

    public MyUserDetail getMyUserDetail() {
        return myUserDetail;
    }

    public void setMyUserDetail(MyUserDetail myUserDetail) {
        this.myUserDetail = myUserDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
