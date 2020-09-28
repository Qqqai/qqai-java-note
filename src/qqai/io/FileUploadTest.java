package qqai.io;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author qqai
 * @createTime 2020/9/28 09:00
 * @description：测试上传
 */

public class FileUploadTest {
    /**
     * springMVC方式
     *
     * @param
     * @return
     */
//    @RequestMapping("testSpringFile")   //只能和前台的fileinput框的name值相同
//    public String testSpringFile(HttpServletRequest request, MultipartFile upload) throws IOException {
//        System.out.println("testSpringFile执行了");
//        //获取上传目录
//        String path = request.getSession().getServletContext().getRealPath("/uploads/");
//        File file = new File(path);
//        if (!file.exists()) {//判断该路径是否存在
//            file.mkdirs();//创建文件夹
//        }
//        //获取上传文件名字
//        String filename = upload.getOriginalFilename();
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        //唯一id
//        filename = uuid + filename;
//        upload.transferTo(new File(path, filename));
//        return "success";
//    }
}
