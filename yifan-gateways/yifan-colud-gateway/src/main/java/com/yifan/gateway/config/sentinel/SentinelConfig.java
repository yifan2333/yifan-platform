package com.yifan.gateway.config.sentinel;

import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
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

    @Override
    public void run(String... args) {
        initGatewayRules();
        initGatewayApis();
    }

    private void initGatewayRules() {
        ReadableDataSource<String, Set<GatewayFlowRule>> flowRuleDataSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-gateway-flow-rules", source -> JSON.parseObject(source, new TypeReference<Set<GatewayFlowRule>>() {}));
        GatewayRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    private void initGatewayApis() {
        ReadableDataSource<String, Set<ApiDefinition>> flowRuleDataSource = new NacosDataSource<>(nacosServerAddr, groupId,
                applicationName + "-gateway-apis", source -> JSON.parseObject(source, new TypeReference<Set<ApiDefinition>>() {}));
        GatewayApiDefinitionManager.register2Property(flowRuleDataSource.getProperty());
    }
}
