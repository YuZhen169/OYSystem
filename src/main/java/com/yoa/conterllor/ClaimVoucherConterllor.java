package com.yoa.conterllor;

import com.alibaba.fastjson.JSONObject;
import com.yoa.entity.CheckResult;
import com.yoa.entity.ClaimVoucher;
import com.yoa.entity.ClaimVoucherDetail;
import com.yoa.entity.Employee;
import com.yoa.service.CheckResultService;
import com.yoa.service.ClaimVoucherService;
import com.yoa.service.EmployeeService;
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

import static java.lang.Integer.parseInt;

/**
 * Created by ❤ on 2019/10/30.
 */
@Controller
@RequestMapping(value = "/claimVoucher")
public class ClaimVoucherConterllor {

    @Resource
    private ClaimVoucherService claimVoucherService;

    @Resource
    private CheckResultService checkResultService;

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/findByCondition")
    @ResponseBody
    public JSONObject findByCondition(ClaimVoucher claimVoucher, HttpSession session,
                                      @RequestParam (value="page") String pageNo,
                                          @RequestParam (value="limit") String pageSize){
        JSONObject json = new JSONObject();
        Employee employee=(Employee)session.getAttribute("employee");
        List<ClaimVoucher> claimVoucherList=new ArrayList<>();

        Integer count=0;
        int page=Integer.parseInt(pageNo);
        int size=Integer.parseInt(pageSize);
        page=(page-1)*size;

        System.out.println("分页:"+pageNo+","+pageSize);

        json.put("msg", "success");
        json.put("code", "0");

        if (null!=employee){
            try {
                if (employee.getPositionId()==2){
                    claimVoucher.setNextDealSn(employee.getSn());
                }else if(employee.getPositionId()==1){

                    claimVoucher.setCreateSn(employee.getSn());

                    claimVoucherList=claimVoucherService.findByCondition(page,size,claimVoucher);

                    for (ClaimVoucher claimVoucher1:claimVoucherList) {
                        if (claimVoucher1.getNextDealSn()!=null){
                            claimVoucher1.setNextName(employeeService.findBySn(claimVoucher1.getNextDealSn()).getName());
                        }
                    }
                    count=claimVoucherService.count(claimVoucher);

                    json.put("count", count);
                    json.put("data", claimVoucherList);

                    return json;

                }else if (employee.getPositionId()==3){
                    claimVoucher.setNextDealSn(employee.getSn());
                }else if(employee.getPositionId()==4){
                    claimVoucher.setNextDealSn(employee.getSn());
                }
                System.out.println(claimVoucher);
                claimVoucherList=claimVoucherService.findByCondition(page,size,claimVoucher);

                for (ClaimVoucher claimVoucher1:claimVoucherList) {
                    if (claimVoucher1.getNextDealSn()!=null){
                        claimVoucher1.setSnName(employeeService.findBySn(claimVoucher1.getCreateSn()).getName());
                        claimVoucher1.setNextName(employeeService.findBySn(claimVoucher1.getNextDealSn()).getName());
                    }
                }

                count=claimVoucherService.count(claimVoucher);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        json.put("data", claimVoucherList);
        json.put("count", count);
        return json;
    }

    @RequestMapping(value = "/findByTimeAndStatus")
    @ResponseBody
    public JSONObject findByTimeAndStatus(@RequestParam("status") String status,
                                                  @RequestParam("startTime") String startTime,
                                                  @RequestParam("endTime") String endTime,
                                                  @RequestParam (value="page") String pageNo,
                                                  @RequestParam (value="limit") String pageSize,
                                                  HttpSession session){
        JSONObject json = new JSONObject();
        Employee employee=(Employee)session.getAttribute("employee");
        List<ClaimVoucher> claimVoucherList=new ArrayList<ClaimVoucher>();
        int count=0;

        if (null!=employee){
            int page=Integer.parseInt(pageNo);
            int size=Integer.parseInt(pageSize);
            page=(page-1)*size;

            System.out.println("分页:"+pageNo+","+pageSize);
            System.out.println("status"+status);
            System.out.println("startTime:"+startTime);
            System.out.println("endTime:"+endTime);

            json.put("msg", "success");
            json.put("code", "0");

            if("请选择".equals(status)){
                status=null;
            }
            if (null!=startTime || !"".equals(startTime)){
                if (null==endTime || "".equals(endTime)){
                    endTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                }
            }
            if (null!=endTime || !"".equals(endTime)) {
                if (null == startTime || "".equals(startTime)) {
                    startTime = claimVoucherService.minCreateTime();
                }
            }
            try {
                if (1==employee.getPositionId()){
                    claimVoucherList=claimVoucherService.findByTimeAndStatus(employee.getSn(),null,status,startTime,endTime,page,size);
                    count=claimVoucherService.countByTimeAndStatus(employee.getSn(),null,status,startTime,endTime);
                    for (ClaimVoucher claimVoucher1:claimVoucherList) {
                        if (claimVoucher1.getNextDealSn()!=null) {
                            claimVoucher1.setNextName(employeeService.findBySn(claimVoucher1.getNextDealSn()).getName());
                        }
                    }
                    json.put("count", count);
                    json.put("data", claimVoucherList);
                    return json;
                }
                claimVoucherList=claimVoucherService.findByTimeAndStatus(null,employee.getSn(),status,startTime,endTime,page,size);
                count=claimVoucherService.countByTimeAndStatus(null,employee.getSn(),status,startTime,endTime);
                for (ClaimVoucher claimVoucher1:claimVoucherList) {
                    if (claimVoucher1.getNextDealSn()!=null) {
                        claimVoucher1.setSnName(employeeService.findBySn(claimVoucher1.getCreateSn()).getName());
                        claimVoucher1.setNextName(employeeService.findBySn(claimVoucher1.getNextDealSn()).getName());
                    }
                }
                json.put("count", count);
                json.put("data", claimVoucherList);
                return json;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        json.put("count", count);
        json.put("data", claimVoucherList);
        return json;
    }

    @RequestMapping(value = "/addClaimVoucher")
    public String addClaimVoucher(ClaimVoucher claimVoucher,
                                  HttpSession session,Model model){
        Employee employee=(Employee)session.getAttribute("employee");
        String flag="";
        if (null!=employee){
            if("待审核".equals(claimVoucher.getStatus())){
                Employee employee1=new Employee();
                employee1.setDepartmentId(employee.getDepartmentId());
                employee1.setStatus("在职");
                employee1.setPositionId(2);
                try {
                    List<Employee> employeeList=employeeService.findByCondition(employee1);
                    for (Employee emp :employeeList) {
                        claimVoucher.setNextDealSn(emp.getSn());
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                claimVoucher.setNextDealSn(employee.getSn());
            }

            claimVoucher.setCreateSn(employee.getSn());
            claimVoucher.setCreateTime(new Date());
            System.out.println(claimVoucher);
            try {
                if (claimVoucherService.addClaimVoucher(claimVoucher)>0){
                    if ("待审核".equals(claimVoucher.getStatus())){
                        flag="提交成功";
                    }else{
                        flag="保存成功";
                    }
                }else{
                    if ("待审核".equals(claimVoucher.getStatus())){
                        flag="提交失败";
                    }else{
                        flag="保存失败";
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        model.addAttribute("flag",flag);
        System.out.println(flag);
        return "forward:/claimVoucher/claimVoucherAdd";
    }

    @RequestMapping(value = "/updateClaimVoucher")
    @ResponseBody
    public String updateClaimVoucher(ClaimVoucher claimVoucher,HttpSession session,Model model){
        Employee employee=(Employee)session.getAttribute("employee");
        try {
            if ("待审核".equals(claimVoucher.getStatus())){
                Employee employee1=new Employee();
                employee1.setDepartmentId(employee.getDepartmentId());
                employee1.setStatus("在职");
                employee1.setPositionId(2);
                List<Employee> employeeList=employeeService.findByCondition(employee1);
                for (Employee emp :employeeList) {
                    claimVoucher.setNextDealSn(emp.getSn());
                    break;
                }
            }else{
                claimVoucher.setNextDealSn(employee.getSn());
            }
            claimVoucher.setModifyTime(new Date());
            System.out.println("claimVoucher:"+claimVoucher);
            if (claimVoucherService.updateClaimVoucher(claimVoucher)>0){
                System.out.println("修改成功");
                return claimVoucher.getStatus();
            }else{
                System.out.println("修改失败");
                return "no";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "no";
    }

    @RequestMapping(value="/auditClaimVoucher")
    @ResponseBody
    public String auditClaimVoucher(CheckResult checkResult,
                                    @RequestParam("sn")String sn,
                                    @RequestParam("totalAccount")String totalAccount,
                                    HttpSession session, Model model){
        double totalAccounts=0;
        if (null!=totalAccount){
            try {
                totalAccounts=Double.parseDouble(totalAccount);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        System.out.println("sn"+sn);
        Employee employee=(Employee)session.getAttribute("employee");
        Employee employee1=new Employee();
        ClaimVoucher claimVoucher=new ClaimVoucher();
        claimVoucher.setId(checkResult.getClaimId());
        claimVoucher.setModifyTime(new Date());
        try {
            if ("通过".equals(checkResult.getResult())){
                if (employee.getPositionId()==2){
                    claimVoucher.setStatus("已审核");
                    if (500<=totalAccounts){
                        employee1.setStatus("在职");
                        employee1.setPositionId(3);
                    }else{
                        employee1.setStatus("在职");
                        employee1.setPositionId(4);
                    }
                    List<Employee> employeeList=employeeService.findByCondition(employee1);
                    for (Employee emp :employeeList) {
                        claimVoucher.setNextDealSn(emp.getSn());
                        break;
                    }
                }else if (employee.getPositionId()==3){
                    claimVoucher.setStatus("已审核");
                    employee1.setStatus("在职");
                    employee1.setPositionId(4);
                    List<Employee> employeeList=employeeService.findByCondition(employee1);
                    for (Employee emp :employeeList) {
                        claimVoucher.setNextDealSn(emp.getSn());
                        break;
                    }
                }else if (employee.getPositionId()==4){
                    claimVoucher.setStatus("已付款");
                    claimVoucher.setNextDealSn(sn);
                }
            }else if ("拒绝".equals(checkResult.getResult())){
                claimVoucher.setStatus("已拒绝");
            }else if("打回".equals(checkResult.getResult())){
                claimVoucher.setStatus("已打回");
                claimVoucher.setNextDealSn(sn);
            }
            System.out.println(claimVoucher);
            checkResult.setCheckTime(new Date());
            checkResult.setCheckerSn(employee.getSn());
            if (claimVoucherService.auditClaimVoucher(claimVoucher)>0){
                if (checkResultService.addCheckResult(checkResult)>0){
                    System.out.println("审核成功!");
                    return checkResult.getResult();
                }else{
                    System.out.println("审核失败!");
                    return "no";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "no";
    }

    @RequestMapping(value = "/deleteClaimVoucher")
    @ResponseBody
    public String deleteClaimVoucher(@RequestParam("id")Integer id){
        System.out.println(id);
        try {
            if (claimVoucherService.deleteClaimVoucher(id)>0){
                return "ok";
            }
        }catch (Exception e){
           e.printStackTrace();
        }
        return "no";
    }

    @RequestMapping(value = "/claimVoucherList")
    public String claimVoucherList(){
        return "/claim/claim_voucher_list";
    }

    @RequestMapping(value = "/claimVoucherAdd")
    public String claimVoucherAdd(Model model,HttpSession session){
        Employee employee=(Employee)session.getAttribute("employee");
        if (null!=employee){
            model.addAttribute("name",employee.getName());
        }
        return "/claim/claim_voucher_add";
    }

    @RequestMapping(value = "/claimVoucherUpdate")
    public String claimVoucherUpdate(@RequestParam("id")Integer id,HttpSession session,Model model){
        Employee employee=(Employee)session.getAttribute("employee");
        try{
            if (null!=employee){
                Employee employee1=employeeService.findBySn(employee.getSn());
                ClaimVoucher claimVoucher=claimVoucherService.findById(id);
                claimVoucher.setNextName(employeeService.findBySn(claimVoucher.getNextDealSn()).getName());
                model.addAttribute("employee",employee1);
                model.addAttribute("claimVoucher",claimVoucher);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/claim/claim_voucher_update";
    }

    @RequestMapping(value = "/claimVoucherView")
    public String claimVoucherView(@RequestParam("id")Integer id,HttpSession session,Model model){
        List<CheckResult> checkResultList2=new ArrayList<>();
        List<CheckResult> checkResultList3=new ArrayList<>();
        List<CheckResult> checkResultList4=new ArrayList<>();
        Employee employee=new Employee();
        try{
            employee =(Employee)session.getAttribute("employee");
            if (null!=employee){
                ClaimVoucher claimVoucher=claimVoucherService.findById(id);
                Employee employee1=employeeService.findBySn(claimVoucher.getCreateSn());
                if (null!=claimVoucher.getNextDealSn()) {
                    claimVoucher.setNextName(employeeService.findBySn(claimVoucher.getNextDealSn()).getName());
                }else{
                    claimVoucher.setNextName("-");
                }
                if (3==employee.getPositionId()){

                    List<CheckResult> checkResultList=checkResultService.findByClaimId(id);
                    for (int i = 0; i < checkResultList.size(); i++) {
                        Employee employee2=employeeService.findBySn(checkResultList.get(i).getCheckerSn());
                        System.out.println(employee2);
                        if (employee2.getPositionId()==2){
                            checkResultList2.add(checkResultList.get(i));
                        }
                    }
                }

                if (4==employee.getPositionId() || 1==employee.getPositionId()){
                    List<CheckResult> checkResultList=checkResultService.findByClaimId(id);
                    for (int i = 0; i < checkResultList.size(); i++) {
                        Employee employee2=employeeService.findBySn(checkResultList.get(i).getCheckerSn());
                        System.out.println(employee2.getPositionId());
                        if (employee2.getPositionId()==2){
                            checkResultList2.add(checkResultList.get(i));
                        }
                        if (employee2.getPositionId()==3){
                            checkResultList3.add(checkResultList.get(i));
                        }
                        if (employee2.getPositionId()==4){
                            checkResultList4.add(checkResultList.get(i));
                        }
                    }
                }
                model.addAttribute("checkResultList2",checkResultList2);
                model.addAttribute("checkResultList3",checkResultList3);
                model.addAttribute("checkResultList4",checkResultList4);
                model.addAttribute("claimVoucher",claimVoucher);
                model.addAttribute("employee",employee1);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/claim/claim_voucher_view";
    }

    @RequestMapping(value = "/claimVoucherAudit")
    public String claimVoucherAudit(@RequestParam("id")Integer id,HttpSession session,Model model){
        List<CheckResult> checkResultList2=new ArrayList<>();
        List<CheckResult> checkResultList3=new ArrayList<>();
        Employee employee=(Employee)session.getAttribute("employee");
        try{
            if (null!=employee){
                ClaimVoucher claimVoucher=claimVoucherService.findById(id);
                Employee employee1=employeeService.findBySn(claimVoucher.getCreateSn());
                if (null!=claimVoucher.getNextDealSn()) {
                    claimVoucher.setNextName(employeeService.findBySn(claimVoucher.getNextDealSn()).getName());
                }else{
                    claimVoucher.setNextName("-");
                }
                if (3==employee.getPositionId()){
                    List<CheckResult> checkResultList=checkResultService.findByClaimId(id);
                    for (int i = 0; i < checkResultList.size(); i++) {
                        Employee employee2=employeeService.findBySn(checkResultList.get(i).getCheckerSn());
                        System.out.println(employee2);
                        if (employee2.getPositionId()==2){
                            checkResultList2.add(checkResultList.get(i));
                        }
                    }
                }

                if (4==employee.getPositionId()){
                    List<CheckResult> checkResultList=checkResultService.findByClaimId(id);
                    for (int i = 0; i < checkResultList.size(); i++) {
                        Employee employee2=employeeService.findBySn(checkResultList.get(i).getCheckerSn());
                        if (employee2.getPositionId()==2){
                            checkResultList2.add(checkResultList.get(i));
                        }
                        if (employee2.getPositionId()==3){
                            checkResultList3.add(checkResultList.get(i));
                        }
                    }
                }
                model.addAttribute("checkResultList2",checkResultList2);
                model.addAttribute("checkResultList3",checkResultList3);
                model.addAttribute("employee",employee1);
                model.addAttribute("claimVoucher",claimVoucher);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/claim/claim_voucher_audit";
    }





}
