package qqai.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqai.cloud.common.R;

import java.util.Objects;

/**
 * @author qqai
 * @createTime 2020/11/28 18:31
 * @description：从配置中心读取配置文件
 */

@RestController
@RequestMapping("/config")
// 动态修改配置
@RefreshScope
public class ConfigClientController {

    @Value("${server.port}")
    private Integer port;

    @Value(value = "${config.info}")
    private String configInfo;

    @GetMapping("/info")
    public R info() {
        return Objects.requireNonNull(R.ok().put("info", configInfo)).put("port", port);
    }
}
