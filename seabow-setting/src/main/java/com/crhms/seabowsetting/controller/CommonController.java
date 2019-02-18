package com.crhms.seabowsetting.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.crhms.seabowsetting.annotation.NonWrapperResultAnnotation;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class CommonController {

    private static final String APP_ID_FACE ="15567420";
    private static final String API_KEY_FACE ="6jnCLDLEox5SsGfktlWhYZqx";
    private static final String SECRET_KEY_FACE ="Q0CY2upbpxxVuPkTNwqkxOWYMDpCwwwI";


    @GetMapping("face")
    public  String face(){
        return "common/face";
    }

    //005.人脸识别登录
    @NonWrapperResultAnnotation
    @ResponseBody
    @RequestMapping(value = "faceLogin",method = RequestMethod.POST)
    public Map faceLogin(@RequestParam("snapData") String data, @RequestParam("userId")String userId) throws Exception{
        //使用Ajax提交base64字符串，需要经过去头转码
        String img_data = data.substring(22, data.length());
        AipFace client = new AipFace(APP_ID_FACE, API_KEY_FACE, SECRET_KEY_FACE);
        HashMap<String,String> options = new HashMap<String,String>();  //请求预置参数
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("user_id", userId); //与后台用户ID匹配
        options.put("max_user_num", "3");

        JSONObject res = client.search(img_data,"BASE64","family", options);    //人脸库搜索 family为后台用户组名称
        System.out.println(res.toString(2));

        Map map = JSON.parseObject(res.toString());
        return map;
    }

    //005.人脸识别登录
    @NonWrapperResultAnnotation
    @ResponseBody
    @RequestMapping(value = "faceCheck",method = RequestMethod.POST)
    public Map faceCheck(@RequestParam("snapData") String data) throws Exception{
        //使用Ajax提交base64字符串，需要经过去头转码
        String img_data = data.substring(22, data.length());
        AipFace client = new AipFace(APP_ID_FACE, API_KEY_FACE, SECRET_KEY_FACE);

        JSONObject res = client.detect(img_data,"BASE64", new HashMap<String,String>());    //检测出人脸的位置
        System.out.println(res.toString(2));

        Map map = JSON.parseObject(res.toString());
        return map;
    }

}