package com.e2open.common.util;

import com.sun.jersey.core.impl.provider.entity.Inflector;
import com.vdurmont.emoji.EmojiParser;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by abhishek on 5/3/16.
 */
public class StringUtils {
    private static final Logger logger = Logger.getLogger(StringUtils.class);

    public static final String EMPTY = "";
    private static final Pattern NAME_PATTERN = Pattern.compile("[0-9a-zA-Z_\\-]");

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    private static final String DOMAIN_PATTERN_STRING = "[a-z][a-z0-9\\-]{0,28}[a-z0-9]";
    private static final Pattern domainPattern = Pattern.compile(DOMAIN_PATTERN_STRING);

    private static SecureRandom random;
    private static Inflector inflector;

    static {
        random = new SecureRandom();
        inflector = Inflector.getInstance();
    }

    public static String singularize(String word) {
        if (!isNullOrEmpty(word)) {
            return word;
        }
        return null;

    }

    public static String convertToTrimLowerCaseString(String text){

        if(text!=null){
            text=text.trim();
            if(text.length()==0) return null;
            return text.toLowerCase();
        }
        return null;
    }


    public static String pluralize(String word) {
        if (!isNullOrEmpty(word)) {
            return inflector.pluralize(word);
        }
        return null;

    }

    public static String nextRandomNumeric() {
        Long randomNum = 100000L + (long) (Math.random() * 999999L);
        return String.valueOf(randomNum);
    }

    public static String nextRandomNumber() {
        return new BigInteger(256, random).toString(32);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.equals("") || str.trim().length() == 0 || str.trim().equals("null");
    }

    public static boolean isNullOrEmptyString(String str) {
        return str == null || str.equals("") || str.trim().length() == 0 || str.trim().equals("null");
    }

    public static String LongToString(Long id) {
        return id == null ? "" : id.toString();
    }

    public static String convertListToString(List<String> arrList) {
        if (arrList != null && arrList.size() > 0) {
            StringBuilder result = new StringBuilder();
            for (String string : arrList) {
                result.append(string);
                result.append(",");
            }
            return result.substring(0, result.length() - 1);
        }
        return "";
    }

    public static String convertCase(String str) {
        if (str == null) return "";
        StringBuilder sb = new StringBuilder();
        String[] commaSeparatedStr = str.split(",");

        int len = commaSeparatedStr.length;
        int lenIncrement = 1;

        for (String strg : commaSeparatedStr) {
            char[] stringArray = strg.toCharArray();
            if (stringArray.length > 0) {
                stringArray[0] = Character.toUpperCase(stringArray[0]);
                sb = sb.append(new String(stringArray));
                if (lenIncrement < len)
                    sb.append(", ");
            }
            lenIncrement++;
        }

        return sb.toString();
    }

    public static String abbreviateAnd(String str) {
        if (!StringUtils.isNullOrEmpty(str))
            return str.replaceAll("[Aa]nd", "&");
        return "";
    }



    public static String getShortString(String longString) {
        if (longString == null) return "";
        if (longString.length() > 50)
            return longString.substring(0, 50) + "...";
        else
            return longString;
    }

    public static String getShortStringForSMS(String longString, int maxLength) {
        if (longString == null) return "";
        if (maxLength == 0) maxLength = 20;
        if (longString.length() > maxLength)
            return longString.substring(0, maxLength) + "...";
        else
            return longString;
    }


    public static String getShortEmailForSMS(String emailAddr, int maxLength) {
        if (emailAddr == null) return "";
        if (maxLength == 0) maxLength = 20;
        if (emailAddr.length() > maxLength) {
            String[] temp = emailAddr.split("@");
            if (temp.length == 2) {
                String value = getShortStringForSMS(temp[0], 5) + "@" + getShortStringForSMS(temp[1], maxLength - 6);
                return value;
            } else return emailAddr.substring(0, maxLength) + "...";
        } else
            return emailAddr;
    }

    public static String getRecommendShortString(String longString) {
        if (longString == null) return "";
        if (longString.length() > 120)
            return longString.substring(0, 120) + "... ";
        else
            return longString;
    }

    public static String getRecommendationSnippet(String longString) {
        if (longString == null) return "";
        if (longString.length() > 75)
            return longString.substring(0, longString.substring(0, 75).lastIndexOf(" ")) + " ...";
        else
            return longString;
    }

    public static String getSingularPluralString(String longString, int count) {
        if (longString == null) return "";
        if (count > 1)
            return longString + "s";
        else
            return longString;
    }

    public static String getSingularPluralString(String longString, long count) {
        if (longString == null) return "";
        if (count > 1)
            return longString + "s";
        else
            return longString;
    }

