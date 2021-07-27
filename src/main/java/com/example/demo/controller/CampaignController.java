package com.example.demo.controller;


import com.example.demo.model.dto.campaign.CampaignDto;
import com.example.demo.model.request.campaignRequest.CampaignRequest;
import com.example.demo.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    @GetMapping(value="/campaigns")
    public ResponseEntity<?> getListCampaign() {
        List<CampaignDto> campaigns = campaignService.getListCampaigns();
        return ResponseEntity.ok(campaigns);
    }


    @PostMapping("/campaign/create")
    public ResponseEntity<?> createCampaign(@Valid @RequestBody CampaignRequest req) {
        CampaignDto campaignDto = campaignService.createCampaign(req);
        return ResponseEntity.ok(campaignDto);
    }

    @GetMapping("/campaign/{id}")
    public ResponseEntity<?> getCampaignById(@PathVariable int id) {
        CampaignDto campaignDto = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaignDto);
    }
}
