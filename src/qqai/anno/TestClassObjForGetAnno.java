package qqai.anno;


/**
 * 尝试用反射获取注解
 *
 * @author qqai
 * @createTime 2020/12/27 14:36
 */
public class TestClassObjForGetAnno {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<Dog> dog = Dog.class;
        Class<MyAnno> anno = MyAnno.class;
        MyAnno myAnno = null;
        try {
//            myAnno = dog.getAnnotation(MyAnno.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println((Boolean) (myAnno == null ? 0 : myAnno));
//        Annotation[] annotations = dog.getAnnotations();
//        for (Annotation annotation : annotations) {
//            System.out.println(annotation.toString());
//        }

    }
}