    public static String clearSpaces(String longString) {
        if (longString != null && !longString.isEmpty()) {
            return longString.replaceAll("\\s+", " ");
        }
        return " ";
    }

    public static String getCleanEmailString(String longString) {
        String temp = getTitleCase(longString);
        temp = getShortString(longString);

        if (temp == null) return "";
        if (temp.length() > 15)
            return temp.substring(0, 15) + "...";
        else
            return temp;

    }

    public static String returnFirstNameAfterSpace(String string) {
        if (string == null) return "";
        StringBuilder sb = new StringBuilder();
        String[] spaceSeperatedString = string.split(" ");
        return spaceSeperatedString[0];
    }

    public static String[] returnFirstAndLastNames(String name) {

        int start = name.indexOf(' ');
        int end = name.lastIndexOf(' ');
        String[] returnNames = new String[2];

        String firstName = "";
        String middleName = "";
        String lastName = "";

        if (start >= 0) {
            firstName = name.substring(0, start);
            if (end > start)
                middleName = name.substring(start + 1, end);
            lastName = name.substring(end + 1, name.length());
        }
        returnNames[0] = firstName;
        returnNames[1] = lastName;
        return returnNames;
    }

    public static String getTitleCase(String longString) {
        if (longString == null) return "";
        StringBuilder sb = new StringBuilder();
        String[] spaceSeperatedString = longString.split(" ");

        int len = spaceSeperatedString.length;
        int lenIncrement = 1;

        for (String strLoop : spaceSeperatedString) {
            char[] stringArray = strLoop.toLowerCase().toCharArray();
            if (stringArray.length > 0) {
                stringArray[0] = Character.toUpperCase(stringArray[0]);
                sb = sb.append(new String(stringArray));
                if (lenIncrement < len)
                    sb.append(" ");
                lenIncrement++;
            }
        }

        return sb.toString();
    }

    public static String getSimplePhoneNumber(String longString) {
        if (longString == null) return "";
        return longString.replace(" ", "");
    }

    public static String getCorrectEntityCountString(Long entityCount, String entityType) {
        if (entityType.equalsIgnoreCase("people")) {
            if (entityCount == 1)
                return "person";
            else return entityType;
        } else if (entityType.equalsIgnoreCase("invites")) {
            if (entityCount == 1)
                return "invite";
            else return entityType;
        } else if (entityType.equalsIgnoreCase("friends")) {
            if (entityCount == 1)
                return "friend";
            else return entityType;
        } else if (entityType.equalsIgnoreCase("love")) {
            if (entityCount == 1)
                return "loves";
            else return entityType;
        } else return entityType;
    }

    public static String getCleanLink(String url) {
        if (StringUtils.isNullOrEmpty(url))
            return url;
        if (url.indexOf("http://") == 0) {
            return url.replaceFirst("http:\\/\\/", "");
        } else if (url.indexOf("https://") == 0) {
            return url.replaceFirst("https:\\/\\/", "");
        } else return url;
    }

