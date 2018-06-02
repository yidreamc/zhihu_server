package tk.xmfaly.zhihu_server.heartbeat;

public class HeartRate {
    private int deviceid;
    private int heartrate;
    private String timestemp;

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

    public String getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(String timestemp) {
        this.timestemp = timestemp;
    }

    public HeartRate(int deviceid, int heartrate, String timestemp) {
        this.deviceid = deviceid;
        this.heartrate = heartrate;
        this.timestemp = timestemp;
    }

    public HeartRate() {
    }
}
