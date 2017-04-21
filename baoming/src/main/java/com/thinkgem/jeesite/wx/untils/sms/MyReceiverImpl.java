package com.thinkgem.jeesite.wx.untils.sms;

import com.wisdom.sms.ssrp.Message;
import com.wisdom.sms.ssrp.SMReceiver;

public class MyReceiverImpl implements SMReceiver {

    @Override
    public boolean onReceiveAnswer(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onReceiveFail(int arg0, String arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onReceiveReport(int arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onReceiveSM(Message arg0) {
        // TODO Auto-generated method stub
        return false;
    }

}
