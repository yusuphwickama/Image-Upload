package com.wickerlabs.imageuploader.util;

import android.util.Patterns;

/**
 * Created by: WickerLabs.Inc
 * Project title: T-Connect
 * Time: 1:46 PM
 * Date: 2/12/2017
 * Website: http://www.wickerlabs.com
 */

public class ValidUtils {

    public static boolean isPhoneValid(String phoneNumber) {
        //Regex for Tanzanian phone number format
        String patternOne = "^(\\+255|0)++(6|7)+[1-9]{2}+[0-9]{6}";

        return phoneNumber.matches(patternOne);
    }

    public static boolean isTTCL(String phoneNumber) {
        // A regex to match a valid ttcl number
        String regex = "^(\\+255|0)+(63|73)+[1-9][0-9]{6}";
        return phoneNumber.matches(regex);
    }

    public static String standardTZNumber(String phoneNumber) {
        String patternOne = "^(\\+255)+(6|7)+[1-9]{2}+[0-9]{6}";
        String patternTwo = "^(0)+(6|7)+[1-9]{2}+[0-9]{6}";

        if (phoneNumber.matches(patternOne)) {
            return phoneNumber;
        } else if (phoneNumber.matches(patternTwo)) {
            return "+255" + phoneNumber.substring(1);
        }

        return null;
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
