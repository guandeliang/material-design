package com.jacob.tools.xunfei;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jacob.tools.utils.FileUtil;
import com.jacob.tools.utils.HttpUtil;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class XunFei {

    private static final String FROM = "en";
    private static final String TO = "cn";
    private static final String FROM_FILE = "E:\\github\\material-design\\xunfei\\enfiles\\app_start_time.txt";
    private static final String TO_FILE = "E:\\github\\material-design\\xunfei\\cnfiles\\app_start_time.txt";


    public static void main(String[] args) throws Exception {
        if (XunFeiConfig.APPID.equals("") || XunFeiConfig.API_KEY.equals("") || XunFeiConfig.API_SECRET.equals("")) {
            System.out.println("Appid 或 APIKey 或 APISecret 为空！请填写相关信息。");
            return;
        }
        List<String> fromList = FileUtil.read(FROM_FILE);
        List<String> toList = new ArrayList<>();
        for (String content:fromList){
            String result = callXunFei(content);
            toList.add(result);
        }

        for (String result:toList){
            System.out.println(result);
        }

        FileUtil.write(TO_FILE, toList);

    }


    public static String callXunFei(String content) throws Exception{
        String result = null;
        String body = buildHttpBody(content);
        Map<String, String> header = XunFeiConfig.buildHttpHeader(body);

        Map<String, Object> resultMap = HttpUtil.doPost2(XunFeiConfig.WebOTS_URL, header, body);
        if (resultMap != null) {
            String resultStr = resultMap.get("body").toString();
            System.out.println("【OTS WebAPI 接口调用结果】\n" + resultStr);
            //以下仅用于调试
            Gson json = new Gson();
            XunFeiResponse resultData = json.fromJson(resultStr, XunFeiResponse.class);
            int code = resultData.code;
            if (resultData.code != 0) {
                System.out.println("请前往https://www.xfyun.cn/document/error-code?code=" + code + "查询解决办法");
            }else{
                System.out.println(resultData.data.result.trans_result.src);
                System.out.println(resultData.data.result.trans_result.dst);
                result = resultData.data.result.trans_result.dst;
            }
        } else {
            System.out.println("调用失败！请根据错误信息检查代码，接口文档：https://www.xfyun.cn/doc/nlp/niutrans/API.html");
        }
        return result;
    }



    /**
     * 组装http请求体
     */
    public static String buildHttpBody(String content) throws Exception {
        //填充common
        JsonObject common = new JsonObject();
        common.addProperty("app_id", XunFeiConfig.APPID);

        //填充business
        JsonObject business = new JsonObject();
        business.addProperty("from", FROM);
        business.addProperty("to", TO);

        //填充data
        byte[] textByte = content.getBytes("UTF-8");
        String textBase64 = new String(Base64.getEncoder().encodeToString(textByte));
        JsonObject data = new JsonObject();
        data.addProperty("text", textBase64);

        JsonObject body = new JsonObject();
        body.add("common", common);
        body.add("business", business);
        body.add("data", data);
        return body.toString();
    }







}
