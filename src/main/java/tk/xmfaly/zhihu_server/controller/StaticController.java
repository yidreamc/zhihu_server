package tk.xmfaly.zhihu_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    @GetMapping("/showexception")
    public Object exs(){
        return "sexception";
    }
}
