package com.rust.demo.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Character;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.entity.Story;
import com.rust.demo.service.CharacterInfoService;
import com.rust.demo.service.CharacterService;
import com.rust.demo.service.StoryService;
import com.rust.demo.util.CustomBeanUtil;
import com.rust.demo.util.ImportUtil;
import com.rust.demo.util.ResultUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CharacterController {

    @Resource
    private CharacterService characterService;
    @Resource
    private CharacterInfoService characterInfoService;
    @Resource
    private StoryService storyService;

    @Transactional("arknightsTransactionManager")
    @PostMapping("/character/import")
    public Result importCharacter(@RequestParam("file") MultipartFile file) {
        ImportUtil<Character> importUtil = new ImportUtil<>();
        importUtil.importJson(file, characterService, Character.class);
        return ResultUtil.success();
    }

    @PostMapping("/character/get")
    public Result get(@RequestBody Character entity) {
        return ResultUtil.success(characterService.selectVoById(entity.getId()));
    }

    @PostMapping("/character/list")
    public Result list(@RequestBody Character entity) {
        List<Character> characterList = characterService.list(new QueryWrapper<>(entity));
        return ResultUtil.success(characterList);
    }

    @Transactional("arknightsTransactionManager")
    @PostMapping("/character/importCharacterInfo")
    public Result importCharacterInfo(@RequestParam("file") MultipartFile file) throws IOException {
        JsonNode jsonNode = new JsonMapper().readTree(file.getInputStream());
        JsonNode handbookDict = jsonNode.get("handbookDict");
        Iterator<String> fieldNames = handbookDict.fieldNames();
        List<CharacterInfo> characterInfoList = new ArrayList<>();
        List<Story> storyList = new ArrayList<>();
        while (fieldNames.hasNext()) {
            String id = fieldNames.next();
            JsonNode node = handbookDict.get(id);
            CharacterInfo characterInfo = JSONUtil.toBean(node.toString(), CharacterInfo.class);
            characterInfo.setId(id);
            JsonNode storyTextAudios = node.get("storyTextAudio");
            int i = 0;
            for (JsonNode storyTextAudio : storyTextAudios) {
                JsonNode stories = storyTextAudio.get("stories");
                for (JsonNode storyNode : stories) {
                    JsonNode storyText = storyNode.get("storyText");
                    String string = storyText.toString();
                    if (string.contains("【")) {
                        if (i == 0) {
                            characterInfoService.setBasicInfo(characterInfo, string);
                        } else if (i == 1) {
                            characterInfoService.setPhysicalExam(characterInfo, string);
                        }
                    }
                    Story story = JSONUtil.toBean(storyTextAudio.toString(), Story.class);
                    CustomBeanUtil.copyProperties(JSONUtil.toBean(storyNode.toString(), Story.class), story);
                    story.setCharId(id);
                    storyList.add(story);
                }
                i++;
            }
            characterInfoList.add(characterInfo);
        }
        characterInfoService.remove(null);
        characterInfoService.saveBatch(characterInfoList);
        storyService.remove(null);
        storyService.saveBatch(storyList);
        return ResultUtil.success();
    }
}