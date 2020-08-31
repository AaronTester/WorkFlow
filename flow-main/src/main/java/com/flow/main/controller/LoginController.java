package com.flow.main.controller;

import com.flow.main.entity.CommonResutMessage;
import com.flow.main.entity.Employee;
import com.flow.main.service.EmployeeService;
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
    EmployeeService employeeService;

    @PostMapping("login/check")
    public ModelAndView checkLoginEmp(@RequestBody Employee employee) {
        ModelAndView mav = new ModelAndView();
        Employee newEmp = employeeService.checkEmp(employee);
        if (null != newEmp) {
            mav.setViewName("main");
            mav.addObject("USER_INFO",newEmp);
        }else {
            mav.setViewName("error");
            mav.addObject("ERROR_MSG", "未查询到该用户");
        }
        return mav;
    }
    @PostMapping("login/validate")
    @ResponseBody
    public CommonResutMessage loginValidate(@RequestBody Employee employee, Model model,
                                            HttpSession session) {
        Employee newEmp = employeeService.checkEmp(employee);
        if(null != newEmp) {
            model.addAttribute("USERINFO", newEmp);
            session.setAttribute("USERINFO", newEmp);
            return new CommonResutMessage();
        }else {
            return CommonResutMessage.fail();
        }
    }

    @GetMapping("login/main")
    public String goToMain(Model model,HttpSession session) {
        Employee employee = (Employee) model.getAttribute("USERINFO");
        if(null != employee) {
            return "main";
        }else { employee = (Employee) session.getAttribute("USERINFO");
            if (employee != null) {
                model.addAttribute("USERINFO", employee);
                return "main";
            }
           return "error";
        }
    }
}
