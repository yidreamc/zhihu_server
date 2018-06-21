package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.service.PushService;

@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private PushService pushService;

    @GetMapping("/help")
    public Object help() throws Exception {
        pushService.sendAndroidBroadcast("有老人在您附近寻求帮助","老人在您附近寻求帮助，请给予帮助！","老人在您附近寻求帮助，请给予帮助！");
        return "1";
    }

    @GetMapping("/diedao")
    public Object diedao() throws Exception {
        pushService.sendAndroidBroadcast("您的老人发生了跌倒！","您的老人发生了跌倒，请及时确认！","您的老人发生了跌倒，请及时确认！");
        return "1";
    }

    @GetMapping("/xinlv")
    public Object xinlv() throws Exception {
        pushService.sendAndroidBroadcast("您的老人有心率异常","您的老人有心率异常，请及时确认！","您的老人有心率异常，请及时确认！");
        return "1";
    }
}
