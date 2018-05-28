package tk.xmfaly.zhihu_server.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import tk.xmfaly.zhihu_server.entity.Location;
import tk.xmfaly.zhihu_server.repository.LocationRepository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveLocation(Location location) {
        mongoTemplate.save(location);
    }

    @Override
    public Location findLocationByEid(String eid) {
        Query query = new Query(Criteria.where("uid").is(eid));
        List<Location> locations = mongoTemplate.find(query, Location.class);

        if (locations.size() != 0) {
            return locations.get(locations.size() - 1);
        }

        return null;


    }
}
