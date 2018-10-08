package in.csdc.dda.util;

import android.util.Log;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rajdeep Yadav
 * Date : 08-Oct-18
 * Time : 12:37 PM
 */
public class StringUtils {
    private static final String	LOG_TAG		= "StringUtils";

    public static final String	EMAIL_REGEX	= "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

    /**
     * @param pStr
     *            String object to be tested.
     * @returns true if the given string is null or empty or contains spaces
     *          only.
     */
    public static boolean isNullOrEmpty(final String pStr) {
        return pStr == null || pStr.trim().length() == 0 || pStr.trim().equalsIgnoreCase("null");
    }

    public static boolean isNullOrEmptyOrZero(final String pStr) {
        return pStr == null || pStr.trim().length() == 0 || pStr.trim().equalsIgnoreCase("null") || pStr.trim().equalsIgnoreCase("0");
    }

    /**
     * @param pEmail
     * @param pAllowBlank
     * @return true if pEmail matches with {@link StringUtils#EMAIL_REGEX},
     *         false otherwise
     */
    public static boolean isValidEmail(String pEmail, boolean pAllowBlank) {
        if (pAllowBlank && isNullOrEmpty(pEmail)) {
            return true;
        }
        Pattern validRegexPattern = Pattern.compile(EMAIL_REGEX);
        return validRegexPattern.matcher(pEmail).matches();
    }

    /**
     * @param pStr
     * @param pStartIndex
     *            or -1 to parse from complete pStr
     * @param pEndIndex
     * @return int value, parsed from pStr or a substring of pStr
     */
    public static int parseInt(String pStr, int pStartIndex, int pEndIndex) {
        if (pStr == null) {
            return 0;
        }
        try {
            if (pStartIndex == -1) {
                return Integer.parseInt(pStr);
            } else {
                return Integer.parseInt(pStr.substring(pStartIndex, pEndIndex));
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "parseInt() pStr: " + pStr + ", Start: " + pStartIndex + ", End: " + pEndIndex);
            return 0;
        }
    }

    /**
     * This method checks and ensure http/https protocol in URL
     *
     * @param url
     * @return formattedUrl
     */
    public static String getFormattedURL(String url) {
        if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
            return url;
        } else if (url.indexOf("://") == 0) {
            return "http" + url;
        } else if (url.indexOf("//") == 0) {
            return "http:" + url;
        } else {
            return "http://" + url;
        }
    }

    /**
     * @param pStr
     *            .
     * @returns
     */
    public static String firstLetterToUpperCase(String pWord) {
        pWord = pWord == null ? "" : pWord;
        String output = "";
        for (int i = 0; i < pWord.length(); i++) {
            if (i == 0) {
                output += Character.toUpperCase(pWord.charAt(i));
            } else {
                output += Character.toLowerCase(pWord.charAt(i));
            }
        }
        return output;
    }


    public static boolean isValidMobileNumber(String pMobileNumber, boolean pPlusSignNeeded, int pMinLength) {
        if (StringUtils.isNullOrEmpty(pMobileNumber)) {
            return false;
        }
        pMobileNumber = pMobileNumber.trim();
        if (pPlusSignNeeded && !pMobileNumber.startsWith("+")) {
            return false;
        }
        return pMobileNumber.length() >= pMinLength;
    }


    public static boolean isValidMobileNumber(String pMobileNumber) {
        if (StringUtils.isNullOrEmpty(pMobileNumber)) {
            return false;
        }

        if(pMobileNumber.length()!=10){
            return false;
        }

        try {
            long l = Long.parseLong(pMobileNumber);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * check whether permission exits in active session or not.
     *
     * @param subset
     * @param superset
     * @return
     */
    public static boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
        for (String string : subset) {
            if (!superset.contains(string)) {
                return false;
            }
        }
        return true;
    }

    /**
     * return formated float as 232.00000000 should be 232, 0.180000000001
     * should be 0.18
     *
     * @param pInputFloat
     * @return
     */
    public static String getFormatDecimalAmount(float pInputFloat) {
        return getFormatDecimalAmount(pInputFloat, 2);
    }

    /**
     * return formated float as 232.00000000 should be 232, 0.180000000001
     * should be 0.18
     *
     * @param pInputFloat
     * @param pNeededDigitsAfterDecimal
     * @return
     */
    public static String getFormatDecimalAmount(float pInputFloat, int pNeededDigitsAfterDecimal) {
        if (pInputFloat == (int) pInputFloat || pNeededDigitsAfterDecimal <= 0) {
            return String.format("%d", (int) pInputFloat);
        } else {
            return String.format("%1." + pNeededDigitsAfterDecimal + "f", pInputFloat);
        }
    }

    private static Pattern usrNamePtrn = Pattern.compile("^[a-zA-Z0-9_-]{3,15}$");

    public static boolean validateUserName(String userName){

        Matcher mtch = usrNamePtrn.matcher(userName);
        return mtch.matches();
    }

    public static String toNumeralString(final Boolean input) {
        if (input == null) {
            return "null";
        } else {
            return input.booleanValue() ? "1" : "0";
        }
    }


    public static String padLeft(String string,int postions,String c){
        String newString=string==null?"":string;

        if(newString.length()>=postions){
            return  newString;
        }
        int diff=postions-newString.length();
        for(int i=0;i<diff;i++){
            newString=c+newString;
        }
        return newString;
    }


    public static String padRight(String string,int postions,String c){
        String newString=string==null?"":string;

        if(newString.length()>=postions){
            return  newString;
        }
        int diff=postions-newString.length();
        for(int i=0;i<diff;i++){
            newString=newString+c;
        }
        return newString;
    }
}
