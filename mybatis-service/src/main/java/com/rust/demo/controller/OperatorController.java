package com.rust.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Operator;
import com.rust.demo.service.OperatorService;
import com.rust.demo.util.ResultUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Deprecated
@RestController
public class OperatorController {

    @Resource
    private OperatorService operatorService;

    @Transactional("workspace01TransactionManager")
    @PostMapping("/operator/add")
    public Result add(@RequestBody Operator operator) {
        operatorService.save(operator);
        return ResultUtil.success();
    }

    @Transactional("workspace01TransactionManager")
    @PostMapping("/operator/update")
    public Result update(@RequestBody Operator operator) {
        operatorService.updateById(operator);
        return ResultUtil.success();
    }

    @PostMapping("/operator/get")
    public Result get(@RequestBody Operator operator) {
        Operator result = operatorService.getById(operator.getId());
        return ResultUtil.success(result);
    }

    @PostMapping("/operator/list")
    public Result list(@RequestBody Operator operator) {
        operator.setId(null);
        List<Operator> list = operatorService.list(new QueryWrapper<>(operator));
        return ResultUtil.success(list);
    }
}
