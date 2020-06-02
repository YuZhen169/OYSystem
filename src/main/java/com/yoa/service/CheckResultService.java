package com.yoa.service;

import com.yoa.entity.CheckResult;

import java.util.List;

/**
 * Created by ❤ on 2019/11/20.
 */
public interface CheckResultService {

    public Integer addCheckResult(CheckResult checkResult);

    public List<CheckResult> findByClaimId(Integer claimId);

    public Integer deleteByMainId(Integer claimId);
}
