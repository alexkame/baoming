/**
 */
package com.thinkgem.jeesite.wx.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.wx.dao.NciUserDao;
import com.thinkgem.jeesite.wx.dao.ScoreDao;
import com.thinkgem.jeesite.wx.entity.Score;
import com.thinkgem.jeesite.wx.entity.User;

/**
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService {

    @Autowired
    private NciUserDao nciUserDao;
    @Autowired
    private ScoreDao scoreDao;

    @Transactional(readOnly = false)
    public User add(String openid, Long salesManId, Long repeatUserId) {
        User user = nciUserDao.getByOpenid(openid, true);
        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            if (repeatUserId == null) {
                user.setType(0);
            } else {
                User repeatUser = nciUserDao.get(repeatUserId + "");
                if (repeatUser != null) {
                    user.setRepeatUserId(repeatUserId);
                    user.setType(1);
                } else {
                    repeatUserId = null;
                    user.setType(0);
                }
            }

            user.setSalesManId(salesManId);
            user.setCreateTime(new Date());
            nciUserDao.insert(user);
            user = nciUserDao.getByOpenid(openid, false);
        }
        return user;
    }

    /**
     * 根据id查询
     *
     * @param openid
     * @return
     */
    public User getById(String id) {
        return nciUserDao.get(id);
    }

    /**
     * 根据微信openid查询
     *
     * @param openid
     * @return
     */
    public User getByOpenid(String openid) {
        return nciUserDao.getByOpenid(openid, false);
    }

    @Cacheable(value = "wxCache", key = "#root.methodName+#nciMoney")
    public List<User> getBySalmesManId(long salesManId) {
        return nciUserDao.getBySalmesManId(salesManId);
    }

    public Score getByUserIdAndType(Long userId, int type) {
        return scoreDao.getByUserIdAndType(userId, type);
    }

    @Cacheable(value = "wxCache", key = "#root.methodName")
    public long getCount() {
        return nciUserDao.getCount();
    }

    /**
     * 查询大于某新华币人数
     *
     * @return
     */
    @Cacheable(value = "wxCache", key = "#root.methodName+#nciMoney")
    public long getCountByNciMoneny(long nciMoney) {
        return nciUserDao.getCountByNciMoneny(nciMoney);
    }

    /**
     * 评测得分
     *
     * @param userId
     */
    @Transactional(readOnly = false)
    public void score(Long userId, int type, int score, String question) {
        Score s = scoreDao.getByUserIdAndType(userId, type);
        if (s == null) {
            s = new Score();
            s.setUserId(userId);
            s.setType(type);
            s.setScore(score);
            s.setCreateTime(new Date());
            s.setQuestion(question);
            scoreDao.insert(s);
        } else {
            scoreDao.updateScore(userId, type, score, question);
        }
    }

    /**
     * 更新用户
     *
     * @param id
     * @param phone
     * @param realName
     * @return
     */
    @Transactional(readOnly = false)
    public int updateNameAndPhone(long id, String phone, String realName) {
        return nciUserDao.updateNameAndPhone(id, phone, realName);
    }

    @Transactional(readOnly = false)
    public int updateSexAndAge(long id, String sex, int age) {
        return nciUserDao.updateSexAndAge(id, sex, age);
    }

    @Transactional(readOnly = false)
    public int updateUserInfo(long id, String realName, String phone, String sex, String city,
            String childrenName, Integer childrenClass) {
        return nciUserDao.updateUserInfo(id, realName, phone, sex, city, childrenName,
                childrenClass);

    }
}
