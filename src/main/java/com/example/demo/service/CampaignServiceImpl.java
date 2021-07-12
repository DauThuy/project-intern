package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Campaign;
import com.example.demo.exception.InValidEmailException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.dto.AccountDto;
import com.example.demo.model.dto.CampaignDto;
import com.example.demo.model.dto.InfoDto;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.security.ProvideJwt;
import com.example.demo.util.EmailValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl {

    @Autowired
    private CampaignRepository campaignRepository;

    public CampaignDto getCampaign() {
        CampaignDto campaign = campaignRepository.getAll();
        return campaign;
    }


}
