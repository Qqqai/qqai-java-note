package qqai.test.impl;

import org.springframework.stereotype.Service;
import qqai.test.MessageService;

/**
 * 描述：实现
 *
 * @author qqai
 * @createTime 2020-09-26 20:00
 */

@Service("MessageService")
public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage() {
        return "getMessage.....";
    }
}
