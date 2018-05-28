package tk.xmfaly.zhihu_server.repository;

import tk.xmfaly.zhihu_server.entity.Location;

public interface LocationRepository {

    void saveLocation(Location location);

    Location findLocationByEid(String eid);
}
