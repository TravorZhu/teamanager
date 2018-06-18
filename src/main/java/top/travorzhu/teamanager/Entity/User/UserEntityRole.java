package top.travorzhu.teamanager.Entity.User;

import javax.persistence.*;

@Entity
@Table(name = "user_entity_roles")
public class UserEntityRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "my_user_detail_id")
    private
    int myUserDetailId;

    @Column(name = "roles_id")
    private
    int rolesId;

    public UserEntityRole(int myUserDetailId, int rolesId) {
        this.myUserDetailId = myUserDetailId;
        this.rolesId = rolesId;
    }

    public UserEntityRole() {
    }

    public int getMyUserDetailId() {
        return myUserDetailId;
    }

    public void setMyUserDetailId(int myUserDetailId) {
        this.myUserDetailId = myUserDetailId;
    }

    public int getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
