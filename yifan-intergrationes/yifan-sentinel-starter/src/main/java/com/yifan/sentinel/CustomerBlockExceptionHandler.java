package com.yifan.sentinel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import com.yifan.common.utils.ResponseUtils;
import com.yifan.web.result.ActionResult;
import com.yifan.web.result.ResultType;

/**
 * 再bean加载完成后，修改SentinelWebMvcConfig对象，自定义异常返回值
 *
 * @author wuyifan
 * @date 2020年05月14日 18:27
 */
public class CustomerBlockExceptionHandler extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public boolean postProcessAfterInstantiation(@Nullable Object bean, @Nullable String beanName) throws BeansException {
        if((bean instanceof SentinelWebMvcConfig)){
            SentinelWebMvcConfig sentinelWebMvcConfig = (SentinelWebMvcConfig) bean;
            if (sentinelWebMvcConfig.getBlockExceptionHandler() != null) {
                sentinelWebMvcConfig.setBlockExceptionHandler(((request, response, e) -> {
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    ResponseUtils.responseJsonWriter(response,
                            new ActionResult.Builder<String>().resultType(ResultType.TOO_MANY_REQUESTS).build());
                }));
            }
        }
        return true;
    }

}
