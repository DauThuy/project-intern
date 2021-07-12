package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Column(name = "campaignId")
    private Integer campaignId;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "overal_budget")
    private Float overalBudget;

    @Column(name = "bid_amount")
    private Float bidAmount;

    @Column(name = "campaign_status")
    private Integer campaignStatus;

    //date_create, modified

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountId;

}