    public static String getHrefLink(String url) {
        if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
            return url;
        } else {
            return "http://" + url;
        }
    }

    public static String toLowerCase(String value) {
        if (!isNullOrEmpty(value)) {
            return value.toLowerCase();
        }
        return null;
    }

    public static String removeSpecialCharacters(String text) {
        text = text.replaceAll("[^A-Za-z0-9 ]", "");
        return text;
    }

    public static String removeSpecialCharactersExceptTilt(String text) {
        text = text.replaceAll("[^A-Za-z0-9 ~():]", "");
        return text;
    }

    public static String getDomainNameFromUrl(String website) {
        if (StringUtils.isNullOrEmpty(website)) {
            return null;
        }
        String regex = "^(ww[a-zA-Z0-9-]{0,}\\.)";
        return website.replaceAll(regex, "");
    }

    /**
     * Calculates the similarity (a number within 0 and 1) between two strings.
     */
    public static double findSimilarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0; /* both strings are zero length */
        }
    /* // If you have StringUtils, you can use it to calculate the edit distance:
    return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) /
                               (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    public static String escapeHTML(String s) {
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c > '\uD800' && c < '\uDFFF'){
                // smiley range
                out.append(c);
            } else if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static String escapeHTML(String s, Boolean parseForJS) {
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else if (c== 10){
                out.append("\\n");
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static String extractFirstSentences(String myText) {
        String[] paragraphs = myText.split("\\n");
        for (String paragraph : paragraphs) {
            return paragraph.split("[\\.\\?\\!][\\r\\n\\t ]+")[0];
        }
        return null;
    }

    /*
    this function parses the string to escape emoji and special characters for HTML
     */
    public static String parseForHtml(String stringToParse){
        return EmojiParser.parseToHtmlDecimal(StringUtils.escapeHTML(stringToParse));
    }

    public static String getFirstNCharactersWithEllipses(String actualString, Integer length){
        StringBuilder stringWithFirstNCharacters = new StringBuilder();
        String[] splittedByWords = actualString.split(" ");

        if(splittedByWords[0].length() > length){
            stringWithFirstNCharacters.append(actualString.substring(0, length) + "...");
        }else{
            for(int i=0; i<splittedByWords.length; i++){
                if(stringWithFirstNCharacters.length() + splittedByWords[i].length() > length){
                    stringWithFirstNCharacters.append("...");
                    break;
                }

                stringWithFirstNCharacters.append(" ");
                stringWithFirstNCharacters.append(splittedByWords[i]);
            }
        }



        return stringWithFirstNCharacters.toString();
    }

    public static String formatNumberAsIndianCurrency(Double num){
        Locale locale = new Locale("en", "in");
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
        String money = currencyInstance.format(num);
        money = money.replaceAll("Rs.", "");

        return money;
    }

    public static String getCleanName(String name){
        if (isNullOrEmpty(name))
            return "";
        Matcher m = NAME_PATTERN.matcher(name);
        String cleanName = StringUtils.EMPTY;
        while (m.find()) {
            cleanName += m.group();
        }
        return cleanName;
    }


    public static String getCLeanName(String alias){
        if (isNullOrEmpty(alias))
            return null;

        alias = alias.trim();
        alias = alias.replaceAll(" ","");

        if (alias != null && alias.length()>0) {
            alias = alias.toLowerCase();
            return alias;
        }else
            return null;

    }

    public static Boolean isAliasValid(String alias){

        if (isNullOrEmpty(alias))
            return Boolean.FALSE;

        return !alias.matches("(.*[_]{2,}).*|([0-9]+)|(^_).*$|^.*(_$)|(^.*[^a-z0-9_].*$)");
    }

    public static String cleanupIdentifierNaming(String alias){
        alias = alias.toLowerCase();

        return alias.replaceAll("^_|[^a-z0-9_]+|_$", "");
    }

    public static void main(String[] args) {
        String[] tests = {"This is a test", "This@Th_at.com", "abcdef_", "_abcdef", "abc_def", "a!@#$%%^bCDEF", "abcd__ef", "1234567890"};
        for (String test : tests) {
            System.out.println(test + ": " + StringUtils.isAliasValid(test));
        }
    }

    public static String hashStringMD5(String input) throws Exception  {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //System.out.println("Digest(in hex format):: " + sb.toString());
        return sb.toString();
    }

    public static String formatMoney(String value) {
        try {
            Double d = Double.valueOf(value);
            StringBuilder builder = new StringBuilder(formatNumberAsIndianCurrency(d));
            builder.insert(0, "\u20B9 ");
            int index = builder.indexOf("-");
            if (index > -1) {
                builder.deleteCharAt(index);
                builder.insert(0, "- ");
            }
            value = builder.toString();
        } catch (NumberFormatException e) {
            logger.info("The input[" + value + "] is not a number ");
        }
        return value;
    }

    public static String cleanuSpecialCharacters(String value){
        return value.replaceAll("'|\\\\|/|\"","");
    }

    public static Boolean validateEmail(String email){
        if (isNullOrEmpty(email))
            return Boolean.FALSE;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }


    public static Boolean validateMobile(String mobile){
        if (isNullOrEmpty(mobile))
            return Boolean.FALSE;
        if (mobile.length() != 10)
            return Boolean.FALSE;

        if (mobile.charAt(0) !='9' && mobile.charAt(0) !='8' && mobile.charAt(0) !='7')
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    /**
     *
     * @param password
     * @return
     *
     * /
     * (			# Start of group
    (?=.*\d)		#   must contains one digit from 0-9
    (?=.*[a-z])		#   must contains one lowercase characters
    (?=.*[A-Z])		#   must contains one uppercase characters
    (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
    .		#     match anything with previous condition checking
    {6,20}	#        length at least 6 characters and maximum of 20
    )			# End of group
     */
    public static Boolean validateResellerPassword(String password){
        if (isNullOrEmpty(password))
            return Boolean.FALSE;

        password = password.trim();
        if (password.length()>=6 && password.length()<=20)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

        //Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        //Matcher matcher = pattern.matcher(password);
        //return  matcher.matches();
    }


    public String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public Boolean validateDomain(String domain){
        if (isNullOrEmpty(domain))
            return Boolean.FALSE;
        Matcher matcher = domainPattern.matcher(domain);
        return  matcher.matches();
    }
}