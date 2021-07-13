package com.example.demo.repository;
import com.example.demo.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    @Query(value = "SELECT * FROM campaigns", nativeQuery = true)
    List<Campaign> getAll();

//    CampaignDto findAllBy()''
//    @Query(value = "select new CampaignDto(c.campaign_name) from CampaignDto u where campId = ? group by objectiveTypeId,modifiedAt", nativeQuery = true)
//    select new StatsDTO(count(u),u.typeId,u.modifiedAt) from UserCampaignObjective u where campId = ? group by objectiveTypeId,modifiedAt

}
