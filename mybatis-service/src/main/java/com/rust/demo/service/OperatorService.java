package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.Operator;
import com.rust.demo.mapper.workspace01.OperatorMapper;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class OperatorService extends ServiceImpl<OperatorMapper, Operator> {
}