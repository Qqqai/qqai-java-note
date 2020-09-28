package qqai.io;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author qqai
 * @createTime 2020/9/28 11:28
 * @description：
 */

public class FileDownLoadTest {
//    public void download(HttpServletResponse response, String filename) throws IOException {
//        File file = new File("src/main/resources/static/jianli/" + filename);
//        if (!file.exists()) {
//            throw new RuntimeException("文件不存在");
//        }
////        response.setContentType("application/force-download");
//        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
//        // 设置Headers
//        // response.setContentType("application/force-download");
//        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8"); // 告诉浏览器输出内容为流
////        FileUtil.download(file, response);
//
//        Files.copy(Paths.get(file.getAbsolutePath()), response.getOutputStream());
//    }
}
