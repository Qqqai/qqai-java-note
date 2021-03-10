package qqai.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * 读入图片数据
 *
 * @author chenaiquan
 * @date 2021/3/1 20:55
 */
public class FileInputImagesTest {
  public static void main(String[] args) throws FileNotFoundException {
    InputStream stream1 = FileInputImagesTest.class.getResourceAsStream("./images/test.jpg");
    System.out.println(stream1);
  }
}
