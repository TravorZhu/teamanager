package top.travorzhu.teamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import top.travorzhu.teamanager.Form.AddUserForm;
import top.travorzhu.teamanager.MyUtil;
import top.travorzhu.teamanager.Entity.User.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserNRoleRepository userNRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String index(Model model){
        if (userRepository.count()==0){
            return "redirect:/init";
        }
        model.addAttribute("isLogin", MyUtil.isLogin(SecurityContextHolder.getContext().getAuthentication()));
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/init")
    public String init(AddUserForm addUserForm){
        if (userRepository.count()==0){
            return "init";
        }
        return "/";
    }

    @PostMapping("/init")
    public String init(@Valid AddUserForm form, BindingResult bindingResult){
        if (userRepository.findByUserName(form.getUsername())!=null){
            bindingResult.addError(new FieldError("addUserForm","username","用户名已存在"));
        }
        if (!form.getPassword().equals(form.getPassworda())){
            bindingResult.addError(new FieldError("addUserForm","passworda","两次密码输入不一样"));
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        for (ObjectError error:
             bindingResult.getAllErrors()) {
            System.out.println(String.format("%s\t:\t%s",error.getObjectName(),error.getDefaultMessage()));
        }
        roleRepository.save(new UserRole(1,"ROLE_ADMIN"));
        roleRepository.save(new UserRole(2,"ROLE_FACTORY"));
        roleRepository.save(new UserRole(3,"ROLE_RETAIL"));
        int role=1;
        userRepository.saveAndFlush(new MyUserDetail(form));
        userNRoleRepository.save(new UserEntityRole(userRepository.findByUserName(form.getUsername()).getId(), role));
        return "redirect:/";
    }
}
