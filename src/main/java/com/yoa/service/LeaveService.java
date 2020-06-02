package com.yoa.service;

import com.yoa.entity.Leave;

import java.util.List;

/**
 * Created by ❤ on 2019/11/4.
 */
public interface LeaveService {

    public List<Leave> findByCondition(Leave leave,Integer pageNo,
                                       Integer pageSize);

    public Integer countByCondition(Leave leave,
                                    String startTime,
                                   String endTime);

    public List<Leave> findByTimeAndStatus(String status,
                                           String startTime,
                                           String endTime,
                                           String sn,
                                           String next,
                                           Integer pageNo,
                                           Integer pageSize);

    public Leave findById(Integer id);

    public Integer addLeave(Leave leave);

    //删除请假单
    public Integer deleteById(Integer id);

}
