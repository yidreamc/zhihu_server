package tk.xmfaly.zhihu_server.service.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("filestorage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "img";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
