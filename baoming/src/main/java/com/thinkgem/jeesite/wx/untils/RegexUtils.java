package com.thinkgem.jeesite.wx.untils;

import org.apache.commons.lang3.StringUtils;

public class RegexUtils {

    /**
     * 字符串包含
     * 
     * @return
     */
    public static boolean contains(String str, String regex) {
        if (str == null || regex == null) {
            return false;
        }

        return str.matches(regex);
    }

    /**
     * 逗号分隔的正则表达式
     * 
     * @param str
     * @return
     */
    public static String getCommaSparatedRegex(String str) {
        if (str == null) {
            return null;
        }

        return "^(" + str + ")|([\\s\\S]*," + str + ")|(" + str + ",[\\s\\S]*)|([\\s\\S]*," + str
                + ",[\\s\\S]*)$";
    }

    /**
     * 根据手机号返回运营商 1 移动 2联通3电信 0 未知
     * 
     * @param phone
     * @return
     */
    public static int getMobileComByPhone(String phone) {
        int type = 0;
        String yds = "134/135/136/137/138/139/150/151/152/157/158/159/182/183/184/187/188/147/178";
        String lts = "130/131/132/155/156/185/186/145/176";
        String dxs = "133/153/180/181/189/177";

        if (phone != null && phone.length() == 11) {
            String comNum = phone.substring(0, 3);
            if (yds.indexOf(comNum) >= 0) {
                return 1;
            }
            if (lts.indexOf(comNum) >= 0) {
                return 2;
            }
            if (dxs.indexOf(comNum) >= 0) {
                return 3;
            }

            String yds4 = "1705";
            String lts4 = "1709/1707/1708";
            String dxs4 = "1700/1701/1702";
            String comNum4 = phone.substring(0, 4);

            if (yds4.indexOf(comNum4) >= 0) {
                return 1;
            }
            if (lts4.indexOf(comNum4) >= 0) {
                return 2;
            }
            if (dxs4.indexOf(comNum4) >= 0) {
                return 3;
            }
        }
        return type;

    }

    /**
     * 是否为16,19或者22位银行账号
     * 
     * @param bankAccount
     * @return
     */
    public static boolean isBankAccount(String bankAccount) {
        if (null == bankAccount) {
            return false;
        }

        return bankAccount.matches("^(\\d{19}|\\d{16}|\\d{22})$");
    }

    /**
     * 是否日期yyyy-mm-dd(yyyy/mm/dd)
     * 
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        if (null == date) {
            return false;
        }
        return date
                .matches("^([1-2]\\d{3})[\\/|\\-](0?[1-9]|10|11|12)[\\/|\\-]([1-2]?[0-9]|0[1-9]|30|31)$");
    }

    /**
     * 是否有效邮箱
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email) {
            return false;
        }

        return email
                .matches("^([a-zA-Z0-9])+([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
    }

    /**
     * 是否整数
     * 
     * @param number
     * @return
     */
    public static boolean isInt(String number) {
        if (null == number) {
            return false;
        }

        return number.matches("^[+-]?(([1-9]{1}\\d*)|([0]{1}))$");
    }

    // public static boolean isValidPassword(String password) {
    // if (null == password) {
    // return false;
    // }
    //
    // return password.matches("[a-zA-Z\\d]{6,20}");
    // }
    /**
     * 是否有效手机号码
     * 
     * @param mobileNum
     * @return
     */
    public static boolean isMobileNum(String mobileNum) {
        if (null == mobileNum) {
            return false;
        }

        return mobileNum
                .matches("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,6,7,8])|(18[0-9]))(\\d{8})$");
    }

    /**
     * 是否数字(小数||整数)
     * 
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        if (null == number) {
            return false;
        }

        return number.matches("^[+-]?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d)+)?$");
    }

    /**
     * 是否正整数
     * 
     * @param number
     * @return
     */
    public static boolean isPositiveInt(String number) {
        if (null == number) {
            return false;
        }

        return number.matches("^[+-]?(([1-9]{1}\\d*)|([0]{1}))$");
    }

    /**
     * 是否是QQ邮箱
     */
    public static boolean isQQEmail(String email) {
        if (null == email) {
            return false;
        }

        return email.matches("^[\\s\\S]*@qq.com$");
    }

    /**
     * 中文姓名
     * 
     * @return
     */
    public static boolean isValidName(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }

        return username.matches("^([\u4E00-\u9FA5]|[.]|[·]){2,15}$");
    }

    /**
     * 密码是否符合规范（^([^\\s'‘’]{8,20})$）
     * 
     * @return
     */
    public static boolean isValidPassword(String password) {
        if (null == password) {
            return false;
        }
        /*
         * String onlyNum = "^[0-9]{8,20}$"; // 仅数字 if
         * (password.matches(onlyNum)) { return false; }
         */
        return password.matches("^([^\\s'‘’]{6,12})$");
    }

    /**
     * 用户名是否符合规范（^[\u4E00-\u9FA5A-Za-z0-9_]+$）
     * 
     * @return
     */
    public static boolean isValidUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return false;
        }

        return username.matches("^[\u4E00-\u9FA5A-Za-z0-9_]{2,12}$");
    }
}
