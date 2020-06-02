package com.yoa.conterllor;

import com.alibaba.fastjson.JSONObject;
import com.yoa.entity.Department;
import com.yoa.entity.Employee;
import com.yoa.entity.Leave;
import com.yoa.entity.Position;
import com.yoa.service.DepartmentService;
import com.yoa.service.EmployeeService;
import com.yoa.service.LeaveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ❤ on 2019/11/4.
 */
@Controller
@RequestMapping(value = "/leave")
public class LeaveConterllor {

    @Resource
    private LeaveService leaveService;

    @Resource
    private EmployeeService employeeService;


    @RequestMapping(value = "/findByCondition")
    @ResponseBody
    public JSONObject findByCondition(Leave leave, HttpSession session,
                                      @RequestParam (value="page") Integer pageNo,
                                      @RequestParam (value="limit") Integer pageSize){
        JSONObject json = new JSONObject();
        Employee employee=(Employee)session.getAttribute("employee");
        List<Leave> leaveList=new ArrayList<>();
        Integer count=0;
        System.out.println("分页:"+pageNo+","+pageSize);

        if (employee.getPositionId()==2 || employee.getPositionId()==3 || employee.getPositionId()==4){
            leave.setNextDealSn(employee.getSn());
            leaveList=leaveService.findByCondition(leave,pageNo,pageSize);
            count=leaveService.countByCondition(leave,null,null);
        }else if(employee.getPositionId()==1){
            leave.setEmployeeSn(employee.getSn());
            leaveList=leaveService.findByCondition(leave,pageNo,pageSize);
            count=leaveService.countByCondition(leave,null,null);
        }
        json.put("data", leaveList);
        json.put("count", count);
        json.put("msg", "success");
        json.put("code", "0");
        return json;
    }

    @RequestMapping(value = "/findByTimeAndStatus")
    @ResponseBody
    public JSONObject findByTimeAndStatus(@RequestParam("status") String status,
                                           @RequestParam("startTime") String startTime,
                                           @RequestParam("endTime") String endTime,
                                           @RequestParam (value="page") Integer pageNo,
                                           @RequestParam (value="limit") Integer pageSize,
                                           HttpSession session){
        Employee employee=(Employee)session.getAttribute("employee");
        List<Leave> leaveList=new ArrayList<>();
        JSONObject json = new JSONObject();
        Integer count=0;

        Leave leave=new Leave();
        if("请选择".equals(status)){
            status=null;
        }
        leave.setStatus(status);
        System.out.println("startTime:"+startTime+",endTime"+endTime);
        if (employee.getPositionId()==2 || employee.getPositionId()==3 || employee.getPositionId()==4){
            leave.setNextDealSn(employee.getSn());
            count=leaveService.countByCondition(leave,startTime,endTime);
            System.out.println(status+","+startTime+","+endTime+","+employee.getSn());
            leaveList=leaveService.findByTimeAndStatus(status,startTime,endTime,null,employee.getSn(),pageNo,pageSize);
        }else if(employee.getPositionId()==1){
            leave.setEmployeeSn(employee.getSn());
            count=leaveService.countByCondition(leave,startTime,endTime);
            leaveList=leaveService.findByTimeAndStatus(status,startTime,endTime,employee.getSn(),null,pageNo,pageSize);
        }
        json.put("msg", "success");
        json.put("code", "0");
        json.put("data", leaveList);
        json.put("count", count);
        return json;
    }

    //添加请假单
    @RequestMapping(value = "/addLeave")
    public String addLeave(Leave leave,HttpSession session,Model model){
        Employee employee=(Employee)session.getAttribute("employee");
        leave.setEmployeeSn(employee.getSn());      //创建人
        leave.setCreateTime(new Date());        //创建时间
        String flag="";

        Employee employee1=new Employee();
        employee1.setDepartmentId(employee.getDepartmentId());//相同职位
        employee1.setStatus("在职");                          //在职
        employee1.setPositionId(2);                             //经理

        List<Employee> employeeList=employeeService.findByCondition(employee1);
        for (Employee emp :employeeList) {
            leave.setNextDealSn(emp.getSn());       //下一个处理人
            break;
        }
        System.out.println(leave);
        if (leaveService.addLeave(leave)>0){
            //判断是提交还是保存
            if ("待审核".equals(leave.getStatus())){
                flag="提交成功";
            }else{
                flag="保存成功";
            }
        }else{
            if ("待审核".equals(leave.getStatus())){
                flag="提交失败";
            }else{
                flag="保存失败";
            }
        }
        model.addAttribute("flag",flag);
        return "/leave/leave_add";
    }

    //删除请假单
    @RequestMapping(value = "/deleteLeave")
    @ResponseBody
    public Integer deleteLeave(@RequestParam("id")Integer id){
        return leaveService.deleteById(id);
    }




    @RequestMapping(value = "/leaveList")
    public String leaveList(){
        return "/leave/leave_list";
    }

    @RequestMapping(value = "/leaveAdd")
    public String leaveAdd(Model model,HttpSession session){
        return "/leave/leave_add";
    }

    @RequestMapping(value = "/leaveView")
    public String leaveView(@RequestParam("id")Integer id,Model model){
        Leave leave=leaveService.findById(id);
        model.addAttribute("leave",leave);
        return "/leave/leave_view";
    }

    @RequestMapping(value = "/leaveUpdate")
    public String leaveUpdate(@RequestParam("id")Integer id,Model model){
        Leave leave=leaveService.findById(id);
        model.addAttribute("leave",leave);
        return "/leave/leave_update";
    }




}
