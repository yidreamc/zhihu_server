package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.heartbeat.HeartbeatProcessService;

@RestController
public class HeartProcessController {
    @Autowired
    private HeartbeatProcessService heartbeatProcessService;
    @GetMapping("/heartprocess")
    public Response hp (int deviceid){
       return heartbeatProcessService.HeartBeatProcess(deviceid);
    }
}
