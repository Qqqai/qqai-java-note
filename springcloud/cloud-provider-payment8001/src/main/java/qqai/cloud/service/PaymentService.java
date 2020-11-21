package qqai.cloud.service;

import qqai.cloud.entity.Payment;

import java.util.List;

/**
 * (Payment)表服务接口
 *
 * @author qqai
 * @since 2020-11-15 01:29:19
 */
public interface PaymentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Payment queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Payment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    int insert(Payment payment);

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    Payment update(Payment payment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}