package qqai.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    /**
     * 笔记 这个是自动注册得关键  遍历所有引用得类然后再按照全路径名称注册到ioc容器中
     * static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {
     *
     * @param args
     * @Override public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
     * register(registry, new PackageImports(metadata).getPackageNames().toArray(new String[0]));
     * }
     */
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
