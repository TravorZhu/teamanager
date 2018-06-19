package top.travorzhu.teamanager.Table;

import lombok.Data;
import top.travorzhu.teamanager.Entity.User.MyUserDetail;
import top.travorzhu.teamanager.Entity.User.RoleRepository;
import top.travorzhu.teamanager.Entity.User.UserNRoleRepository;

@Data
public class UserForm {
    private int id;
    private String username;
    private String role;
    private String email;

    final RoleRepository roleRepository;

    final UserNRoleRepository userNRoleRepository;

    public UserForm(MyUserDetail userDetail, RoleRepository roleRepository, UserNRoleRepository userNRoleRepository){
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
}
