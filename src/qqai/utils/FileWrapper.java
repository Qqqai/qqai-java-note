package qqai.utils;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/14 15:33
 * @description：文件类
 */

public class FileWrapper {

    // 变量
    private final File file;

    // 根据file对象初始化成员变量
    public FileWrapper(File file) {
        this.file = file;
    }

    // 根据path初始化成员变量
    public FileWrapper(String path) {
        this.file = new File(path);
    }

    // 获取类型  0 文件夹   1 文件  -1 不存在
    public int fileType() {
        boolean directory = this.file.isDirectory();
        boolean file = this.file.isFile();
        return directory || file ? directory ? 0 : 1 : -1;
    }

    // 如果不存在创建这个文件
    public String createFileOrDirs() throws IOException {
        boolean b = false;
        // 判断文件是否存在
        if (!this.file.exists()) {
            // 不存在
            String path = this.file.getPath();
            // 这里有问题
            // 判断是否存在后缀 .  存在.后缀就是文件类型
            if (path.split("\\.").length > 1) {
                b = this.file.createNewFile();
            } else {
                // 文件夹类型
                b = this.file.mkdir();
            }
        }
        // 判断是否创建成功
        return b ? "创建成功" : "文件或文件夹已存在";
    }

    // 获取文件夹下的所有文件
    public String[] listFiles() {
        // 判断是不是文件夹类型
        boolean b = this.file.isDirectory();
        // 0 文件夹   1 文件
        int type = b ? 0 : 1;
        File[] files;
        String[] paths = null;
        // 如果是文件
        if (type == 1) {
            // 获取到父级文件夹的路径
            String s = this.file.getParent();
            // 获取所有文件
            files = new File(s).listFiles();
        } else {
            // 文件夹类型 直接获取文件夹下的所有文件
            files = new File(file.getPath()).listFiles();
        }
        if (files != null) {
            paths = new String[files.length];
            int index = 0;
            for (File f : files) {
                // 获取到所有的文件的绝对路径
                paths[index++] = f.getAbsolutePath();
            }
        }
        return paths;
    }

    // 重载 遇上个方法类似  但是有个文件过滤器
    public String[] listFiles(FilenameFilter filter) {
        boolean b = this.file.isDirectory();
        int type = b ? 0 : 1;
        File[] files;
        String[] paths = null;
        if (type == 1) {
            String s = this.file.getParent();
            // 按照过滤器的条件获取文件
            files = new File(s).listFiles(filter);
        } else {
            // 按照过滤器的条件获取文件
            files = new File(file.getPath()).listFiles(filter);
        }
        if (files != null) {
            paths = new String[files.length];
            int index = 0;
            for (File f : files) {
                paths[index++] = f.getAbsolutePath();
            }
        }
        return paths;
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        String s = this.file.isDirectory() ? "文件夹类型" : "文件类型";
        if (this.file.isFile()) {
            s += ",文件约为" + this.file.length() + "字节";
        }
        return s;
    }

    // test
    public static void main(String[] args) throws IOException {
        FileWrapper wrapper = new FileWrapper("D:\\idea\\api\\jdk api 1.8_google.CHM");
        System.out.println(wrapper.fileType());
        System.out.println(wrapper.createFileOrDirs());
        System.out.println(Arrays.toString(wrapper.listFiles()));
        System.out.println(Arrays.toString(wrapper.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".chw");
            }
        })));
        System.out.println(wrapper.toString());
    }
}
