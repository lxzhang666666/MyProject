package com.myself.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class GameController {

    protected static Logger logger = LoggerFactory.getLogger(GameController.class);


    @RequestMapping("/GamePage")
    public String getGamePage() {
        logger.info("访问游戏");
        return "GamePage";
    }

    @RequestMapping("/start")
    @ResponseBody
    public String getStart() {
        logger.info("开始游戏");
        return "http://www.7k7k.com/index.php";
    }

}
