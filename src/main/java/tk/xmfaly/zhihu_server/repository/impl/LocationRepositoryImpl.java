package tk.xmfaly.zhihu_server.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import tk.xmfaly.zhihu_server.entity.Location;
import tk.xmfaly.zhihu_server.repository.LocationRepository;

@Repository
public class LocationRepositoryImpl implements LocationRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveLocation(Location location) {
        mongoTemplate.save(location);
    }
}
