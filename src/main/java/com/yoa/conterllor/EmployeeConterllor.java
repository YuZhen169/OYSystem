package com.yoa.conterllor;

import com.yoa.entity.Employee;
import com.yoa.service.ClaimVoucherService;
import com.yoa.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by ❤ on 2019/10/29.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeConterllor {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private ClaimVoucherService claimVoucherService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(@RequestParam("name")String name,
                        @RequestParam("password")String password,
                        HttpSession session){
        try {
            Employee employee=employeeService.login(name,password);
            if (employee!=null){
                System.out.println(employee);
                session.setAttribute("employee",employee);
                return "ok";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "no";
    }

    @RequestMapping(value = "/index")
    public String index(HttpSession session,Model model){
        String tips=null;
        Employee employee=(Employee)session.getAttribute("employee");

        try {
            if (null!=employee){
                if (employee.getPositionId()!=1){
                    if  (employee.getPositionId()==2){
                        tips=claimVoucherService.findByAuditCount(employee.getSn(),"待审核").toString();
                    }else{
                        tips=claimVoucherService.findByAuditCount(employee.getSn(),"已审核").toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("0".equals(tips)){
            tips=null;
        }
        model.addAttribute("tips",tips);
        return "index";
    }

    @RequestMapping(value = "/exit")
    public String exit(HttpSession session){
        session.removeAttribute("employee");
        return "redirect:/login.html";
    }

    @RequestMapping(value = "/welcome")
    public String welcome(HttpSession session){
        return "redirect:/welcome.html";
    }


}
