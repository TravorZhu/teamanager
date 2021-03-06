package top.travorzhu.teamanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.travorzhu.teamanager.Entity.Tea.TeaSmall;
import top.travorzhu.teamanager.Entity.Tea.TeaSmallRepository;
import top.travorzhu.teamanager.Entity.User.MyUserDetail;
import top.travorzhu.teamanager.Form.SaledTeaForm;
import top.travorzhu.teamanager.MyUtil;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
public class RetailController {
    @Autowired
    TeaSmallRepository teaSmallRepository;

    @GetMapping("/retail")
    String RetailIndex(Model model) {
        MyUserDetail myUserDetail = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", myUserDetail.getUserName());
        model.addAttribute("lastsale", teaSmallRepository.countAllBySaledIsTrueAndSaleUserDetailIdAndSaledLastMonth(myUserDetail.getId()));
        model.addAttribute("allsale", teaSmallRepository.countAllBySaledIsTrueAndSaleUserDetailId(myUserDetail.getId()));
        return "/retail/index";
    }

    @GetMapping("/retail/sale")
    String Sale(Model model, SaledTeaForm saledTeaForm) {
        model.addAttribute("username", MyUtil.getUsername(SecurityContextHolder.getContext().getAuthentication()));
        return "/retail/sale";
    }

    @PostMapping("/retail/sale")
    String SalePost(@Valid SaledTeaForm saledTeaForm, BindingResult bindingResult) {
        Optional<TeaSmall> optionalTeaSmall = teaSmallRepository.findById(saledTeaForm.getTeaId());
        if (!optionalTeaSmall.isPresent()) {
            bindingResult.addError(new FieldError("saledTeaForm", "teaId", "未找到该茶叶小包"));
        } else {
            TeaSmall teaSmall = optionalTeaSmall.get();
            if (teaSmall.isSaled())
                bindingResult.addError(new FieldError("saledTeaForm", "teaId", "该茶叶小包已售出"));
            else {
                teaSmall.setSaled(true);
                teaSmall.setSaleDate(new Date());
                teaSmall.setSaleUserDetailId(((MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
                teaSmallRepository.save(teaSmall);
            }

        }
        if (bindingResult.hasErrors())
            return "/retail/sale";
        return "redirect:/retail/sale?success";
    }
}
