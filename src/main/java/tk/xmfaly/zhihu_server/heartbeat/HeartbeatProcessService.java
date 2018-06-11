package tk.xmfaly.zhihu_server.heartbeat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.xmfaly.zhihu_server.dto.Response;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Math.min;
@Service
public class HeartbeatProcessService {


    @Autowired
    private DailyHeartBeatRepository dailyHeartBeatRepository;

    @Autowired
    private HeartRateRepository heartRateRepository;


    /**从服务器获取数据存入vector
     TodayHeartInfo(保存昨天21:00至今天21:00采集的心跳数据) 中
     HistoryAveHeartBeat(保存用户的之前的每日平均心跳)
     算出用户今日的平均心跳与之前数据进行比较
    **/
    public Response HeartBeatProcess(int deviceid){
        List<HeartRate> HeartBeatList = new ArrayList<>();
        HeartBeatList = heartRateRepository.qureryHeartRatebyId(deviceid);
        double todayAve = 0;
        double todayMax = 0.0;
        String maxHeartDate = new String();
        String minHeartDate = new String();
        double todayMin = 10000.0;
        int Listsize = HeartBeatList.size();
        for (int i=0;i<Listsize;i++){
            HeartRate it = HeartBeatList.get(i);
            double nowHeartRate = (double)it.getHeartrate();
            todayAve += nowHeartRate;
            if(nowHeartRate>todayMax){
                todayMax = nowHeartRate;
                maxHeartDate = it.getTimestamp();
            }
            if(nowHeartRate<todayMin){
                todayMin = nowHeartRate;
                minHeartDate = it.getTimestamp();
            }

        }
        //SimpleDateFormat ymdhms = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        //String date = ymdhms.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        todayAve = todayAve/(1.0*Listsize);
        List<DailyHeartBeat> PreHeartBeatInfo = new ArrayList<>();
        PreHeartBeatInfo = dailyHeartBeatRepository.qureryHeartRatebyId(deviceid);
        SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String date = ymd.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        int high = 0,low = 0;
        if (PreHeartBeatInfo.size()!=0){
            int PreListSize = PreHeartBeatInfo.size();
            for (int i=0;i<min(PreListSize,5);++i){
                DailyHeartBeat it = PreHeartBeatInfo.get(i);
                double preHeartRate = (double)it.getHeartrate();
                if (todayAve>preHeartRate){
                    high++;
                }
                if (todayAve<preHeartRate){
                    low++;
                }
            }
        }
        DailyHeartBeat todayAveRate = new DailyHeartBeat();
        todayAveRate.setDeviceid(deviceid);todayAveRate.setHeartrate((int)todayAve);
        todayAveRate.setTimestamp(date);
        dailyHeartBeatRepository.saveHeartRate(todayAveRate);
        System.out.println("您今天的平均心率为"+todayAve);

        System.out.println("您今天的最高心率出现在"+maxHeartDate.substring(8,10)+"点"+maxHeartDate.substring(10,12)+"分");

        System.out.println("最高心率为"+todayMax);

        System.out.println("您今天的最高心率出现在"+minHeartDate.substring(8,10)+"点"+minHeartDate.substring(10,12)+"分");

        System.out.println("最低心率为"+todayMin);
        if (low>=4){
            System.out.println("您的心率比五天前的较低请注意调节身体状况");
        }
        if (high>=4){
            System.out.println("您的心率比五天前的较高请注意身体");
        }

        Map<String,Object> res = new HashMap<>();
        res.put("todayAve",todayAve);
        res.put("todayMax",todayMax);
        res.put("todayMin",todayMin);
        res.put("HeartBeatList",HeartBeatList);
        res.put("PreHeartBeatInfo",PreHeartBeatInfo);
        res.put("todayAveRate",todayAveRate);

        return new Response(0,"",res);
    }
}