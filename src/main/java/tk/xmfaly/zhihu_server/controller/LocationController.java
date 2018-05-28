package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Location;
import tk.xmfaly.zhihu_server.repository.LocationRepository;

@RestController
@RequestMapping("/location")
public class LocationController {


    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/getLocation")
    public Response getLocation(String eid) {
        Location location = locationRepository.findLocationByEid(eid);

        System.out.println(location.getLatitude());
        return new Response(0, "", "{longitude:" +
                location.getLongitude() + ",latitude:" + location.getLatitude() + "}");
    }
}
