package com.trilogyed.TriNguyenmagiceightballservice.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RefreshScope
public class MagicEightBallServiceController {

    List<String> answers = new ArrayList<>(Arrays.asList(
            "No",
            "Yes",
            "Looking cloudy",
            "Not sure",
            "Absolutely!",
            "Ask again",
            "Ummm",
            "Not a chance"
    ));

    @GetMapping(value="/answer")
    public String magicEightBall(){
        Random random = new Random();
        return answers.get(random.nextInt(answers.size()));
    }

}
