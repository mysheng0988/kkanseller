package com.mysheng.office.kkanseller.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by myaheng on 2018/8/29.
 */

public class ChinesePinyinUtil {
    /**
     * 得到 汉字的全拼
     * @param src 中文字符串
     * @return
     */
    public static String getPingYin(String src) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder sb = new StringBuilder();
        char[] srcArray = src.toCharArray();
        try {
            for (int i = 0; i < srcArray.length; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(srcArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] targetArray = PinyinHelper.toHanyuPinyinStringArray(srcArray[i], format);
                    sb.append(targetArray[0]);
                } else {
                    sb.append(java.lang.Character.toString(srcArray[i]));
                }
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 得到中文首字母,例如"专科"得到zk返回
     * @param str 中文字符串
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                sb.append(pinyinArray[0].charAt(0));
            } else {
                sb.append(word);
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串转移为ASCII码
     * @param cnStr  中文字符串
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuilder sb = new StringBuilder();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            sb.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return sb.toString();
    }
    //随机生成常见汉字
    public static String getRandomChar() {
        String str = "";
        int highCode;
        int lowCode;

        Random random = new Random();

        highCode = (176 + Math.abs(random.nextInt(39))); //B0 + 0~39(16~55) 一级汉字所占区
        lowCode = (161 + Math.abs(random.nextInt(93))); //A1 + 0~93 每区有94个汉字

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
