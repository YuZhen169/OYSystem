package com.yoa.service;

import com.yoa.entity.ClaimVoucherDetail;

import java.util.List;

/**
 * Created by ❤ on 2019/11/2.
 */
public interface ClaimVoucherDetailService {

    public Integer addClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail)throws Exception;

    public Integer updateClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail)throws Exception;

    public List<ClaimVoucherDetail> findByCondition(ClaimVoucherDetail claimVoucherDetail);

    public Integer deleteClaimVoucherDetail(Integer id);

    public Integer deleteClaimVoucherDetailById(Integer id);
}
