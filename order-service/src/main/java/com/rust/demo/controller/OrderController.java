package com.rust.demo.controller;

import com.rust.demo.common.Result;
import com.rust.demo.feign.OperatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OperatorService operatorService;

    @GetMapping("/operator/get/{id}")
    public Object getOperator(@PathVariable("id") Long id) {
        return operatorService.get(id);
    }
}
