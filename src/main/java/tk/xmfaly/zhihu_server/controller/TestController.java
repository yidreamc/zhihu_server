package tk.xmfaly.zhihu_server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.heartbeat.HeartRate;
import tk.xmfaly.zhihu_server.heartbeat.HeartRateRepository;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private HeartRateRepository heartRateRepository;

    @GetMapping("/initdata")
    public String init(){
        heartRateRepository.saveHeartRate(new HeartRate(100,100,""));

        return "123";
    }
}
