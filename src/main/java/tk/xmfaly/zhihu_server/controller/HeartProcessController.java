package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.heartbeat.HeartbeatProcessService;

public class HeartProcessController {
    @Autowired
    HeartbeatProcessService heartbeatProcessService;
    @GetMapping("/heartprocess")
    public Response hp (int deviceid){
       return heartbeatProcessService.HeartBeatProcess(deviceid);
    }
}
