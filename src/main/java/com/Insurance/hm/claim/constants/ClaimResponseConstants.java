package com.Insurance.hm.claim.constants;

import lombok.Getter;

@Getter
public enum ClaimResponseConstants {

    CLAIM_NO("Claim NO."),
    CHANGE_CLAIM_STATUS(" 사고 상태 변경 성공!"),
    CHANGE_PARTNER_SCORE(" 파트너 점수 변경 성공!"),
    FIND_ALL("Claim 전체 조회");

    private String message;

    ClaimResponseConstants(String message) {
        this.message = message;
    }
}
