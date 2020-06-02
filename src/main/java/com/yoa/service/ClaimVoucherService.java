package com.yoa.service;

import com.yoa.entity.ClaimVoucher;


import java.util.List;

/**
 * Created by ❤ on 2019/10/30.
 */
public interface ClaimVoucherService {

    public List<ClaimVoucher> findByCondition(Integer pageNo,
                                              Integer pageSize,
                                              ClaimVoucher claimVoucher)throws Exception;

    public List<ClaimVoucher> findByTimeAndStatus(String sn,
                                                  String next,
                                                  String status,
                                                  String startTime,
                                                  String endTime,
                                                  Integer pageNo,
                                                  Integer pageSize)throws Exception;

    public Integer addClaimVoucher(ClaimVoucher claimVoucher)throws Exception;

    public Integer count(ClaimVoucher claimVoucher)throws Exception;

    public String minCreateTime();

    public Integer countByTimeAndStatus(String sn,
                                        String next,
                                        String status,
                                        String startTime,
                                        String endTime)throws Exception;
    public ClaimVoucher findById(Integer id)throws Exception;

    public Integer updateClaimVoucher(ClaimVoucher claimVoucher)throws Exception;

    public Integer auditClaimVoucher(ClaimVoucher claimVoucher)throws Exception;

    public Integer deleteClaimVoucher(Integer id);

    public Integer findByAuditCount(String nextDealSn,String status)throws Exception;

}
