package com.myself.project.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.myself.project.VO.InfoVO;
import com.myself.project.impl.SMSServiceImpl;
import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class SMSController {

    protected static Logger logger = LoggerFactory.getLogger(SMSController.class);

    @Autowired
    private SMSServiceImpl smsService;

    @RequestMapping("index")
    public String getIndex(){
        logger.info("访问首页");
        return "index";
    }

    @RequestMapping("SMSPage")
    public String getSMSPage() {
        logger.info("访问短信页");
        return "SMSPage";
    }



    @PostMapping("/sendSMS")
    @ResponseBody
    public String sendSMS( InfoVO infoVO) {
        logger.info("infoVO  {}", infoVO);
        SendSmsResponse sendSmsResponse = smsService.sendSms(infoVO);
        logger.info("sendSmsResponse  {} " ,sendSmsResponse.getMessage());
        return sendSmsResponse.getMessage();
    }
}
