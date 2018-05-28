package tk.xmfaly.zhihu_server.heartbeat;

import com.sun.org.apache.bcel.internal.generic.DADD;
import org.hibernate.boot.jaxb.internal.stax.HbmEventReader;
import org.springframework.beans.factory.annotation.Autowired;

import  java.math.*;
import java.text.SimpleDateFormat;
import  java.util.*;

import static java.lang.Math.min;

public class HeartbeatProcessService {
    @Autowired
    private HeartRateRepository heartRateRepository;
    /**从服务器获取数据存入vector
     TodayHeartInfo(保存昨天21:00至今天21:00采集的心跳数据) 中
     HistoryAveHeartBeat(保存用户的之前的每日平均心跳)
     算出用户今日的平均心跳与之前数据进行比较
    **/
    public int HeartBeatProcess(int deviceid){
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
                maxHeartDate = it.getTimestemp();
            }
            if(nowHeartRate<todayMin){
                todayMin = nowHeartRate;
                minHeartDate = it.getTimestemp();
            }

        }
        //SimpleDateFormat ymdhms = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        //String date = ymdhms.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        todayAve = todayAve/(1.0*Listsize);
        List<HeartRate> PreHeartBeatInfo = new ArrayList<>();
        int high = 0,low = 0;
        int PreListSize = PreHeartBeatInfo.size();
        for (int i=0;i<min(PreListSize,5);++i){
            HeartRate it = PreHeartBeatInfo.get(i);
            double preHeartRate = (double)it.getHeartrate();
            if (todayAve>preHeartRate){
                high++;
            }
            if (todayAve<preHeartRate){
                low++;
            }
        }
        System.out.println("您今天的平均心率为"+todayAve);

        System.out.println("您今天的最高心率出现在"+maxHeartDate.substring(8,10)+"点"+maxHeartDate.substring(10,12)+"分");

        System.out.println("最高心率为"+todayAve);

        System.out.println("您今天的最高心率出现在"+minHeartDate.substring(8,10)+"点"+minHeartDate.substring(10,12)+"分");

        System.out.println("最低心率为"+todayAve);
        if (low>=4){
            System.out.println("您的心率比五天前的较低请注意调节身体状况");
        }
        if (high>=4){
            System.out.println("您的心率比五天前的较高请注意身体");
        }
        return 0;
    }
}