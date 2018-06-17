package tk.xmfaly.zhihu_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.News;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsController {

    @GetMapping("/news")
    public Object news(){
        return "{\n" +
                "\t\"code\": 0,\n" +
                "\t\"msg\": \"\",\n" +
                "\t\"data\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"近三分之一老人因社交稀少等原因有抑郁症状\",\n" +
                "\t\t\t\"url\": \"http://news.cctv.com/2016/12/05/ARTIU7FHRoz86y5QhQoJoCHm161205.shtml\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"10位百岁老人的长寿法则\",\n" +
                "\t\t\t\"url\": \"http://www.yangtse.com/app/health/2017-12-04/489317.html\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"黄昏恋 给老年人带来安慰\",\n" +
                "\t\t\t\"url\": \"http://health.people.com.cn/n1/2017/0816/c14739-29474086.html\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"80岁老人的健康生活\",\n" +
                "\t\t\t\"url\": \"http://www.zkqiang.com/sitegroup/root/html/ff808081497560d30149787988eb0071/1f3c112f30f94f7484954696588a6bb2.html\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"title\": \"智慧健康养老给老人“大幸福”\",\n" +
                "\t\t\t\"url\": \"http://app.yzinter.com/Special/xczjc1/2018-02-28/524633.html\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "\t\n" +
                "}";
    }

}
