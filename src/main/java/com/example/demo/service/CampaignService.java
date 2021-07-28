package com.example.demo.service;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CampaignService {
    List<CampaignDto> getListCampaigns();
    CampaignDto getCampaignById(int id);
    CampaignDto createCampaign(CampaignRequest request);
    CampaignDto updateCampaign(CampaignRequest request, int id);
    String deleteCampaignById(int id);
    int getViews(int id);
    List<String> getBanners(int id);
}
