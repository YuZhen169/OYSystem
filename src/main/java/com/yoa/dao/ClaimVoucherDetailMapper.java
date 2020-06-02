package com.yoa.dao;


import com.yoa.entity.ClaimVoucherDetail;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ❤ on 2019/11/1.
 */
@Repository("claimVoucherDetailMapper")
public interface ClaimVoucherDetailMapper {

    public Integer addClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail)throws Exception;

    public Integer updateClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail)throws Exception;

    public List<ClaimVoucherDetail> findByCondition(ClaimVoucherDetail claimVoucherDetail);

    public Integer deleteClaimVoucherDetail(@Param("id") Integer id);

    public Integer deleteClaimVoucherDetailById(@Param("id") Integer id);
}
