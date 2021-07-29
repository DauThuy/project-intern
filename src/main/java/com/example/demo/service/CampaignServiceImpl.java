package com.example.demo.service;

import com.example.demo.entity.Campaign;
import com.example.demo.exception.InValidDateException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.dto.campaign.ResponseForBannerDto;
import com.example.demo.model.dto.campaign.ResponseForClickDto;
import com.example.demo.model.mapper.CampaignMapper;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.util.CampaignUtils;
import com.example.demo.util.DateConditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignServiceImpl implements CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public List<CampaignDto> getListCampaigns() {
        List<CampaignDto> campaignDtos = new ArrayList<>();
        List<Campaign> campaigns = campaignRepository.findAllBy();

        for (Campaign campaign : campaigns) {
            if (!campaign.getIsDelete()) {
                campaignDtos.add(CampaignMapper.toCampaignDto(campaign));
            }
        }
        Collections.sort(campaignDtos, new CampaignUtils());
        return campaignDtos;
    }

    @Override
    public CampaignDto getCampaignById(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        if (!campaignRepository.existsById(id) || campaign.getIsDelete()) {
            throw new NotFoundException("Not found campaign");
        }
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public CampaignDto createCampaign(CampaignRequest request) {
        Campaign campaign = CampaignMapper.toCreate(request);
        campaignRepository.save(campaign);
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public CampaignDto updateCampaign(CampaignRequest request, int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        if (!campaignRepository.existsById(id) || campaign.getIsDelete()) {
            throw new NotFoundException("Not found campaign");
        }
//        Date now = new Date();

        campaign.setCampaignName(request.getCampaignName());
        campaign.setCampaignStatus(request.getCampaignStatus());

//        if (!DateConditional.vailidDate(request.getStartDate(), request.getEndDate())) {
//            throw new InValidDateException();
//        }
        if (!DateConditional.startedDateModifiedConditional(campaign.getDateCreated(), request.getStartDate())) {
            throw new InValidDateException();
        }
        if (!DateConditional.endDateModifiedConditional(campaign.getDateCreated(), request.getStartDate(), request.getEndDate())) {
            throw new InValidDateException();
        }
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());


//        if (CreatedModifiedDateConditional.createdDateConditional(request.getStartDate())) {
//            campaign.setStartDate(request.getStartDate());
//        } else {
//            throw new InValidDate();
//        }
//
//        if (CreatedModifiedDateConditional.modifiedDateConditional(request.getStartDate(), request.getEndDate())) {
//            campaign.setEndDate(request.getEndDate());
//        } else {
//            throw new InValidDate();
//        }
//        campaign.setEndDate(request.getEndDate());
        
        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());

        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());
//        campaign.setAccountId(request.getAccountId());

        campaignRepository.save(campaign);
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public String deleteCampaignById(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        campaign.setIsDelete(true);
        campaignRepository.save(campaign);

        return "removed campaign" + id;
    }

    @Override
    public ResponseForClickDto getViews(int id) {
        ResponseForClickDto responseForClickDto = new ResponseForClickDto();
        Campaign campaign = campaignRepository.findByCampaignId(id);
        int clicks = campaign.getClicks();
        int cost = campaign.getCost();

        clicks += 1;
        cost += campaign.getBidAmount();

        campaign.setClicks(clicks);
        campaign.setCost(cost);
        campaignRepository.save(campaign);

        responseForClickDto.setFinalUrl(campaign.getFinalUrl());
        System.out.println("numbers of click: " + clicks);
        System.out.println("url: " + campaign.getFinalUrl());

        calculateUsedAmount(id);
        calculateUsageRate(id);

        return responseForClickDto;
    }

        @Override
        public List<ResponseForBannerDto> getBanners() {
            List<Campaign> campaigns = campaignRepository.findAllBy();
            List<Campaign> campaignSortedByBidAmounts =  new ArrayList<>();
            List<ResponseForBannerDto> banners = new ArrayList<>();

            for (Campaign campaign: campaigns) {
                if (campaign.getOveralBudget() - campaign.getCost() >= campaign.getBidAmount() && campaign.getCampaignStatus() == 1) {
                    campaignSortedByBidAmounts.add(campaign);
                }
            }
            campaignSortedByBidAmounts.stream().sorted();

            int countBanner = 0;
            for (Campaign campaign: campaignSortedByBidAmounts) {
                if (countBanner >= 4) {
                    break;
                }
                banners.add(new ResponseForBannerDto(campaign.getCampaignId(), campaign.getPreview()));
                countBanner++;
            }
            return banners;
        }

    public int calculateUsedAmount(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        int views = campaign.getClicks();
        int bidAmount = campaign.getBidAmount();
        int usedAmount = views * bidAmount;

        System.out.println("user amount: " + usedAmount);

        campaign.setUsedAmount(usedAmount);
        campaignRepository.save(campaign);
        return usedAmount;
    }

    public float calculateUsageRate(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        int usedAmount = calculateUsedAmount(campaign.getCampaignId());
        int budget = campaign.getOveralBudget();
        float usageRate = (usedAmount * 100.0f) / budget;
        usageRate = (float) Math.floor(usageRate * 100) / 100;

        campaign.setUsageRate(usageRate);
        campaignRepository.save(campaign);

        return usageRate;
    }
}
