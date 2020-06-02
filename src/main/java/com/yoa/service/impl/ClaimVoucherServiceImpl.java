package com.yoa.service.impl;

import com.yoa.dao.ClaimVoucherDetailMapper;
import com.yoa.dao.ClaimVoucherMapper;
import com.yoa.entity.ClaimVoucher;
import com.yoa.entity.ClaimVoucherDetail;
import com.yoa.service.CheckResultService;
import com.yoa.service.ClaimVoucherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ❤ on 2019/10/30.
 */
@Service("claimVoucherService")
public class ClaimVoucherServiceImpl implements ClaimVoucherService{

    @Resource
    private ClaimVoucherMapper claimVoucherMapper;

    @Resource
    private CheckResultService checkResultService;

    @Resource
    private ClaimVoucherDetailMapper claimVoucherDetailMapper;


    @Override
    public List<ClaimVoucher> findByCondition(Integer pageNo, Integer pageSize, ClaimVoucher claimVoucher) throws Exception {
        return claimVoucherMapper.findByCondition(claimVoucher,pageNo,pageSize);
    }

    @Override
    public List<ClaimVoucher> findByTimeAndStatus(String sn,String next,String status, String startTime, String endTime, Integer pageNo, Integer pageSize) throws Exception {
        return claimVoucherMapper.findByTimeAndStatus(sn,next,status,startTime,endTime,pageNo,pageSize);
    }

    @Override
    public Integer addClaimVoucher(ClaimVoucher claimVoucher)   {
        Integer result=0;
        try {
            if (claimVoucherMapper.addClaimVoucher(claimVoucher)>0){
                Integer id=claimVoucherMapper.findId();
                for (ClaimVoucherDetail claimVoucherDetail:claimVoucher.getDetailList()) {
                    claimVoucherDetail.setMainId(id);
                    result=claimVoucherDetailMapper.addClaimVoucherDetail(claimVoucherDetail);
                }
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer count(ClaimVoucher claimVoucher) throws Exception {
        return claimVoucherMapper.count(claimVoucher);
    }

    @Override
    public String minCreateTime() {
        return claimVoucherMapper.minCreateTime();
    }

    @Override
    public Integer countByTimeAndStatus(String sn,String next, String status, String startTime, String endTime) throws Exception {
        return claimVoucherMapper.countByTimeAndStatus(sn,next,status,startTime,endTime);
    }


    @Override
    public ClaimVoucher findById(Integer id)throws Exception {
        return claimVoucherMapper.findById(id);
    }

    @Override
    public Integer updateClaimVoucher(ClaimVoucher claimVoucher){
        Integer result=0;
        ClaimVoucherDetail claimVoucherDetail=new ClaimVoucherDetail();
        claimVoucherDetail.setMainId(claimVoucher.getId());
        List<ClaimVoucherDetail> claimVoucherDetailList=claimVoucherDetailMapper.findByCondition(claimVoucherDetail);
        try {

            for (ClaimVoucherDetail cvd2:claimVoucherDetailList) {
                int i=claimVoucherDetailList.size();
                for (ClaimVoucherDetail cvd:claimVoucher.getDetailList()) {
                    if (null==cvd.getId()){
                        cvd.setMainId(claimVoucher.getId());
                        claimVoucherDetailMapper.addClaimVoucherDetail(cvd);
                    }else{

                        claimVoucherDetailMapper.updateClaimVoucherDetail(cvd);
                    }
                    System.out.println("原"+cvd2.getId()+",新"+cvd.getId()+",i:"+i);
                    if (cvd2.getId().equals(cvd.getId())){
                        break;
                    }
                    i--;
                    if (i==0){
                        System.out.println(i+","+cvd2.getId());
                        claimVoucherDetailMapper.deleteClaimVoucherDetailById(cvd2.getId());
                    }
                }
            }
            result=claimVoucherMapper.updateClaimVoucher(claimVoucher);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer auditClaimVoucher(ClaimVoucher claimVoucher) throws Exception {
        return claimVoucherMapper.auditClaimVoucher(claimVoucher);
    }

    @Override
    public Integer deleteClaimVoucher(Integer id) {
        Integer result=0;
        try {
            System.out.println("claimVoucherDetailMapper"+claimVoucherDetailMapper.deleteClaimVoucherDetail(id));
            System.out.println("checkResultService"+checkResultService.deleteByMainId(id));
            result=claimVoucherMapper.deleteClaimVoucher(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer findByAuditCount(String nextDealSn, String status)throws Exception {
        return claimVoucherMapper.findByAuditCount(nextDealSn,status);
    }


}
