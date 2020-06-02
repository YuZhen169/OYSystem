package com.yoa.service.impl;

import com.yoa.dao.ClaimVoucherDetailMapper;
import com.yoa.entity.ClaimVoucherDetail;
import com.yoa.service.ClaimVoucherDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ❤ on 2019/11/2.
 */
@Service("claimVoucherDetailService")
public class ClaimVoucherDetailServiceImpl implements ClaimVoucherDetailService{

    @Resource
    private ClaimVoucherDetailMapper claimVoucherDetailMapper;

    @Override
    public Integer addClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) throws Exception {
        return claimVoucherDetailMapper.addClaimVoucherDetail(claimVoucherDetail);
    }

    @Override
    public Integer updateClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) throws Exception {
        return claimVoucherDetailMapper.updateClaimVoucherDetail(claimVoucherDetail);
    }

    @Override
    public List<ClaimVoucherDetail> findByCondition(ClaimVoucherDetail claimVoucherDetail) {
        return claimVoucherDetailMapper.findByCondition(claimVoucherDetail);
    }

    @Override
    public Integer deleteClaimVoucherDetail(Integer id) {
        return claimVoucherDetailMapper.deleteClaimVoucherDetail(id);
    }

    @Override
    public Integer deleteClaimVoucherDetailById(Integer id) {
        return claimVoucherDetailMapper.deleteClaimVoucherDetailById(id);
    }


}
