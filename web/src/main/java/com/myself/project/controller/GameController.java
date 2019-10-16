package com.myself.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class GameController {

    protected static Logger logger = LoggerFactory.getLogger(GameController.class);


    @RequestMapping("/GamePage")
    public String getGamePage() {
        logger.info("访问游戏");
        return "GamePage";
    }

}
