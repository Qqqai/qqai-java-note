package mybatis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * student
 * @author 
 */
@Data
public class Student implements Serializable {
    private Integer sid;

    private String NAME;

    private Integer age;

    private Date birthday;

    private static final long serialVersionUID = 1L;
}