package com.myself.project.controller;


import com.alibaba.fastjson.JSON;
import com.myself.project.MyBiz;
import com.myself.project.common.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/we")
public class DataController {

    protected static Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private MyBiz myBiz;

    private static final String DATA_STR = "data";

    @RequestMapping("/DataPage")
    public String getDataPage() {
        logger.info("访问数据页");
        return "DataPage";
    }

    @RequestMapping("/getData.json")
    @ResponseBody
    public String getData(){
        List<Project> data = myBiz.getData(DATA_STR);
        logger.info("数据{}", JSON.toJSONString(data));
        return  JSON.toJSONString(data);
    }

    @PostMapping("/addData.json")
    @ResponseBody
    public boolean addData(String name){
        boolean b = myBiz.insertData(name);
        logger.info("数据{}", JSON.toJSONString(b));
        return  b;
    }

    @PostMapping("/updataData.json")
    @ResponseBody
    public boolean updataData(String name,String id){
        boolean b = myBiz.updateData(name, id);
        logger.info("数据{}", JSON.toJSONString(b));
        return  b;
    }

    @RequestMapping("/jquery/data.json")
    @ResponseBody
    public String getJqData(){
        Map<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.put("asd","das");
        Map<Object, Object> objectMap = new HashMap<Object, Object>();
        objectMap.put("result",hashMap);
        String s = JSON.toJSONString(objectMap);
        return s;

    }
}
