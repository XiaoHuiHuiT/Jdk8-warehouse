package com.bntang666.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * @author BNTang
 */
public class Demo {

    static String SQL = "INSERT INTO `scp`.`forecast_terapeak`(`keywords`, `market_place`, `category_id`, `week_num`, `total_sale_money`,  `created_time`, `updated_time`) VALUES ('%s', '%s', %s, %s, %s,    NOW(), NOW());";

    static String data = "128585.6965\t218782.0554\t196553.9229\t182399.1662\t209912.999\t216272.0805\t182409.0728\t159881.1696\t187279.5478\t196395.0283\t260743.5051\t334303.5038\t408963.3952\t401703.3932\t389723.1962\t486530.3409\t459274.5776\t521421.8821\t501263.8143\t442961.5478\t315670.7039\t340115.959\t349559.4622\t349306.011\t320066.1417\t294618.3525\t327659.7602\t280311.6728\t267430.0457\t240717.9712\t246923.6704\t263632.552\t247379.9673\t248517.24\t264188.6173\t286450.0434\t171349.5846\t175302.742\t168162.6983\t175345.7433\t159045.0851\t148535.4495\t151689.4254\t165824.8031\t171535.5646\t154700.8531\t154157.4214\t193662.2536\t200532.7949\t195424.1969\t166886.3427\t188308.4331";

    public static void main(String[] args) {
        String jsonString = "";
    }

    /**
     * 是否是数字或小数
     *
     * @return boolean
     * @throws
     * @tags @return
     * @author wanghc
     * @date 2015-9-16 下午5:50:15
     */
    private static boolean isNumber(double value) {
        // 0.01 = 1 - 0.01 = != 0 true
        // 1.0 = 1 - 1.0 = 0 != 0 false

        // 3.14 = 4
        // 3.14

        //
        return Math.ceil(value) - value != 0;
    }

    public void ifelse1() {

        List<String> a = new ArrayList<>();
        a.add("1a");
        a.add("2b");
        a.add("3c");
        a.add("4d");
        a.add("5e");

        int limit = 2;
        Integer size = a.size();
        if (limit < size) {
            //分批数
            int part = size / limit;
            for (int i = 0; i < part; i++) {
                List<String> listPage = a.subList(0, limit);

                // 分批处理
                for (int q = 0; q < listPage.size(); q++) {
                }

                // 剔除已处理的
                a.subList(0, limit).clear();
            }
            // 如果有剩余, 处理剩余条数
            if (!a.isEmpty()) {
                for (int q = 0; q < a.size(); q++) {
                }
            }
        }

    }

    public static boolean getList(BufferedReader br) {
        List<String[]> allString = new ArrayList<>();
        boolean status = false;
        String everyLine = "";
        try {
            int index = 0;
            while ((everyLine = br.readLine()) != null) {
                String[] strList = everyLine.split(",");
                System.out.println(everyLine);
                allString.add(strList);
                index++;
                if (index == 3) {
                    status = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("总条数为：" + allString.size());
//取值
        for (int i = 0; i < allString.size(); i++) {
            System.out.println(allString.get(i)[1]);
        }
//这里做新增操作,保存到数据库。。。。。。
        return status;
    }

    /**
     * 传入总数和每多少进行分段
     *
     * @param count   总数
     * @param fenDuan 每次查询多少
     * @return 分页参数
     */
    private static Map<Long, Long> getStartIdAndEndId(long count, long fenDuan) {
        Map<Long, Long> map = new TreeMap();
        long num = count / fenDuan;
        long yuShu = count % fenDuan;

        for (int i = 1; i <= num; i++) {
            map.put((i - 1) * fenDuan, i * fenDuan);
        }

        Long yuShufinal = num * fenDuan + yuShu;
        map.put(num * fenDuan, yuShufinal);

        return map;
    }

    public static String generateInsertSQL(String keyword, String site, String category, String str) throws IOException {
        String replaceStr = str.replace("\t", ",");

        String[] strings = replaceStr.split(",");

        for (int i = 0; i < strings.length; i++) {
            String format = String.format(SQL, keyword, site, category, i + 1, strings[i]);
            System.out.println(format);
        }
        return null;
    }

}