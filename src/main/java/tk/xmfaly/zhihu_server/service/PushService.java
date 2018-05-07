package tk.xmfaly.zhihu_server.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import tk.xmfaly.zhihu_server.lib.push.AndroidNotification;
import tk.xmfaly.zhihu_server.lib.push.PushClient;
import tk.xmfaly.zhihu_server.lib.push.android.*;


@Service
public class PushService {

    private String appkey = "5ae87975b27b0a3d78000371";
    private String appMasterSecret = "hk7ajcug0ijafoaloqs4eaqokgryq0nv";
    private String timestamp = null;
    private PushClient client = new PushClient();

    /**
     * 广播
     *
     * @throws Exception
     */
    public void sendAndroidBroadcast(String title, String text, String ticker ) throws Exception {
        AndroidBroadcast broadcast = new AndroidBroadcast(appkey, appMasterSecret);
        broadcast.setTicker(ticker);
        broadcast.setTitle(title);
        broadcast.setText(text);
        broadcast.goAppAfterOpen();
        broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        broadcast.setProductionMode();
        // Set customized fields
        //broadcast.setExtraField("test", "helloworld");
        client.send(broadcast);
    }

    /**
     * 单播
     *
     * @throws Exception
     */
    public void sendAndroidUnicast(String title, String text, String ticker,String token) throws Exception {
        AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken(token);
        unicast.setTicker(ticker);
        unicast.setTitle(title);
        unicast.setText(text);
        unicast.goAppAfterOpen();
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        unicast.setProductionMode();
        // Set customized fields
        unicast.setExtraField("test", "helloworld");
        client.send(unicast);
    }

    /**
     * 组播
     *
     * @throws Exception
     */
    public void sendAndroidGroupcast() throws Exception {
        AndroidGroupcast groupcast = new AndroidGroupcast(appkey, appMasterSecret);
        /*  TODO
         *  Construct the filter condition:
         *  "where":
         *	{
         *		"and":
         *		[
         *			{"tag":"test"},
         *			{"tag":"Test"}
         *		]
         *	}
         */
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        JSONObject testTag = new JSONObject();
        JSONObject TestTag = new JSONObject();
        testTag.put("tag", "test");
        TestTag.put("tag", "Test");
        tagArray.put(testTag);
        tagArray.put(TestTag);
        whereJson.put("and", tagArray);
        filterJson.put("where", whereJson);
        System.out.println(filterJson.toString());

        groupcast.setFilter(filterJson);
        groupcast.setTicker("Android groupcast ticker");
        groupcast.setTitle("中文的title");
        groupcast.setText("Android groupcast text");
        groupcast.goAppAfterOpen();
        groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        groupcast.setProductionMode();
        client.send(groupcast);
    }

    /**
     * 自定义广播
     *
     * @throws Exception
     */
    public void sendAndroidCustomizedcast() throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey, appMasterSecret);
        // TODO Set your alias here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        customizedcast.setAlias("alias", "alias_type");
        customizedcast.setTicker("Android customizedcast ticker");
        customizedcast.setTitle("中文的title");
        customizedcast.setText("Android customizedcast text");
        customizedcast.goAppAfterOpen();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        client.send(customizedcast);
    }

    /**
     * 自定义广播
     *
     * @throws Exception
     */
    public void sendAndroidCustomizedcastFile() throws Exception {
        AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey, appMasterSecret);
        // TODO Set your alias here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.
        String fileId = client.uploadContents(appkey, appMasterSecret, "aa" + "\n" + "bb" + "\n" + "alias");
        customizedcast.setFileId(fileId, "alias_type");
        customizedcast.setTicker("Android customizedcast ticker");
        customizedcast.setTitle("中文的title");
        customizedcast.setText("Android customizedcast text");
        customizedcast.goAppAfterOpen();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        customizedcast.setProductionMode();
        client.send(customizedcast);
    }

    public void sendAndroidFilecast() throws Exception {
        AndroidFilecast filecast = new AndroidFilecast(appkey, appMasterSecret);
        // TODO upload your device tokens, and use '\n' to split them if there are multiple tokens
        String fileId = client.uploadContents(appkey, appMasterSecret, "aa" + "\n" + "bb");
        filecast.setFileId(fileId);
        filecast.setTicker("Android filecast ticker");
        filecast.setTitle("中文的title");
        filecast.setText("Android filecast text");
        filecast.goAppAfterOpen();
        filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        client.send(filecast);
    }


}
