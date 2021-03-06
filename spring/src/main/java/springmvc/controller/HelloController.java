package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author qqai
 * @createTime 2020/9/28 13:30
 * @description：测试
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile upload, String name, HttpServletRequest request) throws IOException {
        System.out.println(upload.getOriginalFilename());
        //获取上传目录
        String path = request.getSession().getServletContext().getRealPath("/uploads/" + name + "/");
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {//判断该路径是否存在
            file.mkdirs();//创建文件夹
        }
        //获取上传文件名字
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //唯一id
        filename = uuid + filename;
        // 标记 springmvc提供的文件操作  基于字节流的读写方式
        upload.transferTo(new File(path, filename));
        return "success";
    }

    @GetMapping("/down/{filename}")
    @ResponseBody
    public String down(@PathVariable String filename, HttpServletResponse response) throws IOException {
//        System.out.println(filename);
        String path = "D:\\idea\\sources\\jvm\\qqai\\spring\\target\\spring\\uploads\\qqai\\" + filename;
        File file = new File(path += ".md");
//        System.out.println(path);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }
//        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
        // 设置Headers
        response.setHeader("Content-Type", "application/octet-stream;charset=utf-8"); // 告诉浏览器输出内容为流
//        FileUtil.download(file, response);
        // 标记 nio的下载方式
        Files.copy(Paths.get(file.getAbsolutePath()), response.getOutputStream());
        return "success";
    }

    @GetMapping("/attr")
    @ResponseBody
    public String attr(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        request.setAttribute("name", "qqai");
        redirectAttributes.addFlashAttribute("name", "qqai");
        return "true";
    }

}
