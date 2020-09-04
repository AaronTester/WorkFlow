package com.flow.main.controller;

import com.flow.api.entry.CommonResutMessage;
import com.flow.api.entry.User;
import com.flow.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author Aaron
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2020/8/26
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("login/check")
    public ModelAndView checkLoginEmp(@RequestBody User user) {
        ModelAndView mav = new ModelAndView();
        User newUser = userService.checkUser(user);
        if (null != newUser) {
            mav.setViewName("main");
            mav.addObject("USER_INFO",newUser);
        }else {
            mav.setViewName("error");
            mav.addObject("ERROR_MSG", "未查询到该用户");
        }
        return mav;
    }
    @PostMapping("login/validate")
    @ResponseBody
    public CommonResutMessage loginValidate(@RequestBody User user, Model model,
                                            HttpSession session) {
        User newUser = userService.checkUser(user);
        if(null != newUser) {
            model.addAttribute("USERINFO", newUser);
            session.setAttribute("USERINFO", newUser);
            return new CommonResutMessage();
        }else {
            return CommonResutMessage.fail();
        }
    }

    @GetMapping("login/main")
    public String goToMain(Model model,HttpSession session) {
        User user = (User) model.getAttribute("USERINFO");
        if(null != user) {
            return "main";
        }else { user = (User) session.getAttribute("USERINFO");
            if (user != null) {
                model.addAttribute("USERINFO", user);
                return "main";
            }
           return "error";
        }
    }
}
