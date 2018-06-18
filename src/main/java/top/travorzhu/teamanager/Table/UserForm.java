package top.travorzhu.teamanager.Table;
import top.travorzhu.teamanager.Entity.User.MyUserDetail;
import top.travorzhu.teamanager.Entity.User.RoleRepository;
import top.travorzhu.teamanager.Entity.User.UserNRoleRepository;

public class UserForm {
    private int id;
    private String username;
    private String role;
    private String email;

    final RoleRepository roleRepository;

    final UserNRoleRepository userNRoleRepository;

    public UserForm(MyUserDetail userDetail,RoleRepository roleRepository,UserNRoleRepository userNRoleRepository){
        this.roleRepository=roleRepository;
        this.userNRoleRepository=userNRoleRepository;
        id=userDetail.getId();
        username=userDetail.getUserName();
        int roleid= userNRoleRepository.findByMyUserDetailId(id).getRolesId();
        role=roleRepository.findById(roleid).getName();
        email=userDetail.getEmail();
        switch (role){
            case "ROLE_ADMIN":role="管理员";   break;
            case "ROLE_FACTORY":role="工厂";  break;
            case "ROLE_RETAIL":role="零售商"; break;
            default:role="ERROR"; break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
