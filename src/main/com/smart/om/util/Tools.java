package com.smart.om.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @author langyuk
 */
public class Tools {
    private static final Logger logger = Logger.getLogger(Tools.class);
    private static final String DEFAULT_STRING = "";
    private final static String[] hexMd5 = {"0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 将对象转成字符串并去除首尾空格，为null时返回空字符串
     */
    public static String trim(Object obj) {
        if (obj == null) {
            return DEFAULT_STRING;
        }
        return obj.toString().trim();
    }

    /**
     * MD5加密
     *
     * @param str 被加密字符串
     * @return MD5加密后的32位密文
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexMd5[d1] + hexMd5[d2];
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            resultSb.append(byteToHexString(bytes[i]));
        }
        return resultSb.toString();
    }

    public static String md5(String str) {
        if (str == null) {
            return DEFAULT_STRING;
        }
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            return DEFAULT_STRING;
        }
        return byteArrayToHexString(md.digest(str.getBytes()));
    }

    /******
     * 查询服务器IP
     *
     * @return
     */
    public static String getServerIp() {
        String SERVER_IP = "";
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces
                        .nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                SERVER_IP = ip.getHostAddress();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                    SERVER_IP = ip.getHostAddress();
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return SERVER_IP;
    }

    /**
     * 把用分隔符分开的字符串转换成集合
     */
    public static List<String> strToList(String outStr, String splitStr) {
        List<String> strList = new ArrayList<String>();
        try {
            String tmpStr = outStr;
            // 获得分隔符的数量
            int splits = 0;
            splits = countTokens(tmpStr, splitStr);
            // 拆分的结果应为分隔符的数量加一
            for (int i = 0; i < splits + 1; i++) {
                if (i == splits) {// 如果是最后一个元素的话，直接添加到集合中
                    strList.add(tmpStr);
                } else {
                    strList.add(tmpStr.substring(0, tmpStr.indexOf(splitStr)));
                    tmpStr = tmpStr.substring(tmpStr.indexOf(splitStr) + splitStr.length());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    /**
     * 记算分隔符的数量
     */
    public static int countTokens(String str, String splitStr) {
        // 记数器，记录分隔符的数量
        int count = 0;
        // 循环统计分隔符数量
        while (true) {
            // 记录当前分隔符的位置
            int curPos = str.indexOf(splitStr);
            // 记录字串的总长度
            int endPos = str.length();
            // 如果查询不到分隔符，则跳出循环
            if (curPos == -1) {
                break;
            }
            // 重新获得子字符串
            str = str.substring(curPos + splitStr.length());
            // 记数器做自加操作
            count++;
        }
        // System.out.println("分隔符数量：" + count);
        return count;
    }

    /**
     * 获取汉字的拼音首字母
     *
     * @param str
     * @return
     */
    public static String getFirstLetter(String str) {
        if (str == null) {
            return DEFAULT_STRING;
        }

        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
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
     * 获取汉字全拼
     *
     * @param str
     * @return
     */
    public static String getPingYin(String str) {
        if (str == null) {
            return DEFAULT_STRING;
        }

        char[] chars = str.toCharArray();
        String[] strs = new String[chars.length];
        HanyuPinyinOutputFormat hpof = new HanyuPinyinOutputFormat();
        hpof.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hpof.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hpof.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < chars.length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    strs = PinyinHelper.toHanyuPinyinStringArray(chars[i], hpof);
                    sb.append(strs[0]);
                } else {
                    sb.append(Character.toString(chars[i]));
                }
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination ex) {
            return DEFAULT_STRING;
        }
    }

    /**
     * 文本串转换成Html格式字符串
     *
     * @param str
     * @return
     */
    public static String getHtmlFromText(String str) {
        if (str == null) {
            return DEFAULT_STRING;
        }

        str = str.replace("&", "&amp;");
        str = str.replace("<", "&lt;");
        str = str.replace(">", "&gt;");
        str = str.replace(" ", "&nbsp;");
        str = str.replace("\r\n", "<br />");
        str = str.replace("\r", "<br />");
        str = str.replace("\n", "<br />");

        return str;
    }

    /**
     * Html格式字符串转换成文本串
     *
     * @param str
     * @return
     */
    public static String getTextFromHtml(String str) {
        if (str == null) {
            return DEFAULT_STRING;
        }

        // 删除脚本
        str = Pattern.compile("<script[^>]*?>.*?</script>", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        // 删除样式
        str = Pattern.compile("<style[^>]*?>.*?</style>", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        // 删除Html标记
        str = Pattern.compile("<(.[^>]*)>", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        str = Pattern.compile("([\\r\\n])[\\s]+", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        str = Pattern.compile("-->", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        str = Pattern.compile("<!--.*", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        str = Pattern.compile("&(quot|#34);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("\"");
        str = Pattern.compile("&(amp|#38);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("&");
        str = Pattern.compile("&(lt|#60);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("<");
        str = Pattern.compile("&(gt|#62);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll(">");
        str = Pattern.compile("&(nbsp|#160);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll(" ");
        str = Pattern.compile("&(iexcl|#161);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("\\xa1");
        str = Pattern.compile("&(cent|#162);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("\\xa2");
        str = Pattern.compile("&(pound|#163);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("\\xa3");
        str = Pattern.compile("&(copy|#169);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("\\xa9");
        str = Pattern.compile("&#(\\d+);", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");

        str = str.replace("<", "");
        str = str.replace(">", "");
        str = str.replace("\r\n", "");
        str = str.replace("\r", "");
        str = str.replace("\n", "");

        return str.trim();
    }

    /**
     * 根据上传的base64图片进行保存
     * @param base64String
     * @param suffix
     * @param picUrl
     */
    public static void base64StringToImage(String base64String, String suffix, String picUrl) {
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File file = new File(picUrl);// 可以是jpg,png,gif格式
            if (!file.exists()) {
                file.createNewFile();
            }
            ImageIO.write(bi1, suffix, file);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据当前时间组合成一个30位的唯一字符串:year+month+day+hour+minute+second+000001+0+uuid(9位 )
     *
     * @return yyyyMMddhhmmss000001+0+uuid(9位)
     */
    public static String getId() {
        String lastId = "";
        String uuid = "";
        String year = "";
        String month = "";
        String day = "";
        String hour = "";
        String minute = "";
        String second = "";
        Calendar calendar = Calendar.getInstance();
        switch (String.valueOf(calendar.get(Calendar.YEAR)).length()) {
            case 1:
                year = "000" + String.valueOf(calendar.get(Calendar.YEAR));
                break;
            case 2:
                year = "00" + String.valueOf(calendar.get(Calendar.YEAR));
                break;
            case 3:
                year = "0" + String.valueOf(calendar.get(Calendar.YEAR));
                break;
            default:
                year = String.valueOf(calendar.get(Calendar.YEAR));
                break;
        }
        month = (String.valueOf(calendar.get(Calendar.MONTH)).length() == 1 && calendar.get(Calendar.MONTH) != 9) ? ("0" + String.valueOf(calendar.get(Calendar.MONTH) + 1)) : String.valueOf(calendar.get(Calendar.MONTH) + 1);
        day = (String.valueOf(calendar.get(Calendar.DATE)).length() == 1) ? ("0" + String.valueOf(calendar.get(Calendar.DATE))) : String.valueOf(calendar.get(Calendar.DATE));
        hour = (String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)).length() == 1) ? ("0" + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))) : String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        minute = (String.valueOf(calendar.get(Calendar.MINUTE)).length() == 1) ? ("0" + String.valueOf(calendar.get(Calendar.MINUTE))) : String.valueOf(calendar.get(Calendar.MINUTE));
        second = (String.valueOf(calendar.get(Calendar.SECOND)).length() == 1) ? ("0" + String.valueOf(calendar.get(Calendar.SECOND))) : String.valueOf(calendar.get(Calendar.SECOND));
        String id = year + month + day + hour + minute + second;
        if (lastId.length() == 0) {
            lastId = id + "000001";
        } else if (id.substring(0, 14).equals(lastId.substring(0, 14)) == false) {
            lastId = id + "000001";
        } else {
            if (lastId.length() != 20) {
                lastId = id + "000001";
            } else {
                int m = Integer.valueOf(lastId.substring(14)).intValue() + 1;
                for (int i = 0; i < 6 - String.valueOf(m).length(); i++) {
                    id = id + "0";
                }
                lastId = id + m;
            }
        }

        //每次都生成新的UUID，以免服务器时间来回修改导致主键重复
        uuid = "";
        if (uuid.equals("")) {
            uuid = String.valueOf(UUID.randomUUID().hashCode());
            if (uuid.startsWith("-")) {
                uuid = uuid.substring(1);
            }
            int len = uuid.length();
            if (len > 9) {
                uuid = uuid.substring(len - 9);
            }
            len = uuid.length();
            for (int i = 10; i > len; i--) {
                uuid = "0" + uuid;
            }
        }
        return lastId + uuid;
    }


    /**
     * 上传图片
     *
     * @return 字符串: "/upload/" + 图片分类 + "/" + 图片路径
     */
    public static String uploadImg(InputStream fis, String path, HttpServletRequest request, String imgPath) throws IOException {
        if (imgPath == null) {
            imgPath = "/upload/" + path + "/" + Tools.getId() + ".jpg";
        }
        byte buffer[] = new byte[8192];
        int count;
        FileOutputStream out = null;
        String imgePath = request.getRealPath("") + imgPath;
        try {
            out = new FileOutputStream(imgePath);
            while ((count = fis.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return imgPath;
    }
    
    public static void main(String[] args) {
    	System.out.println(DateUtil.getAfterDayOfSpecified("2015-10-10"));
		
	}
}
