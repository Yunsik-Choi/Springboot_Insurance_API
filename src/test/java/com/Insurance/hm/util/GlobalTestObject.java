package com.Insurance.hm.util;

import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryCreateRequestDto;
import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.claim.domain.Claim;
import com.Insurance.hm.claim.domain.entity.ClaimStatus;
import com.Insurance.hm.claim.dto.ClaimCreateRequestDto;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.*;
import com.Insurance.hm.client.dto.ClientCreateRequestDto;
import com.Insurance.hm.compensation.domain.Compensation;
import com.Insurance.hm.compensation.domain.entity.CompensationStatus;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.Channel;
import com.Insurance.hm.contract.domain.entity.ContractDate;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.EmployeeJoinRequestDto;
import com.Insurance.hm.global.domain.claimPatner.ClaimPartner;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceStatus;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;
import com.Insurance.hm.partner.domain.Partner;
import com.Insurance.hm.partner.domain.entity.PartnerCategory;
import com.Insurance.hm.partner.dto.PartnerCreateRequestDto;

import java.sql.Clob;
import java.time.LocalDateTime;
import java.util.List;

public class GlobalTestObject {

    public static Employee getEmployee() {
        return Employee.builder()
                .name("최윤식")
                .loginId("abcd")
                .password("1234")
                .phoneNumber("010-000-000")
                .email("abcd")
                .department(Department.개발)
                .role(Role.과장)
                .build();
    }

    public static Insurance getInsurance() {
        Insurance insurance = Insurance.builder()
                .name("HM 운전자 보험")
                .description("HM보험사에서 내놓는 최초의 보험")
                .category(InsuranceCategory.운전자)
                .target(getInsuranceTarget())
                .createEmployee(getEmployee())
                .managementEmployee(getEmployee())
                .status(InsuranceStatus.결재대기)
                .build();
        return insurance;
    }

    public static InsuranceTarget getInsuranceTarget() {
        InsuranceTarget insuranceTarget = new InsuranceTarget();
        insuranceTarget.setCreditRating(3);
        insuranceTarget.setEndAge(70);
        insuranceTarget.setStartAge(24);
        return insuranceTarget;
    }

    public static RRN getRrn(){
        RRN rrn = new RRN();
        rrn.setRrnFront(210527);
        rrn.setRrnBack(11111111);
        return rrn;
    }

    public static Client getClient(){
        Client client = Client.builder()
                .accountNumber("12341234")
                .address("대한민국")
                .age(20)
                .bank(Bank.IBK기업)
                .email("naver.com")
                .gender(Gender.MALE)
                .name("이름")
                .phoneNumber("010-0000-000")
                .job(Job.서비스직)
                .rrn(getRrn())
                .build();
        return client;
    }

    public static ContractDate getContractDate(){
        ContractDate contractDate = new ContractDate();
        contractDate.setStartDate(LocalDateTime.now());
        contractDate.setEndDate(LocalDateTime.now());
        contractDate.setRegisterDate(LocalDateTime.now());
        return contractDate;
    }

    public static Contract getContract() {
        Contract contract = Contract.builder()
                .employee(GlobalTestObject.getEmployee())
                .insurance(GlobalTestObject.getInsurance())
                .information(GlobalTestFields.getInformation())
                .client(GlobalTestObject.getClient())
                .contractDate(GlobalTestObject.getContractDate())
                .channel(Channel.온라인)
                .status(ContractStatus.계약신청)
                .premiumRate(0.2)
                .accumulatedPremium(1L)
                .insurancePremium(1L)
                .build();
        return contract;
    }

    public static ContractSignRequestDto getContractSignRequestDto() {
        ContractSignRequestDto contractSignRequestDto = new ContractSignRequestDto();
        contractSignRequestDto.setInsurancePremium(GlobalTestObject.getContract().getInsurancePremium());
        contractSignRequestDto.setAccumulatedPremium(GlobalTestObject.getContract().getAccumulatedPremium());
        contractSignRequestDto.setInformation(GlobalTestObject.getContract().getAdditionalInformation());
        contractSignRequestDto.setPremiumRate(GlobalTestObject.getContract().getPremiumRate());
        contractSignRequestDto.setStatus(GlobalTestObject.getContract().getStatus());
        contractSignRequestDto.setChannel(GlobalTestObject.getContract().getChannel());
        contractSignRequestDto.setContractDate(GlobalTestObject.getContract().getContractDate());
        contractSignRequestDto.setClientId(1L);
        contractSignRequestDto.setInsuranceId(1L);
        contractSignRequestDto.setEmployeeId(1L);
        return contractSignRequestDto;
    }

    public static EmployeeJoinRequestDto getEmployeeJoinRequestDto() {
        EmployeeJoinRequestDto joinRequestDto = new EmployeeJoinRequestDto();
        joinRequestDto.setDepartment(Department.개발);
        joinRequestDto.setEmail("abcd@aaa.com");
        joinRequestDto.setLoginId("abcd");
        joinRequestDto.setName("에이비씨");
        joinRequestDto.setPassword("1234");
        joinRequestDto.setPhoneNumber("010-0000-0000");
        joinRequestDto.setRole(Role.과장);
        return joinRequestDto;
    }

