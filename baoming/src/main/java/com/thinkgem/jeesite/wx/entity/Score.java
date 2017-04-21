/**
 *
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.wx.constant.ConStant;

/**
 */
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    private long userId;
    private int type;
    private int score;
    private Date createTime;
    private User user;
    private String question;
    public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	private String typename;
    private String scoreResult;

    public Date getCreateTime() {
        return createTime;
    }

    public int getScore() {
        return score;
    }

    public String getScoreResult() {
    	String[] strings;
    	switch (type) {
		case 1:
			strings= ConStant.scoreResult[score];
	        if ("good".equals(strings[1])) {
	            return "好";
	        }
	        return "差";
		case 2:
			strings = ConStant.scoreResult2[score];
	        if ("good".equals(strings[1])) {
	            return "较低";
	        }else if("normal".equals(strings[1])) {
	            return "一般";
	        }else if("littlebad".equals(strings[1])) {
	            return "较高";
	        }
	        return "非常高";
		default:
			break;
		}
        return "";
    }

    public int getType() {
        return type;
    }

    public String getTypename() {
        switch (type) {
        case 1:
            return "颈椎病";
        case 2:
            return "癌症体质";
        case 3:
            return "亚健康";

        default:
            break;
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}