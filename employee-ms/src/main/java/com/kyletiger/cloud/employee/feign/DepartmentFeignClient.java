package com.kyletiger.cloud.employee.feign;

import com.kyletiger.cloud.employee.entity.Department;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// ``@FeignClient``：
// 1. 指定``name``参数用于创建Ribbon Client（i.e. ``LoadBalancerClient``）；
// 2. 由于使用了Eureka，所以Ribbon会把name配置的微服务名解析为Eureka Server里注册表中的对应服务；
// 3. 采用指定``fallbackFactory``参数用于配置降级实现；
//@FeignClient(name = "department-ms", fallbackFactory = FeignClientFallbackFactory.class)
// 微服务之间的内部调用也可统一通过API Gateway来路由，统一进行各种控制策略。
@FeignClient(name = "apigateway-zuul", fallbackFactory = FeignClientFallbackFactory.class)
@RequestMapping(value="/departments")   // 配置使下面的映射都在这个端点下
public interface DepartmentFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department findOne(@PathVariable("id") Long id);
}

/**
 * UserFeignClient的fallbackFactory类，该类需实现FallbackFactory接口，并覆写create方法
 * 产生用{@link FeignClient}备注的interface的实例。
 */
@Component
class FeignClientFallbackFactory implements FallbackFactory<DepartmentFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public DepartmentFeignClient create(Throwable fallBackCause) {
        return new DepartmentFeignClient() {
            @Override
            public Department findOne(Long id) {
                // 日志最好放在各个fallback方法中，而不要直接放在create方法中。否则在启动时，就会打印该日志。
                // 详见https://github.com/spring-cloud/spring-cloud-netflix/issues/1471
                FeignClientFallbackFactory.LOGGER.info("Fallback reason was: ", fallBackCause);
                return new Department(-1L,"<Hystrix Fallback>", "");
            }
        };
    }
}
