package com.management.staff;

import com.csvreader.CsvReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class StaffApplicationTests {

    @Test
    void contextLoads() throws IOException {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("123456"));

        File csvFile = new File("/Users/liuhuilin/Downloads/JAVA测试题目/职工数据.csv");

        ArrayList<String> strings = readCsvByBufferedReader(csvFile.getPath());
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < strings.size(); i++) {
            String empInfo = strings.get(i);
            String[] split = empInfo.split(",");
            if (split.length == 6) {
                String id = split[0];
                String name = split[1];
                String sex = split[2];
                String age = split[3];
                String deptCode = split[4];
                String empDegreeCode = split[5];
                if ("男".equals(sex)) {
                    sex = "1";
                } else if ("女".equals(sex)) {
                    sex = "0";
                } else {
                    sex = "2";
                }

                if ("业务部".equals(deptCode)) {
                    deptCode = "1";
                } else if ("后勤部".equals(deptCode)) {
                    deptCode = "2";
                } else if ("人事部".equals(deptCode)) {
                    deptCode = "3";
                } else {
                    deptCode = "0";
                }

                if ("大专".equals(empDegreeCode)) {
                    empDegreeCode = "1";
                } else if ("本科".equals(empDegreeCode)) {
                    empDegreeCode = "2";
                } else if ("研究生".equals(empDegreeCode)) {
                    empDegreeCode = "3";
                } else {
                    empDegreeCode = "0";
                }

                sb.append("insert into emp values(")
                        .append("'"+id+"',")
                        .append("'"+name+"',")
                        .append(sex+",")
                        .append(age+",")
                        .append(deptCode+",")
                        .append(empDegreeCode+");")
                        .append("\n");

                System.out.println(id+name+sex+age+deptCode+empDegreeCode);
            }
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/liuhuilin/Downloads/JAVA测试题目/emp.txt");
            fileOutputStream.write(sb.toString().getBytes());
            fileOutputStream.close();
        }
    }
    public static Map<String, Object> readCsvByCsvReader(File file) {
        Map<String, Object> mapData = new HashMap<>();
        String fileName = file.getName();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        mapData.put("sheetName",fileName);

        ArrayList<String> strList = new ArrayList<>();
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            ArrayList<String[]> arrList = new ArrayList<String[]>();
            // 测试中文有乱码，使用GB2312编码
            CsvReader reader = new CsvReader(file.getPath(), ',', Charset.forName("GB2312"));
            // 读取表头
            reader.readHeaders();
            String[] headArray = reader.getHeaders();//获取标题
            // System.out.println(headArray);
            while (reader.readRecord()) {
                // System.out.println(Arrays.asList(reader.getValues()));
                // 按行读取，并把每一行的数据添加到list集合
                arrList.add(reader.getValues());
            }
            reader.close();
            // System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            for (int i = 1; i < arrList.size(); i++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                Map<String, Object> map = new HashMap<>();
                for (int j=0;j<arrList.get(0).length;j++) {
                    map.put(""+arrList.get(0)[j]+"", arrList.get(i)[j]);
                }
                // 返回的数格为拼接
                /*String ele = arrList.get(i)[0] + "," + arrList.get(i)[1] + "," + arrList.get(i)[2];
                System.out.println(ele);
                strList.add(ele);*/
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapData.put("data",list);
        return mapData;
    }

    /**
     * BufferedReader 读取
     * @param filePath
     * @return
     */
    public static ArrayList<String> readCsvByBufferedReader(String filePath) {
        File csv = new File(filePath);
        csv.setReadable(true);
        csv.setWritable(true);
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(csv), "UTF-8");
            br = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        ArrayList<String> records = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                records.add(line);
            }
            System.out.println("csv表格读取行数：" + records.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(records);
        return records;
    }

}
