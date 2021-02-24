package qqai.anno;


/**
 * 尝试用反射获取注解
 *
 * @author qqai
 * @createTime 2020/12/27 14:36
 */
public class TestClassObjForGetAnno {
    public static void main(String[] args) {
        Class<Dog> dog = Dog.class;
        Class<MyAnno> anno = MyAnno.class;
        MyAnno myAnno = null;
        myAnno = dog.getAnnotation(MyAnno.class);
        System.out.println(myAnno == null ? 0 : myAnno);
//        Annotation[] annotations = dog.getAnnotations();
//        for (Annotation annotation : annotations) {
//            System.out.println(annotation.toString());
//        }

    }
}
