package tk.xmfaly.zhihu_server.heartbeat;

import java.util.List;

public interface HeartRateRepository {
    void saveHeartRate(HeartRate heartRate);
    List<HeartRate> qureryHeartRatebyId(int deviceid);
}
