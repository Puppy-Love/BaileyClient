package com.min.bailey.client.app.utils;

/**
 * @author Administrator
 * @创建者 min
 * @描述 处理文字工具
 */
public class TextHandleUtil {

    public static String handleCount2TenThousand(int count) {
        if (count == 0) {
            return "--";
        }
        if (count / 10000 > 0) {
            double num = (double) count / 10000;
            return String.format("%.2f", num) + "万";
        }
        return String.valueOf(count);
    }

    public static String handleDurationSecond(int totalTime) {
        return handleDurationMillis(totalTime * 1000);
    }

    public static String handleDurationMillis(int totalTime) {
        int hour = totalTime / (3600 * 1000);
        int minutes = totalTime % (3600 * 1000) / (60 * 1000);
        int second = totalTime % (3600 * 1000) % (60 * 1000) / 1000;

        String result = minutes + ":" + second;
        if (hour > 0) {
            result = hour + ":" + result;
        }
        return result;
    }

    /**
     * 格式化加密的银行卡号码
     *
     * @param bankId
     * @return
     */
    public static String handBankId(String bankId) {
        return ObjectUtils.isNotEmpty(bankId)
                ? bankId.substring(0, 4) + "  ****  ****  " + bankId.substring(bankId.length() - 5, bankId.length() - 1)
                : "";
    }

    public static void main(String[] args) {
        System.out.println(TextHandleUtil.handleCount2TenThousand(19000));
        System.out.println(TextHandleUtil.handleDurationSecond(452));
    }
}
