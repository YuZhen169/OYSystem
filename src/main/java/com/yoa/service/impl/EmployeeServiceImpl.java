package com.yoa.service.impl;

import com.yoa.dao.EmployeeMapper;
import com.yoa.entity.Employee;
import com.yoa.service.EmployeeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by ❤ on 2019/10/29.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(@Param("name") String name, @Param("password") String password) throws Exception{
        return employeeMapper.login(name,password);
    }

    @Override
    public Employee findBySn(@Param("sn") String sn) throws Exception{
        return employeeMapper.findBySn(sn);
    }

    @Override
    public List<Employee> findByCondition(Employee employee){
        List<Employee> employeeList=null;
        try {
            employeeList=employeeMapper.findByCondition(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }


}
