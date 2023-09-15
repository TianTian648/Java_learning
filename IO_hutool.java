import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class IO_hutool {
    public static void main(String[] args) {
        String familyNameNet = "https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet = "http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet = "http://www.haoming8.cn/baobao/7641.html";
        //1 爬取数据
        String familyNameStr = HttpUtil.get(familyNameNet);
        String boyNameStr = HttpUtil.get(boyNameNet);
        String girlNameStr = HttpUtil.get(girlNameNet);

        //正则表达式提取数据
//        ArrayList<String> familyName = getData(familyNameStr, ".{4}(?=，|。)");
//        ArrayList<String> boyName = getData(boyNameStr, "([\u4E00-\u9FA5]{2})(?=、)");
//        ArrayList<String> girlName = getData(girlNameStr, "(.{2}\\s){4}");
        List<String> familyName = ReUtil.findAll("(.{4})(，|。)",familyNameStr,1);
        List<String> boyName = ReUtil.findAll("([\\u4E00-\\u9FA5]{2})(?=、)",boyNameStr,0);
        List<String> girlName = ReUtil.findAll("(.. ){4}..",girlNameStr,1);
        System.out.println(familyName);


    }
}
