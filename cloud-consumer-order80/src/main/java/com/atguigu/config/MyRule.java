package com.atguigu.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule{
    @Bean
    public IRule getRule(){
//        return new RoundRobinRule();
//        return new RetryRule();
        return new RandomRule();
    }
}
