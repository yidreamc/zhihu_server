package tk.xmfaly.zhihu_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class HelloWorld {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
}
