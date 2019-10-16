package com.myself.project;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.myself.project.VO.InfoVO;

public interface SMSService {

    SendSmsResponse sendSms(InfoVO info);

    QuerySendDetailsResponse querySendDetails(String bizId) ;
}
