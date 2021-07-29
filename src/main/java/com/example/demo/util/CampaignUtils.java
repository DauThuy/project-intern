package com.example.demo.util;

import com.example.demo.entity.Campaign;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

public class CampaignUtils implements Comparator <CampaignDto> {
    @Autowired
    private static CampaignRepository campaignRepository;

    @Override
    public int compare(CampaignDto campaignDto1, CampaignDto campaignDto2) {
//        return campaignDto1.getCampaignId() - campaignDto2.getCampaignId();
        return campaignDto1.getCampaignName().compareTo(campaignDto2.getCampaignName());
    }


}
