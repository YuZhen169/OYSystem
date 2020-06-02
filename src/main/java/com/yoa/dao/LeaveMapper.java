package com.yoa.dao;

import com.yoa.entity.Leave;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ❤ on 2019/11/4.
 */
@Repository("leaveMapper")
public interface LeaveMapper {

    public List<Leave> findByCondition(@Param("leave")Leave leave,@Param("pageNo")Integer pageNo,
                                       @Param("pageSize")Integer pageSize)throws Exception;


    public Integer countByCondition(@Param("leave")Leave leave,
                                    @Param("startTime") String startTime,
                                    @Param("endTime") String endTime)throws Exception;

    public List<Leave> findByTimeAndStatus(@Param("status") String status,
                                            @Param("startTime") String startTime,
                                            @Param("endTime") String endTime,
                                           @Param("sn")String sn,
                                           @Param("next")String next,
                                           @Param("pageNo")Integer pageNo,
                                           @Param("pageSize")Integer pageSize)throws Exception;

    //根据id查询请假单
    public Leave findById(@Param("id")Integer id)throws Exception;

    //添加请假单
    public Integer addLeave(Leave leave) throws Exception;

    //删除请假单
    public Integer deleteById(@Param("id")Integer id)throws Exception;


}
