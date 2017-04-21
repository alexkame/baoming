package com.thinkgem.jeesite.wx.untils;

import java.util.Date;

import com.thinkgem.jeesite.wx.constant.ConStant;

public class NciUtils {
	
	
	/**
	 * 距离活动结束时间 单位为毫秒
	 * @return
	 */
	public static long getTimesEnd(){
		long time = 0;
		Date now = new Date();
		time = ConStant.LOTTERY_ENDDATE.getTime()-now.getTime();
		return time;
	}
}
