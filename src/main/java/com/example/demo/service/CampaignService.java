package com.example.demo.service;
import com.example.demo.entity.Campaign;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component
@Service
public interface CampaignService {
    public List<Campaign> getListCampaigns();
}
