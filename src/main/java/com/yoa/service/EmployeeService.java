package com.yoa.service;

import com.yoa.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by ❤ on 2019/10/29.
 */
public interface EmployeeService {

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    public Employee login(@Param("name") String name, @Param("password") String password) throws  Exception;

    /**
     * 根据ID查询名称
     * @param sn
     * @return
     */
    public Employee findBySn(@Param("sn") String sn) throws Exception;

    public List<Employee> findByCondition(Employee employee);

}
