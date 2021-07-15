package com.rust.demo.util;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ImportUtil<T> {

    public void importJson(MultipartFile file, IService<T> service, Class<T> clazz) {
        JsonNode jsonNode = null;
        try {
            jsonNode = new JsonMapper().readTree(file.getInputStream());
        } catch (IOException e) {
            return;
        }
        Iterator<String> fieldNames = jsonNode.fieldNames();
        List<T> entityList = new ArrayList<>();
        while (fieldNames.hasNext()) {
            String id = fieldNames.next();
            T entity = JSONUtil.toBean(jsonNode.get(id).toString(), clazz);
            dealFields(entity, id);
            entityList.add(entity);
        }
        service.remove(null);
        service.saveBatch(entityList);
    }

    private void dealFields(T entity, String id) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                switch (field.getName()) {
                    case "id":
                        field.set(entity, id);
                        break;
                    case "tagList":
                    case "enemyTags":
                        field.set(entity, dealTags(field.get(entity).toString()));
                        break;
                    default:
                        break;
                }
            } catch (IllegalAccessException | NullPointerException e) {
            }
        }
    }

    private String dealTags(String tags) {
        return StringUtils.hasText(tags) ? null : tags.replaceAll("[\\[\\]\"]", "");
    }
}
