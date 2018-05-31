package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Location;
import tk.xmfaly.zhihu_server.repository.LocationRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {


    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/getLocation")
    public Response getLocation(String eid) {
        Location location = locationRepository.findLocationByEid(eid);
        Map<String,Object> map = new HashMap<>();
        map.put("longitude",location.getLongitude().trim());
        map.put("latitude",location.getLatitude().trim());
        return new Response(0, "", map);
    }
}
