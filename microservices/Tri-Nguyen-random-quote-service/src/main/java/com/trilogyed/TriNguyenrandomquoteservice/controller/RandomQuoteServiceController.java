package com.trilogyed.TriNguyenrandomquoteservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RefreshScope
public class RandomQuoteServiceController {

    Random random = new Random();
    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${answerServiceName}")
    private String answerServiceName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    @Value("${officialGreeting}")
    private String officialGreeting;

    @GetMapping(value="/quoteGreeting")
    public String quoteGreeting(){
        return officialGreeting;
    }

//    Issue the following ```curl``` command in the terminal to refresh http://locahost:2244/quoteGreeting to see the new greeting message.
//    curl localhost:2244/actuator/refresh -d {} -H "Content-Type: application/json"

//    Random random = new Random();
//
//    @GetMapping(value="/quote")
//    public String getARandomQuote(){
//        ArrayList<String> quoteList = new ArrayList<>(Arrays.asList(
//            "To me programming is more than an important practical art. It is also a gigantic undertaking in the foundations of knowledge. —Grace Hopper",
//
//            "Programs must be written for people to read, and only incidentally for machines to execute. —Hal Ableson",
//
//            "Don't call me the mother of the internet. —Radia Perlman",
//
//            "When I first started using the phrase software engineering, it was considered to be quite amusing. They used to kid me about my radical ideas. Software eventually and necessarily gained the same respect as any other discipline. —Margaret Hamilton",
//
//            "Machines take me by surprise with great frequency. —Alan Turing",
//
//            "The function of good software is to make the complex appear simple. —Grady Booch",
//
//            "An API that isn't comprehensible isn't usable. —James Gosling",
//
//            "I'm not a great programmer; I'm just a good programmer with great habits. —Martin Fowler"
//        ));
//        return quoteList.get(random.nextInt(quoteList.size()));
//    }

    List<String> quoteList;
    public RandomQuoteServiceController(){
        quoteList = new ArrayList<>(Arrays.asList(
                "To me programming is more than an important practical art. It is also a gigantic undertaking in the foundations of knowledge. —Grace Hopper",
                "Programs must be written for people to read, and only incidentally for machines to execute. —Hal Ableson",

                "Don't call me the mother of the internet. —Radia Perlman",

                "When I first started using the phrase software engineering, it was considered to be quite amusing. They used to kid me about my radical ideas. Software eventually and necessarily gained the same respect as any other discipline. —Margaret Hamilton",

                "Machines take me by surprise with great frequency. —Alan Turing",

                "The function of good software is to make the complex appear simple. —Grady Booch",

               "An API that isn't comprehensible isn't usable. —James Gosling",

                "I'm not a great programmer; I'm just a good programmer with great habits. —Martin Fowler"
        ));
    }

    @GetMapping(value="/answerme")
    public String helloCloud(){

        List<ServiceInstance> instances = discoveryClient.getInstances(answerServiceName);

        //                       http://                localhost                :          2244                    /quote
        String quoteServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;
        String magicEightBallAnswer = restTemplate.getForObject(quoteServiceUri, String.class);
        String returnVal = "The Magic Eight Ball says " + magicEightBallAnswer;
        return returnVal;
    }

    @GetMapping(value="/quote")
    public String getQuote() {
        Random random = new Random();
        return quoteList.get(random.nextInt(quoteList.size()));
    }

    @PostMapping(value="/quote")
    public String addQuote(@RequestBody String newQuote) {
        quoteList.add(newQuote);
        return newQuote;
    }
}
