package tk.xmfaly.zhihu_server.heartbeat;

import java.util.List;

public interface DailyHeartBeatRepository {
    void saveHeartRate(DailyHeartBeat dailyHeartBeat);
    List<DailyHeartBeat> qureryHeartRatebyId(int deviceid);
}
