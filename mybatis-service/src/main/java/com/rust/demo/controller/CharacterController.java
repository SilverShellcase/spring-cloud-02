package com.rust.demo.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rust.demo.common.Result;
import com.rust.demo.entity.Character;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.service.CharacterInfoService;
import com.rust.demo.service.CharacterService;
import com.rust.demo.util.ImportUtil;
import com.rust.demo.util.RegexUtil;
import com.rust.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterInfoService characterInfoService;

    @PostMapping("/character/importJson")
    public Result importJson(@RequestParam("file") MultipartFile file) {
        ImportUtil<Character> importUtil = new ImportUtil<>();
        importUtil.importJson(file, characterService, Character.class);
        return ResultUtil.success();
    }

    @PostMapping("/character/get")
    public Result get(@RequestBody Character character) {
        return ResultUtil.success(characterService.selectVoById(character.getId()));
    }

    @PostMapping("/character/list")
    public Result list(@RequestBody Character character) {
        List<Character> characterList = characterService.list(new QueryWrapper<>(character));
        return ResultUtil.success(characterList);
    }

    @PostMapping("/character/importInfoJson")
    public Result importInfoJson(@RequestParam("file") MultipartFile file) throws IOException {
        JsonNode jsonNode = new JsonMapper().readTree(file.getInputStream());
        JsonNode handbookDict = jsonNode.get("handbookDict");
        Iterator<String> fieldNames = handbookDict.fieldNames();
        List<CharacterInfo> characterInfoList = new ArrayList<>();
        while (fieldNames.hasNext()) {
            String id = fieldNames.next();
            JsonNode node = handbookDict.get(id);
            CharacterInfo characterInfo = JSONUtil.toBean(node.toString(), CharacterInfo.class);
            characterInfo.setId(id);
            JsonNode storyTextAudios = node.get("storyTextAudio");
            int i = 0;
            for (JsonNode storyTextAudio : storyTextAudios) {
                JsonNode stories = storyTextAudio.get("stories");
                for (JsonNode story : stories) {
                    JsonNode storyText = story.get("storyText");
                    String string = storyText.toString();
                    if (!string.contains("【")) {
                        break;
                    }
                    if (i == 0) {
                        setBasicInfo(characterInfo, string);
                    } else {
                        setPhysicalExam(characterInfo, string);
                    }
                    break;
                }
                if (i++ >= 1) {
                    break;
                }
            }
            characterInfoList.add(characterInfo);
        }
        characterInfoService.remove(null);
        characterInfoService.saveBatch(characterInfoList);
        return ResultUtil.success();
    }

    private void setBasicInfo(CharacterInfo info, String str) {
        if (str.contains("【代号】")) {
            info.setCodeName(dealBracket(str, "(【代号】.*?\\\\n)"));
            info.setGender(dealBracket(str, "(【性别】.*?\\\\n)"));
            info.setCombatExperience(dealBracket(str, "(【战斗经验】.*?\\\\n)"));
            info.setPlaceOfBirth(dealBracket(str, "(【出身地】.*?\\\\n)"));
            info.setDateOfBirth(dealBracket(str, "(【生日】.*?\\\\n)"));
            info.setRace(dealBracket(str, "(【种族】.*?\\\\n)"));
            info.setHeight(dealBracket(str, "(【身高】.*?\\\\n)"));
            info.setInfectionStatus(RegexUtil.retain(str, "(【矿石病感染情况】.*?\\\\n.*?(\"|\\\\n))").replaceAll("【.*?】.*?\\\\n|\"|\\\\n", "").trim());
        }
    }

    private void setPhysicalExam(CharacterInfo info, String str) {
        if (str.contains("【物理强度】")) {
            info.setPhysicalStrength(dealBracket(str, "(【物理强度】.*?\\\\n)"));
            info.setMobility(dealBracket(str, "(【战场机动】.*?\\\\n)"));
            info.setPhysicalResilience(dealBracket(str, "(【生理耐受】.*?\\\\n)"));
            info.setTacticalAcumen(dealBracket(str, "(【战术规划】.*?\\\\n)"));
            info.setCombatSkill(dealBracket(str, "(【战斗技巧】.*?\\\\n)"));
            info.setOriginiumArtsAssilimation(RegexUtil.retain(str, "(【源石技艺适应性】.*?(\"|\\\\n))").replaceAll("【.*?】|\\\\n|\"", "").trim());
        }
    }

    private String dealBracket(String str, String regex) {
        return RegexUtil.retain(str, regex).replaceAll("【.*?】|\\\\n", "").trim();
    }
}