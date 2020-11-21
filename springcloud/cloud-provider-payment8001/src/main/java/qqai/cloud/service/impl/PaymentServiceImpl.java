package qqai.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqai.cloud.dao.PaymentDao;
import qqai.cloud.entity.Payment;
import qqai.cloud.service.PaymentService;

import java.util.List;

/**
 * (Payment)表服务实现类
 *
 * @author qqai
 * @since 2020-11-15 01:29:19
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Payment queryById(Long id) {
        return this.paymentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Payment> queryAllByLimit(int offset, int limit) {
        return this.paymentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Payment payment) {
        return this.paymentDao.insert(payment);
    }

    /**
     * 修改数据
     *
     * @param payment 实例对象
     * @return 实例对象
     */
    @Override
    public Payment update(Payment payment) {
        this.paymentDao.update(payment);
        return this.queryById(payment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.paymentDao.deleteById(id) > 0;
    }
}