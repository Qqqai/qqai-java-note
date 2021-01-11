package qqai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    /**
     * 笔记 ：AutoConfigurationImportSelector.class
     *
     * @Override public String[] selectImports(AnnotationMetadata annotationMetadata) {
     * if (!isEnabled(annotationMetadata)) {
     * return NO_IMPORTS;
     * }
     * AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(annotationMetadata);
     * return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
     * }
     */
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
