package com.jungle.springcloud.service;

import com.jungle.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: zoujunwen
 * @Date: 2019/5/21
 */
//使用Feign 针对“MICROSERVICECLOUD-DEPT” 做负载均衡
//@FeignClient(value = "MICROSERVICECLOUD-DEPT")

//Feign 结合Hystrix，实现fallback和Controller解耦
@FeignClient(value = "microservicecloud-dept", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    Dept get(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    boolean add(Dept dept);

}
