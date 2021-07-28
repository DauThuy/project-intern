package com.example.demo.service;
import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.dto.campaign.ResponseForBannerDto;
import com.example.demo.model.dto.campaign.ResponseForClickDto;
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
<<<<<<< HEAD
    ResponseForClickDto getViews(int id);
    List<ResponseForBannerDto> getBanners();
=======
    int getViews(int id);
    List<String> getBanners(int id);
>>>>>>> 9c034511a06d461e61fc735cf01689fbba458ec5
}
