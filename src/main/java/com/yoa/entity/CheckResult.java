package com.yoa.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.*;

/**
 * Created by ❤ on 2019/11/20.
 */
@Data
@Table(name="biz_check_result")
public class CheckResult {

    private Integer id;
    private Integer claimId;
    private Date checkTime;
    private String checkerSn;
    private String result;
    private String comm;

    private Employee employee;

}
