package com.example.demo.service;

import com.example.demo.entity.Campaign;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.mapper.CampaignMapper;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.util.CampaignUtils;
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
        campaign.setCampaignName(request.getCampaignName());
        campaign.setCampaignStatus(request.getCampaignStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());

        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());

        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());
        campaign.setAccountId(request.getAccountId());

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
    public int getViews(int id) {
        Campaign campaign = campaignRepository.findByCampaignId(id);
        int clicks = campaign.getClicks();
        int cost = campaign.getCost();

        clicks += 1;
        cost += campaign.getBidAmount();

        campaign.setClicks(clicks);
        campaign.setCost(cost);
        campaignRepository.save(campaign);
        return clicks;
    }

    @Override
    public List<String> getBanners(int id) {
        List<String> banners = new ArrayList<>();
        //...
        return banners;
    }
}

//        campaign.setDateCreated(campaign.getDateCreated());
//        campaign.setDateModified(campaign.getDateModified());