package com.thinkgem.jeesite.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.thinkgem.jeesite.wx.untils.sms.SmsUtil;

public class Test {

    public static void main(String[] args) throws IOException {
        // int legth =
        // "lsm:3:1:尊敬的18612345678用户，恭喜您在新华保险创业天使E起来活动中获得幸运大奖：苹果iwatch一台，您所投票的营销员将与您联系，并将奖品送到您手中，本条短信为您的兑奖凭证，请妥善保管。新华保险健康无忧产品正火热销售，欢迎咨询！新华保险24小时服务热线95567。"
        // .length();

        File file = new File("E:/workspace/nci/src/test/java/com/thinkgem/jeesite/test/phones.list");
        List<String> phones = FileUtils.readLines(file);

        int index = 3;
        for (String phone : phones) {
            int i = SmsUtil
                    .send(phone,
                            1,
                            "lsm:"
                                    + 3
                                    + ":1:尊敬的"
                                    + phone
                                    + "用户，感谢您参与新华保险创业天使E起来活动，恭喜您在12月7日—12月26日的 “新华币”排名进入前100名，并获得500M流量卡一张。请于2016年1月31日前，前往营销员投票界面的抽奖环节点击领奖！充值问题请咨询4001076866。");
            System.out.println(index + "_" + phone + ":" + i);
            index++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // System.out.print( RegexUtils.isValidName("帕提古丽热合吐拉合吐拉合吐拉合吐拉"));
    }
}
