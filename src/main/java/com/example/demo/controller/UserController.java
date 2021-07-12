package com.example.demo.controller;

import com.example.demo.entity.Campaign;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.CampaignDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.service.AccountService;
import com.example.demo.service.CampaignService;
import com.example.demo.service.CampaignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private CampaignService campaignService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/login")
    public InfoDto getInfo(@Valid @RequestBody AccountDto dto) {
            return accountService.login(dto);
    }

    @GetMapping(value="/campaign")
    public ResponseEntity<?> getListCampaign() {
        List<Campaign> campaigns = campaignService.getListCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser() {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser() {
        return null;
    }
}
