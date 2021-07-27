package com.example.demo.model.mapper;

import com.example.demo.entity.Campaign;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

public class CampaignMapper {
    public static CampaignDto toCampaignDto(Campaign campaign) {
        CampaignDto campaignMapper = new CampaignDto();
        campaignMapper.setCampaignName(campaign.getCampaignName());
        campaignMapper.setCampaignStatus(campaign.getCampaignStatus());
        campaignMapper.setUsedAmount(0f);
        campaignMapper.setUsageRate(0f);
        campaignMapper.setOveralBudget(campaign.getOveralBudget());
        campaignMapper.setStartDate((campaign.getStartDate()));
        campaignMapper.setEndDate(campaign.getEndDate());

        return campaignMapper;
    }

    public static Campaign toCreateOrUpdateCampaign (CampaignRequest request) {
        Campaign campaign = new Campaign();
        Calendar endTime = Calendar.getInstance();
        Date startTime = new Date();
        endTime.setTime(startTime);
        endTime.roll(Calendar.DATE, 2);
        campaign.setCampaignName(request.getCampaignName());
        campaign.setStartDate(startTime);//campaign.getStartDate()
        campaign.setEndDate(endTime.getTime());
        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());
        campaign.setCampaignStatus(request.getCampaignStatus());

        campaign.setDateCreated(campaign.getDateCreated());
        campaign.setDateModified(campaign.getDateModified());

        campaign.setAccountId(request.getAccountId());
        campaign.setIsDelete(campaign.getIsDelete());

        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());

        return campaign;
    }
//    public static Campaign toUpdateCampaign (CampaignRequest request) {
//        Campaign campaign = new Campaign();
//        Date now = new Date();
//
//        campaign.setCampaignId(request.getCampaignId());
//        campaign.setCampaignName(request.getCampaignName());
//        campaign.setStartDate(request.getStartDate());
//        campaign.setEndDate(request.getEndDate());
//        campaign.setOveralBudget(request.getOveralBudget());
//        campaign.setBidAmount(request.getBidAmount());
//        campaign.setCampaignStatus(request.getCampaignStatus());
//
//        campaign.setDateCreated(campaign.getDateCreated());
//        campaign.setDateModified(campaign.getDateModified());
//        campaign.setAccountId(campaign.getAccountId());
//        campaign.setIsDelete(campaign.getIsDelete());
//
//        campaign.setTitle(request.getTitle());
//        campaign.setDescription(request.getDescription());
//        campaign.setPreview(request.getPreview());
//        campaign.setFinalUrl(request.getFinalUrl());
//
//        return campaign;
//    }
}
