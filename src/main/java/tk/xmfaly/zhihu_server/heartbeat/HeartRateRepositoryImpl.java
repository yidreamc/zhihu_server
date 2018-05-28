package tk.xmfaly.zhihu_server.heartbeat;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@Repository

public class HeartRateRepositoryImpl implements HeartRateRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveHeartRate(HeartRate heartRate) {mongoTemplate.save(heartRate);}

    @Override
    public List<HeartRate> qureryHeartRatebyId(int deviceid) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
        query.addCriteria(Criteria.where("deviceid").regex(String.valueOf(deviceid)));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"timestamp")));
        List<HeartRate> res = mongoTemplate.find(query,HeartRate.class);
        return res;
    }
}
