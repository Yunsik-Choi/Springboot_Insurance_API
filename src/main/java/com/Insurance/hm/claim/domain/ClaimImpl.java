package com.Insurance.hm.claim.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "claim")
public class ClaimImpl {

    @Id
    private String id;

    private LocalDateTime accident_date;
    private Long damage_cost;
    private String hospital_statement;
    private LocalDateTime receiptDate;
    //정비에 필요한 서류
    //private File maintenace_details;
    //현장 사진 파일
    //private File site_photos;

    private ClaimStatus status;
    private ClaimType type;
}
