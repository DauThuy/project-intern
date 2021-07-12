package com.example.demo.model.mapper;

import com.example.demo.entity.Campaign;
import com.example.demo.model.dto.CampaignDto;

import java.util.Date;

public class CampaignMapper {
    public static CampaignDto toCampaignDto(Campaign campaign) {
        CampaignDto cmpMapper = new CampaignDto();
        cmpMapper.setCampaignName(campaign.getCampaignName());

        return cmpMapper;
    }
}
