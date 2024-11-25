package cn.artskill.java.common.core.utils;
 
import java.util.*;
 
/**
 * 随机密码生成工具 *
 */
public class PasswordUtil {
 
    // 特殊字符
    private static final String SPECIAL_CHARS = "!@#$%&*_=";
    // 去掉I、L、O、Q易混淆字符
    private static final String UPPER_WORD_CHARS = "ABCDEFGHJKMNPRSTUVWXYZ";
    // 去掉i、l、o、q易混淆字符
    private static final String LOWER_WORD_CHARS = "abcdefghjkmnprstuvwxyz";
    // 去掉1、0易混淆字符
    private static final String NUMBER_CHARS = "23456789";
 
 
    /**
     * 生成固定长度的复杂密码
     * @param length
     * @return
     */
    public static String randomPassword(int length){
        return randomPassword(length,true,true,true,true);
    }
 
    /**
     * @param length        生成密码的长度  建议至少6位
     * @param isLowerWord   是否包含小写字母
     * @param isUpperWord   是否包含大写字母
     * @param isSpecialChar 是否包含特殊字符
     * @param isNum         是否包含数字
     * @return String 随机密码
     * @description: 生成随机密码的工具方法
     */
    public static String randomPassword(int length, Boolean isLowerWord, Boolean isUpperWord, Boolean isSpecialChar, Boolean isNum) {
        Random rnd = new Random();
        // 先取必填项1个
        Map<Integer, String> map = new HashMap<>();
        if (isLowerWord) {
            map.put(1, LOWER_WORD_CHARS);
        }
        if (isUpperWord) {
            map.put(2, UPPER_WORD_CHARS);
        }
        if (isSpecialChar) {
            map.put(3, SPECIAL_CHARS);
        }
        if (isNum) {
            map.put(4, NUMBER_CHARS);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            sb.append(value.charAt(rnd.nextInt(value.length())));
        }
        char[] chars = new char[length - sb.length()];
        for (int i = 0; i < length - sb.length(); i++) {
            chars[i] = nextChar(rnd, isLowerWord, isUpperWord, isSpecialChar, isNum);
        }
        StringBuilder resultSb = sb.append(new String(chars));
        // 对结果数据乱序处理（前面固定放到前几个了，故需乱序）
        String[] split = resultSb.toString().split("");
        List<String> strings = Arrays.asList(split);
        Collections.shuffle(strings);
        StringBuilder stringBuilder = new StringBuilder(strings.size());
        strings.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
 
 
    private static char nextChar(Random rnd, Boolean isLowerWord, Boolean isUpperWord, Boolean isSpecialChar, Boolean isNum) {
        List<Integer> list = new ArrayList<>();
        if (isLowerWord) {
            list.add(1);
        }
        if (isUpperWord) {
            list.add(2);
        }
        if (isSpecialChar) {
            list.add(3);
        }
        if (isNum) {
            list.add(4);
        }
        if (list.size() == 0) {
            // 默认数字密码
            list.add(4);
        }
 
        // 随机list索引
        int index = rnd.nextInt(list.size());
        Integer integer = list.get(index);
        switch (integer) {
            case 1:
                return LOWER_WORD_CHARS.charAt(rnd.nextInt(LOWER_WORD_CHARS.length()));
            case 2:
                return UPPER_WORD_CHARS.charAt(rnd.nextInt(UPPER_WORD_CHARS.length()));
            case 3:
                return SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
            default:
                return NUMBER_CHARS.charAt(rnd.nextInt(NUMBER_CHARS.length()));
        }
    }
 
}
 
