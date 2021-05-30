package cn.demo.myuploader.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class UploadConfig {

    public static String path;

    @Value("${upload.path}")
    public void setPath(String path) {
        UploadConfig.path = path;
    }
}
