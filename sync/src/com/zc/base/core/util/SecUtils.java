package com.zc.base.core.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;


public class SecUtils {
    private static Integer RULENUM_DEFAULT = Integer.valueOf(2);


    private static Integer RULENUM_MIN = Integer.valueOf(1);


    private static Integer RULENUM_MAX = Integer.valueOf(4);


    private static Integer PWDLEN_DEFAULT = Integer.valueOf(6);


    private static Integer PWDLEN_MIN = Integer.valueOf(6);


    private static Integer PWDLEN_MAX = Integer.valueOf(32);


    private static Integer DEFAULT_INTERVAL = Integer.valueOf(3000);


    private static final Integer DEFAULT_MAX_LOGIN = Integer.valueOf(3);


    public static String generatePassword(String username, Integer ruleNum, Integer pwdLength) {
        String password = null;


        ruleNum = ruleNum == null ? RULENUM_DEFAULT : ruleNum;

        ruleNum = ruleNum.intValue() < RULENUM_MIN.intValue() ? RULENUM_MIN : ruleNum;

        ruleNum = ruleNum.intValue() > RULENUM_MAX.intValue() ? RULENUM_MAX : ruleNum;


        pwdLength = pwdLength == null ? PWDLEN_DEFAULT : pwdLength;

        pwdLength = pwdLength.intValue() < PWDLEN_MIN.intValue() ? PWDLEN_MIN : pwdLength;

        pwdLength = pwdLength.intValue() > PWDLEN_MAX.intValue() ? PWDLEN_MAX : pwdLength;

        Random r = new Random();
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789`~!@#$%^&*()-_=+\\|[{}]";


        int count = 0;


        String reverseUsername = StringUtils.reverse(username);
        while (count < ruleNum.intValue()) {
            count = 0;
            StringBuffer passwordBuffered = new StringBuffer();
            for (int i = 1; i <= pwdLength.intValue(); i++) {
                passwordBuffered.append(str.charAt(r.nextInt(84)));
            }
            password = passwordBuffered.toString();
            count = Pattern.matches("^.*[a-z]+?.*$", password) ? count + 1 : count;
            count = Pattern.matches("^.*[A-Z]+?.*$", password) ? count + 1 : count;
            count = Pattern.matches("^.*[0-9]+?.*$", password) ? count + 1 : count;
            count = Pattern.matches("^.*[`~!@#$%^&*()-_=+\\|[{}]]+?.*$", password) ? count + 1 : count;


            if ((password.equals(username)) || (password.equals(reverseUsername))) {
                count = 0;
            }
        }

        return password;
    }


    public static boolean isOutOfLoginCount(Integer interval, Integer maxLoginNumber, List<Date> loginHistoryList) {
        boolean isOut = false;


        interval = interval == null ? DEFAULT_INTERVAL : interval;
        maxLoginNumber = maxLoginNumber == null ? DEFAULT_MAX_LOGIN : maxLoginNumber;


        if ((loginHistoryList == null) || (loginHistoryList.size() + 1 <= maxLoginNumber.intValue())) {
            return isOut;
        }

        Collections.sort(loginHistoryList);
        Collections.reverse(loginHistoryList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        int actualLoginCount = 1;
        Date preDate = new Date();

        for (int i = 1; i < loginHistoryList.size(); i++) {
            preDate = (Date) loginHistoryList.get(i - 1);
            Date curr = (Date) loginHistoryList.get(i);

            System.out.println(sdf.format(curr));

            long distence = preDate.getTime() - curr.getTime();

            if (distence > interval.intValue()) break;
            actualLoginCount++;
        }


        if (actualLoginCount >= maxLoginNumber.intValue()) {
            isOut = true;
        }

        return isOut;
    }
}
