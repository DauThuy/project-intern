package com.example.demo.service;

import com.example.demo.entity.Campaign;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.mapper.CampaignMapper;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.repository.CampaignRepository;
//import com.example.demo.util.CampaignUtils;
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
        return campaignDtos;
    }

    @Override
    public CampaignDto getCampaignById(int id) {
        Campaign campaign = campaignRepository.findCampaignByCampaignId(id);
        if (!campaignRepository.existsById(id) || campaign.getIsDelete()) {
            throw new NotFoundException("Not found campaign");
        }
        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public CampaignDto createCampaign(CampaignRequest request) {
        Campaign campaign = CampaignMapper.toCreateOrUpdateCampaign(request);
        campaignRepository.save(campaign);
        return CampaignMapper.toCampaignDto(campaign);
    }


    @Override
    public CampaignDto updateCampaign(CampaignRequest request, int id) {
        Campaign campaign = campaignRepository.findCampaignByCampaignId(id);
        Calendar endTime = Calendar.getInstance();
        Date startTime = new Date();
        endTime.setTime(startTime);
        endTime.roll(Calendar.DATE, 2);

        campaign.setCampaignName(request.getCampaignName());
        campaign.setCampaignStatus(request.getCampaignStatus());
        campaign.setStartDate(startTime);
        campaign.setEndDate(endTime.getTime());
        campaign.setOveralBudget(request.getOveralBudget());
        campaign.setBidAmount(request.getBidAmount());
        campaign.setIsDelete(campaign.getIsDelete());
        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        campaign.setPreview(request.getPreview());
        campaign.setFinalUrl(request.getFinalUrl());
        campaign.setAccountId(request.getAccountId());

        return CampaignMapper.toCampaignDto(campaign);
    }

    @Override
    public String deleteCampaignById(int id) {
        Campaign campaign = campaignRepository.findCampaignByCampaignId(id);
        campaign.setIsDelete(true);
        campaignRepository.save(campaign);

        return "removed campaign" + id;
    }
}

//        campaign.setDateCreated(campaign.getDateCreated());
//        campaign.setDateModified(campaign.getDateModified());