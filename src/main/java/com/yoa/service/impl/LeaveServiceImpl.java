package com.yoa.service.impl;

import com.yoa.dao.LeaveMapper;
import com.yoa.entity.Leave;
import com.yoa.service.LeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ❤ on 2019/11/4.
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService{

    @Resource
    private LeaveMapper leaveMapper;

    @Override
    public List<Leave> findByCondition(Leave leave,Integer pageNo,Integer pageSize) {
        List<Leave> leaveList=null;
        try {
            leaveList=leaveMapper.findByCondition(leave,(pageNo-1)*pageSize,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public Integer countByCondition(Leave leave, String startTime, String endTime) {
        Integer result=0;
        try {
            result=leaveMapper.countByCondition(leave,startTime,endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Leave> findByTimeAndStatus(String status, String startTime, String endTime, String sn, String next, Integer pageNo, Integer pageSize) {
        List<Leave> leaveList=null;
        try {
            leaveList=leaveMapper.findByTimeAndStatus(status,startTime,endTime,sn,next,(pageNo-1)*pageSize,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaveList;
    }

    @Override
    public Leave findById(Integer id) {
        Leave leave=null;
        try {
            leave=leaveMapper.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leave;
    }

    @Override
    public Integer addLeave(Leave leave){
        Integer result=0;
        try {
            result=leaveMapper.addLeave(leave);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer deleteById(Integer id) {
        Integer result=0;
        try {
            result=leaveMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
