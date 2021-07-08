package com.rust.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rust.demo.entity.CharacterInfo;
import com.rust.demo.mapper.arknights.CharacterInfoMapper;
import com.rust.demo.util.CustomRegexUtil;
import org.springframework.stereotype.Service;

@Service
public class CharacterInfoService extends ServiceImpl<CharacterInfoMapper, CharacterInfo> {

    public void setBasicInfo(CharacterInfo info, String str) {
        if (str.contains("【代号】")) {
            info.setCodeName(dealBracket(str, "(【代号】.*?\\\\n)"));
            info.setGender(dealBracket(str, "(【性别】.*?\\\\n)"));
            info.setCombatExperience(dealBracket(str, "(【战斗经验】.*?\\\\n)"));
            info.setPlaceOfBirth(dealBracket(str, "(【出身地】.*?\\\\n)"));
            info.setDateOfBirth(dealBracket(str, "(【生日】.*?\\\\n)"));
            info.setRace(dealBracket(str, "(【种族】.*?\\\\n)"));
            info.setHeight(dealBracket(str, "(【身高】.*?\\\\n)"));
            info.setInfectionStatus(CustomRegexUtil.retain(str, "(【矿石病感染情况】.*?\\\\n.*?(\"|\\\\n))").replaceAll("【.*?】.*?\\\\n|\"|\\\\n", "").trim());
        }
    }

    public void setPhysicalExam(CharacterInfo info, String str) {
        if (str.contains("【物理强度】")) {
            info.setPhysicalStrength(dealBracket(str, "(【物理强度】.*?\\\\n)"));
            info.setMobility(dealBracket(str, "(【战场机动】.*?\\\\n)"));
            info.setPhysicalResilience(dealBracket(str, "(【生理耐受】.*?\\\\n)"));
            info.setTacticalAcumen(dealBracket(str, "(【战术规划】.*?\\\\n)"));
            info.setCombatSkill(dealBracket(str, "(【战斗技巧】.*?\\\\n)"));
            info.setOriginiumArtsAssilimation(CustomRegexUtil.retain(str, "(【源石技艺适应性】.*?(\"|\\\\n))").replaceAll("【.*?】|\\\\n|\"", "").trim());
        }
    }

    public String dealBracket(String str, String regex) {
        return CustomRegexUtil.retain(str, regex).replaceAll("【.*?】|\\\\n", "").trim();
    }
}
