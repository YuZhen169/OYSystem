package com.yoa.dao;

import com.yoa.entity.ClaimVoucher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ❤ on 2019/10/30.
 */
@Repository("claimVoucherMapper")
public interface ClaimVoucherMapper {

    public List<ClaimVoucher> findByCondition(@Param("claimVoucher")ClaimVoucher claimVoucher,@Param("pageNo")Integer pageNo,
                                              @Param("pageSize")Integer pageSize
                                              )throws Exception;

    public Integer count(ClaimVoucher claimVoucher)throws Exception;

    public Integer countByTimeAndStatus(@Param("sn")String sn,
                                        @Param("next")String next,
                                        @Param("status") String status,
                                        @Param("startTime") String startTime,
                                        @Param("endTime") String endTime)throws Exception;

    public List<ClaimVoucher> findByTimeAndStatus(@Param("sn")String sn,
                                                  @Param("next")String next,
                                                  @Param("status") String status,
                                                  @Param("startTime") String startTime,
                                                  @Param("endTime") String endTime,
                                                  @Param("pageNo")Integer pageNo,
                                                  @Param("pageSize")Integer pageSize
                                                  )throws Exception;

    public Integer addClaimVoucher(ClaimVoucher claimVoucher)throws Exception;

    public Integer findId()throws Exception;

    public String minCreateTime();

    public ClaimVoucher findById(@Param("id") Integer id);

    public Integer updateClaimVoucher(ClaimVoucher claimVoucher)throws Exception;

    public Integer auditClaimVoucher(ClaimVoucher claimVoucher)throws Exception;

    public Integer deleteClaimVoucher(@Param("id") Integer id)throws Exception;

    public Integer findByAuditCount(@Param("nextDealSn")String nextDealSn,@Param("status")String status)throws  Exception;


}
