package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Help;
import tk.xmfaly.zhihu_server.repository.HelpRepository;
import tk.xmfaly.zhihu_server.service.PushService;

@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private PushService pushService;

    @Autowired
    private HelpRepository helpRepository;


    @GetMapping("/help")
    public Object help() throws Exception {
        Help help = new Help("120.181788","35.949482","有老人在您附近寻求帮助");
        help = helpRepository.save(help);
        pushService.sendAndroidBroadcast("有老人在您附近寻求帮助","老人在您附近寻求帮助，请给予帮助！",String.valueOf(help.getId()));
        return "1";
    }

    @GetMapping("/diedao")
    public Object diedao() throws Exception {
//        Help help = new Help("120.181788","35.949482","有老人在附近发生了跌倒");
//        helpRepository.save(help);
        pushService.sendAndroidBroadcast("您的老人发生了跌倒！","您的老人发生了跌倒，请及时确认！","您的老人发生了跌倒，请及时确认！");
        return "1";
    }

    @GetMapping("/xinlv")
    public Object xinlv() throws Exception {
//        Help help = new Help("120.181788","35.949482","附近有老人心率异常");
//        helpRepository.save(help);
        pushService.sendAndroidBroadcast("您的老人有心率异常","您的老人有心率异常，请及时确认！","您的老人有心率异常，请及时确认！");
        return "1";
    }


    @GetMapping("/zc")
    public Object zc() throws Exception {
//        Help help = new Help("120.181788","35.949482","附近有老人走出围栏");
//        helpRepository.save(help);
        pushService.sendAndroidBroadcast("您的老人走出了电子围栏","您的老人走出了电子围栏，请及时确认！","您的老人走出了电子围栏，请及时确认！");
        return "1";
    }

    @GetMapping("/delethelp")
    public Response del(int id){
        helpRepository.delete(id);
        return new Response(0,"");
    }

    @GetMapping("/allhelp")
    public Response allhelp(){
        Iterable iterable = helpRepository.findAll();
        return new Response(0,"",iterable);
    }
}
