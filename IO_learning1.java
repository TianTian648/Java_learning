import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IO_learning1 {
    public static void main(String[] args) throws IOException {
        /*
         制造假数据：
             获取姓氏：https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0
             获取男生名字：http://www.haoming8.cn/baobao/10881.html
             获取女生名字：http://www.haoming8.cn/baobao/7641.html
        */
        //1 定义变量记录网址
        String familyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet = "http://www.haoming8.cn/baobao/7641.html";
        //2 爬取数据
        String familyNameStr = webCrawler(familyNameNet);
        String boyNameStr = webCrawler(boyNameNet);
        String girlNameStr = webCrawler(girlNameNet);

        //3正则表达式提取数据
        ArrayList<String> familyName = getData(familyNameStr, ".{4}(?=，|。)");
        ArrayList<String> boyName = getData(boyNameStr, "([\u4E00-\u9FA5]{2})(?=、)");
        ArrayList<String> girlName = getData(girlNameStr, "(.{2}\\s){4}");
        //4处理数据
        ArrayList<String> familyNameList = new ArrayList<>();
        for (String s : familyName) {
            for (int i = 0; i < s.length(); i++) {
                familyNameList.add(s.charAt(i) + "");
            }
        }
        ArrayList<String> boyNameList = new ArrayList<>();
        for (String s : boyName) {
            if (!boyNameList.contains(s)) {
                boyNameList.add(s);
            }
        }
        ArrayList<String> girlNameList = new ArrayList<>();
        for (String s : girlName) {
            String[] split = s.split(" ");
            for (String s1 : split) {
                girlNameList.add(s1);
            }
        }

        //5生成数据
        ArrayList<String> name = getName(familyNameList, boyNameList, girlNameList, 35, 25);
        Collections.shuffle(name);
        //6 数据写入
        BufferedWriter br = new BufferedWriter(new FileWriter("name.txt"));
        for (String s : name) {
            br.write(s);
            br.newLine();
        }
        br.close();
    }

    private static ArrayList<String> getName(ArrayList<String> familyNameList, ArrayList<String> boyNameList, ArrayList<String> girlNameList, int boyNumber, int girlNumber) {
        ArrayList<String> nameList = new ArrayList<>();
        for (int i = 0; i < boyNumber; i++) {
            Collections.shuffle(familyNameList);
            Collections.shuffle(boyNameList);
            String s = familyNameList.get(0) + boyNameList.get(0);
            nameList.add(s);
        }
        for (int i = 0; i < girlNumber; i++) {
            Collections.shuffle(familyNameList);
            Collections.shuffle(girlNameList);
            String s = familyNameList.get(0) + girlNameList.get(0);
            nameList.add(s);
        }
        return nameList;
    }

    private static ArrayList<String> getData(String str, String regex) {
        ArrayList<String> list = new ArrayList<>();
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            list.add(group);
        }
        return list;
    }

    public static String webCrawler(String net) throws IOException {
        //定义StringBuilder对象
        StringBuilder sb = new StringBuilder();
        URL url = new URL(net);
        URLConnection urlConnection = url.openConnection();
        //读取数据
        InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }
        return sb.toString();
    }
}
