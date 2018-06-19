package top.travorzhu.teamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import top.travorzhu.teamanager.Entity.Factory.Factory;
import top.travorzhu.teamanager.Entity.Factory.FactoryRepostory;
import top.travorzhu.teamanager.Entity.Tea.TeaBig;
import top.travorzhu.teamanager.Entity.Tea.TeaBigRepository;
import top.travorzhu.teamanager.Entity.Tea.TeaSmall;
import top.travorzhu.teamanager.Entity.Tea.TeaSmallRepository;
import top.travorzhu.teamanager.Entity.User.MyUserDetail;
import top.travorzhu.teamanager.Entity.User.UserRepository;
import top.travorzhu.teamanager.Form.AddTeaForm;
import top.travorzhu.teamanager.Form.EditFactoryForm;
import top.travorzhu.teamanager.MyUtil;
import top.travorzhu.teamanager.storage.StorageService;

import javax.validation.Valid;

@Controller
public class FactoryController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FactoryRepostory factoryRepostory;

    @Autowired
    private TeaBigRepository teaBigRepository;

    @Autowired
    private TeaSmallRepository teaSmallRepository;




    @GetMapping("/factory")
    String index(Model model) {
        model.addAttribute("username", MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        return "/factory/index";
    }

    @GetMapping("/factory/add")
    String add(Model model, AddTeaForm addTeaForm) {
        model.addAttribute("username", MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        return "/factory/add";
    }

    @PostMapping("/factory/add")
    String addPost(@Valid AddTeaForm addTeaForm, BindingResult bindingResult) {
        MyUserDetail user = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Factory factory = factoryRepostory.findByMyUserDetail(user);
        if (factory==null)
            return "redirect:/factory/editfactory?need";
        if (bindingResult.hasErrors())
            return "/factory/add";
        factory.setLastTeaNum(factory.getLastTeaNum() + 1);
        MultipartFile file=addTeaForm.getImagine();
        TeaBig teaBig = new TeaBig(addTeaForm, factory);
        String fileType=file.getOriginalFilename().split(".",2)[1];
        String fileName=teaBig.getId()+"."+fileType;
        storageService.store(file, fileName);
        teaBig.setImgPath(String.format("/img/%s",fileName));
        for (TeaSmall teaSmall:
                teaBig.getTeaSmalls()) {
            teaSmallRepository.save(teaSmall);
        }
        teaBigRepository.save(teaBig);
        return "/factory/addsuccess";
    }

    @GetMapping("/factory/editfactory")
    String EditFactory(Model model,EditFactoryForm editFactoryForm){
        model.addAttribute("username", MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        return "/factory/editfactory";
    }

    @PostMapping("/factory/editfactory")
    String EditFactory(@Valid EditFactoryForm editFactoryForm,BindingResult bindingResult){
        MyUserDetail myUserDetail=(MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Factory factory = factoryRepostory.findByMyUserDetail(myUserDetail);
        if (factory==null){
            factory=new Factory(editFactoryForm.getName(),editFactoryForm.getAddress(),userRepository.findByUserName(myUserDetail.getUserName()));
            factoryRepostory.save(factory);
        }
        else {
            factory.setAddr(editFactoryForm.getAddress());
            factory.setName(editFactoryForm.getName());
        }
        return "redirect:/factory/editfactory?success";
    }
}
