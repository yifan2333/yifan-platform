package com.yifan.sentinel;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * The type Sentinel config.
 *
 * @author wuyifan
 * @date 2020年05月14日 18:28
 */
@Configuration
public class SentinelConfig implements CommandLineRunner {



    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServerAddr;

    @Value("${spring.application.name}")
    private String applicationName;

    private static final String groupId = "SENTINEL_GROUP";

    /**
     * Customer block exception handler customer block exception handler.
     *
     * @return the customer block exception handler
     * @author wuyifan
     * @date 2020年05月14日 18:28
     */
    @Bean
    public CustomerBlockExceptionHandler customerBlockExceptionHandler() {
        return new CustomerBlockExceptionHandler();
    }

    @Override
    public void run(String... args) {
        loadFlowRule();
        loadDegradeRule();
        loadAuthorityRule();
        loadParamFlowRule();
        loadSystemRule();
    }

    /**
     * Load flow rule.
     *
     * @author wuyifan
     * @date 2020年05月14日 18:28
     */
    private void loadFlowRule() {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-flow-rules", source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    /**
     * Load degrade rule.
     *
     * @author wuyifan
     * @date 2020年05月14日 18:28
     */
    private void loadDegradeRule() {
        ReadableDataSource<String, List<DegradeRule>> degradeRuleSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-degrade-rules", source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));
        DegradeRuleManager.register2Property(degradeRuleSource.getProperty());
    }

    /**
     * Load param flow rule.
     *
     * @author wuyifan
     * @date 2020年05月14日 18:28
     */
    private void loadParamFlowRule() {
        ReadableDataSource<String, List<ParamFlowRule>> paramFlowRuleDataSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-param-flow-rules", source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {}));
        ParamFlowRuleManager.register2Property(paramFlowRuleDataSource.getProperty());
    }

    /**
     * Load authority rule.
     *
     * @author wuyifan
     * @date 2020年05月14日 18:28
     */
    private void loadAuthorityRule() {
        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-authority-rules", source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
        }));
        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());
    }

    /**
     * Load system rule.
     *
     * @author wuyifan
     * @date 2020年05月14日 18:28
     */
    private void loadSystemRule() {
        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-system-rules", source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {}));
        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
    }
}
