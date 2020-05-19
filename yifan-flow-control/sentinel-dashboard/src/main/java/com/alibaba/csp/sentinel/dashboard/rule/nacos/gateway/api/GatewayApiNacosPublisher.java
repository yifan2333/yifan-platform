/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.nacos.api.config.ConfigService;

@Component("gatewayApiNacosPublisher")
public class GatewayApiNacosPublisher implements DynamicRulePublisher<List<ApiDefinitionEntity>> {
    @Autowired
    private ConfigService configService;

    @Override
    public void publish(String app, List<ApiDefinitionEntity> rules) throws Exception {
        NacosConfigUtil.setRuleStringToNacos(
            this.configService,
            app,
            NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX,
            rules
        );
    }
}