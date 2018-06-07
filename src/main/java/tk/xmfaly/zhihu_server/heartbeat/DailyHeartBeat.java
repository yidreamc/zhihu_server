package tk.xmfaly.zhihu_server.heartbeat;

public class DailyHeartBeat {
    private int deviceid;
    private int heartrate;
    private String timestamp;

    public int getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(int deviceid) {
        this.deviceid = deviceid;
    }

    public int getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(int heartrate) {
        this.heartrate = heartrate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public DailyHeartBeat() {
    }
}
