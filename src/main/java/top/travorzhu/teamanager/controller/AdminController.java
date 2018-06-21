package top.travorzhu.teamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.travorzhu.teamanager.Entity.User.*;
import top.travorzhu.teamanager.Form.AddUserForm;
import top.travorzhu.teamanager.Form.ChangePasswordForm;
import top.travorzhu.teamanager.MyUtil;
import top.travorzhu.teamanager.Table.UserForm;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserNRoleRepository userNRoleRepository;

    @GetMapping("/admin")
    String AdminIndex(Model model){
        model.addAttribute("username",MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        return "admin/index";
    }

    @GetMapping("/admin/adduser")
    String AdminAddUser(AddUserForm addUserForm, Model model){
        model.addAttribute("username",MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        return "admin/adduser";
    }

    @PostMapping("/admin/adduser")
    public String addUser(@Valid AddUserForm form, BindingResult bindingResult){
        if (userRepository.findByUserName(form.getUsername())!=null){
            bindingResult.addError(new FieldError("addUserForm","username","用户名已存在"));
        }
        if (!form.getPassword().equals(form.getPassworda())){
            bindingResult.addError(new FieldError("addUserForm","passworda","两次密码输入不一样"));
        }
        if(bindingResult.hasErrors()){
            return "/admin/adduser";
        }
        int role;
        switch (form.getRole()){
            case "Admin":role=1; break;
            case "Factory":role=2; break;
            case "Retail":role=3; break;
            default: bindingResult.addError(new FieldError("addUserForm","role","请在三项中选择"));return "register";
        }
        userRepository.saveAndFlush(new MyUserDetail(form));
        userNRoleRepository.save(new UserEntityRole(userRepository.findByUserName(form.getUsername()).getId(), role));
        return "redirect:./adduser?add";
    }

    @GetMapping("/admin/listuser")
    String ListUser(Model model){
        model.addAttribute("username",MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        List<UserForm>UserForms=new ArrayList<>();

        for (MyUserDetail user:
        userRepository.findAll()) {
             UserForm userForm=new UserForm(user,roleRepository,userNRoleRepository);
             UserForms.add(userForm);
        }
        model.addAttribute("userForms",UserForms);
        return "/admin/listuser";
    }

    @GetMapping("/admin/deleteuser")
    String DeleteUser(@RequestParam()int id){
        MyUserDetail userDetail = userRepository.getOne(id);
        int id2=userNRoleRepository.findByMyUserDetailId(id).getId();
        userNRoleRepository.deleteById(id2);
        userRepository.deleteById(id);
        return "redirect:./listuser?deletesuccess";
    }

    @GetMapping("/admin/changeuserpassword")
    String ChangeUserPassword(Model model,ChangePasswordForm changePasswordForm,@RequestParam int userId){
        model.addAttribute("username",MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        String username=userRepository.findById(userId).get().getUserName();
        changePasswordForm.setUsername(username);
        return "/admin/changepassword";
    }

    @PostMapping("/admin/changeuserpassword")
    String ChangeUserPasswordPost(Model model,@Valid ChangePasswordForm form,BindingResult bindingResult){
        model.addAttribute("username",MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        if (!form.getPassword().equals(form.getPassworda())){
            bindingResult.addError(new FieldError("addUserForm","passworda","两次密码输入不一样"));
        }
        if(bindingResult.hasErrors()){
            return "redirect:./changeuserpassword?error&userId="+userRepository.findByUserName(form.getUsername()).getId();
        }
        MyUserDetail myUserDetail=userRepository.findByUserName(form.getUsername());
        myUserDetail.setPassWord(new BCryptPasswordEncoder().encode(form.getPassword().trim()));
        userRepository.save(myUserDetail);
        return "redirect:./listuser?changesuccess";
    }
}
