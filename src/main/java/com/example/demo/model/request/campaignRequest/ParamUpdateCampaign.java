package com.example.demo.model.request.campaignRequest;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ParamUpdateCampaign {
    @NotNull
    private String campaignName;

    @NotNull
    private String campaignStatus;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Date overalBudget;

    @NotNull
    private Float bidAmount;
}