    public static ClientCreateRequestDto getClientCreateRequestDto(){
        ClientCreateRequestDto createRequestDto = new ClientCreateRequestDto();
        createRequestDto.setAccountNumber(getClient().getAccountNumber());
        createRequestDto.setAddress(getClient().getAddress());
        createRequestDto.setAge(getClient().getAge());
        createRequestDto.setBank(getClient().getBank());
        createRequestDto.setEmail(getClient().getEmail());
        createRequestDto.setGender(getClient().getGender());
        createRequestDto.setName(getClient().getName());
        createRequestDto.setPhoneNumber(getClient().getPhoneNumber());
        createRequestDto.setRrn(getClient().getRrn());
        createRequestDto.setJob(getClient().getJob());
        return createRequestDto;
    }

    public static AccidentHistory getAccidentHistory() {
        AccidentHistory accidentHistory = AccidentHistory.builder()
                .accidentDate(LocalDateTime.now())
                .accidentDescription("사고 요약")
                .accidentRate(50.0)
                .client(getClient())
                .build();
        return accidentHistory;
    }

    public static AccidentHistoryCreateRequestDto getAccidentHistoryCreateRequestDto(){
        AccidentHistoryCreateRequestDto accidentHistoryCreateRequestDto = new AccidentHistoryCreateRequestDto();
        accidentHistoryCreateRequestDto.setAccidentDate(getAccidentHistory().getAccidentDate());
        accidentHistoryCreateRequestDto.setAccidentDescription(getAccidentHistory().getAccidentDescription());
        accidentHistoryCreateRequestDto.setAccidentRate(getAccidentHistory().getAccidentRate());
        return accidentHistoryCreateRequestDto;
    }

    public static BoardCreateDto getBoardCreateDto(){
        BoardCreateDto boardCreateDto = new BoardCreateDto();
        boardCreateDto.setTitle("제목");
        boardCreateDto.setContent("내용");
        boardCreateDto.setEmployeeId(getEmployee().getId());
        return boardCreateDto;
    }

    public static Board getBoard() {
        Board board = Board.builder()
                .employee(getEmployee())
                .title("제목")
                .content("내용")
                .build();
        return board;
    }

    public static Claim getClaim() {
        Claim claim = Claim.builder()
                .partnerScore(0.0)
                .claimReason("사고 이유")
                .claimDetail("사고 내용")
                .claimRate(80.0)
                .accidentDate(LocalDateTime.now())
                .contract(getContract())
                .damageCost(10000L)
                .employee(getEmployee())
                .receiptDate(LocalDateTime.now())
                .status(ClaimStatus.접수완료)
                .build();
        claim.getClaimpartnerList().add(getClaimPartner());
        return claim;
    }

    private static ClaimPartner getClaimPartner() {
        ClaimPartner claimPartner = new ClaimPartner(
                Claim.builder()
                        .claimDetail("사고 내용")
                        .partnerScore(0.0)
                        .claimReason("사고 이유")
                        .claimRate(80.0)
                        .contract(getContract())
                        .damageCost(10000L)
                        .employee(getEmployee())
                        .receiptDate(LocalDateTime.now())
                        .status(ClaimStatus.접수완료)
                        .build(),
                Partner.builder()
                        .address("주소")
                        .category(PartnerCategory.병원)
                        .contactNumber("010-0000-0000")
                        .employee(getEmployee())
                        .name("협력업체 이름")
                        .build());
        return claimPartner;
    }

    public static Partner getPartner() {
        Partner partner = Partner.builder()
                .address("주소")
                .category(PartnerCategory.병원)
                .contactNumber("010-0000-0000")
                .employee(getEmployee())
                .name("협력업체 이름")
                .build();
        return partner;
    }

    public static ClaimCreateRequestDto getClaimCreateRequestDto(){
        Claim claim = getClaim();

        ClaimCreateRequestDto createRequestDto = new ClaimCreateRequestDto();
        createRequestDto.setClaimReason(claim.getClaimReason());
        createRequestDto.setAccidentDate(claim.getAccidentDate());
        createRequestDto.setClaimDetail(claim.getClaimDetail());
        createRequestDto.setClaimRate(claim.getClaimRate());
        createRequestDto.setContractId(claim.getContract().getId());
        createRequestDto.setDamageCost(claim.getDamageCost());
        createRequestDto.setEmployeeId(claim.getEmployee().getId());
        return createRequestDto;
    }

    public static PartnerCreateRequestDto getPartnerCreateRequestDto() {
        PartnerCreateRequestDto createRequestDto = new PartnerCreateRequestDto();
        createRequestDto.setName("협력업체 이름");
        createRequestDto.setAddress("협력업체 주소");
        createRequestDto.setCategory(PartnerCategory.병원);
        createRequestDto.setContactNumber("010-0000-00000");
        createRequestDto.setEmployeeId(1L);
        return createRequestDto;
    }

    public static Compensation getCompensation() {
        Compensation compensation = Compensation.builder()
                .status(CompensationStatus.보상대기)
                .employee(getEmployee())
                .cost(100000L)
                .contract(getContract())
                .claim(getClaim())
                .dateTime(LocalDateTime.now())
                .build();
        return compensation;
    }
}
