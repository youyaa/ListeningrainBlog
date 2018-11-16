package listeningrain.cn.blog.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/6 17:03
 * Description:
 */
@ConfigurationProperties
public class StorageProperties {

    private String location = "static/upload-dir";

    public String getLocation() {

        return location;

    }

    public void setLocation(String location) {

        this.location = location;

    }
}
