package com.yoa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by ❤ on 2019/11/4.
 */
@Data
@Table(name="biz_leave")
public class Leave{

    private Integer id;
    private String employeeSn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Double leaveDay;
    private String reason;
    private String status;
    private String leaveType;
    private String nextDealSn;
    private String approveOpinion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;

    private Employee createEmp;
    private Employee nextEmp;

}
