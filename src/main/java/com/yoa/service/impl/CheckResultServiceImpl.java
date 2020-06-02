package com.yoa.service.impl;

import com.yoa.dao.CheckResultMapper;
import com.yoa.entity.CheckResult;
import com.yoa.service.CheckResultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ❤ on 2019/11/20.
 */
@Service
public class CheckResultServiceImpl implements CheckResultService{

    @Resource
    private CheckResultMapper checkResultMapper;

    @Override
    public Integer addCheckResult(CheckResult checkResult) {
        return checkResultMapper.addCheckResult(checkResult);
    }

    @Override
    public List<CheckResult> findByClaimId(Integer claimId) {
        return checkResultMapper.findByClaimId(claimId);
    }

    @Override
    public Integer deleteByMainId(Integer claimId) {
        return checkResultMapper.deleteByMainId(claimId);
    }


}
