package qqai;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * student
 *
 * @author
 */

@Data
@Accessors(chain = true)
public class Student implements Serializable {
    private Integer sid;

    private String name;

    private Integer age;

    private Date birthday;

    private static final long serialVersionUID = 1L;

}