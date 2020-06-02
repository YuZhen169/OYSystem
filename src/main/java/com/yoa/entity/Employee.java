package com.yoa.entity;

import lombok.Data;

import javax.persistence.Table;


/**
 * Created by ❤ on 2019/10/29.
 */
@Data
@Table(name = "sys_employee")
public class Employee {
    private String sn;
    private Integer positionId;
    private Integer departmentId;
    private String name;
    private String password;
    private String status;

    private Department department;
    private Position position;

}
