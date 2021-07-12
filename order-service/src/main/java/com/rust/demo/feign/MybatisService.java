package com.rust.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "mybatis-service")
public interface MybatisService {

    @PostMapping(value = "/character/import", consumes = "multipart/form-data")
    Object importCharacter(MultipartFile file);

    @PostMapping(value = "/character/importCharacterInfo", consumes = "multipart/form-data")
    Object importCharacterInfo(MultipartFile file);

    @PostMapping(value = "/enemy/import", consumes = "multipart/form-data")
    Object importEnemy(MultipartFile file);

    @PostMapping(value = "/team/import", consumes = "multipart/form-data")
    Object importTeam(MultipartFile file);
}
