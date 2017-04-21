/**
 */
package com.thinkgem.jeesite.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.wx.dao.VoteLogDao;
import com.thinkgem.jeesite.wx.entity.VoteLog;

/**
 */
@Service
@Transactional(readOnly = true)
public class VoteService extends BaseService {

    @Autowired
    private VoteLogDao voteLogDao;

    /**
     * 获取今天投票记录
     * 
     * @param userId
     * @return
     */
    public VoteLog getTodayByUserId(long userId) {
        return voteLogDao.getTodayByUserId(userId, false);
    }

}
