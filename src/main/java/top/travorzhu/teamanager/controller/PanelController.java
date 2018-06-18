package top.travorzhu.teamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import top.travorzhu.teamanager.Entity.User.MyUserDetail;
import top.travorzhu.teamanager.Entity.User.RoleRepository;
import top.travorzhu.teamanager.Entity.User.UserNRoleRepository;

@Controller
public class PanelController {
    @Autowired
    UserNRoleRepository userNRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/retail")
    public String Detail(){
        return "retail";
    }

    @GetMapping("/login_success")
    public String LoginSuccess(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        int userId=((MyUserDetail)userDetails).getId();
        int roleId = userNRoleRepository.findByMyUserDetailId(userId).getRolesId();
        switch (roleRepository.findById(roleId).getName()){
            case "ROLE_ADMIN":      return "redirect:/admin";
            case "ROLE_FACTORY":    return "redirect:/factory";
            case "ROLE_RETAIL":     return "redirect:/retail";
            default:                return "redirect:/logout";
        }
    }
}
