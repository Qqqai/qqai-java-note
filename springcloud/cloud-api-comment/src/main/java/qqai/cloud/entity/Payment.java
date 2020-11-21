package qqai.cloud.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qqai
 * @createTime 2020/11/17 22:35
 * @description：实体
 */

@Data
public class Payment implements Serializable {
    private static final long serialVersionUID = 257871455308086797L;
    /**
     * id
     */
    private Long id;

    private String serial;

}