package com.rust.demo.feign;

import com.rust.demo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "mybatis-service")
public interface MybatisService {

    @PostMapping(value = "/character/import", consumes = "multipart/form-data")
    Result importCharacter(MultipartFile file);

    @PostMapping(value = "/character/importCharacterInfo", consumes = "multipart/form-data")
    Result importCharacterInfo(MultipartFile file);

    @PostMapping(value = "/enemy/import", consumes = "multipart/form-data")
    Result importEnemy(MultipartFile file);

    @PostMapping(value = "/team/import", consumes = "multipart/form-data")
    Result importTeam(MultipartFile file);
}
