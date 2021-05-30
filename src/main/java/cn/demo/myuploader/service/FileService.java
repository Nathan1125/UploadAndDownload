package cn.demo.myuploader.service;

import cn.demo.myuploader.config.UploadConfig;

import cn.demo.myuploader.model.File;
import cn.demo.myuploader.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.demo.myuploader.dao.FileDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static cn.demo.myuploader.utils.FileUtils.generateFileName;
import static cn.demo.myuploader.utils.UploadUtils.*;

/**
 * 文件上传服务
 */
@Service
public class FileService {
    @Autowired
    private FileDao fileDao;


    /**
     * 上传文件
     * @param md5
     * @param file
     */
    public void upload(String name,
                       String md5,
                       MultipartFile file) throws IOException {
        String path = UploadConfig.path + generateFileName();
        FileUtils.write(path, file.getInputStream());
        fileDao.save(new File(name, md5, path, new Date()));
    }


    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
    public boolean checkMd5(String md5) {
        File file = new File();
        file.setMd5(md5);
        return fileDao.getByFile(file) == null;
    }
}
