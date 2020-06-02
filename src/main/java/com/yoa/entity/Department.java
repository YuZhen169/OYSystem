package com.yoa.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Created by ❤ on 2019/10/30.
 */
@Data
@Table(name = "sys_department")
public class Department {

    private Integer id;
    private String name;
}
