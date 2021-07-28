package com.example.demo.model.mapper;

import com.example.demo.entity.Campaign;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CampaignMapper {
    public static CampaignDto toCampaignDto(Campaign campaign) {
        CampaignDto campaignMapper = new CampaignDto();
        campaignMapper.setCampaignId(campaign.getCampaignId());
        campaignMapper.setCampaignName(campaign.getCampaignName());
        campaignMapper.setCampaignStatus(campaign.getCampaignStatus());
        campaignMapper.setUsedAmount(0);
        campaignMapper.setUsageRate(0);
        campaignMapper.setOveralBudget(campaign.getOveralBudget());
        campaignMapper.setStartDate((campaign.getStartDate()));
        campaignMapper.setEndDate(campaign.getEndDate());

        campaignMapper.setBudget(campaign.getOveralBudget());
        campaignMapper.setBidAmount(campaign.getBidAmount());
        campaignMapper.setTitle(campaign.getTitle());
        campaignMapper.setDescription(campaign.getDescription());
        campaignMapper.setPreview(campaign.getPreview());
        campaignMapper.setFinalUrl(campaign.getFinalUrl());

        return campaignMapper;
    }

    public static Campaign toCreate (CampaignRequest request) {
        Campaign campaign = new Campaign();

        Date now = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, 2);
        Date endTime = calendar.getTime();

        campaign.setCampaignName(request.getCampaignName());
        campaign.setStartDate(now);//campaign.getStartDate()
        campaign.setEndDate(endTime); //request.getEndDate()
        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());
        campaign.setCampaignStatus(request.getCampaignStatus());

        campaign.setDateCreated(now);
        campaign.setDateModified(campaign.getDateModified());

        campaign.setAccountId(request.getAccountId());
        campaign.setIsDelete(campaign.getIsDelete());

        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());

        return campaign;
    }
}
//        Date now = new Date();
//        Calendar calendar = Calendar.getInstance();

//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(date);
//
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(now);
//        Calendar endTime = Calendar.getInstance();
//        Date startTime = new Date();
//        endTime.setTime(startTime);
//        endTime.roll(Calendar.DATE, 2);
