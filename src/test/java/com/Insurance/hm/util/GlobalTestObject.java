package com.Insurance.hm.util;

import com.Insurance.hm.AccidentHistory.domain.AccidentHistory;
import com.Insurance.hm.AccidentHistory.dto.AccidentHistoryCreateRequestDto;
import com.Insurance.hm.board.domain.Board;
import com.Insurance.hm.board.dto.BoardCreateDto;
import com.Insurance.hm.client.domain.Client;
import com.Insurance.hm.client.domain.entity.Bank;
import com.Insurance.hm.client.domain.entity.Gender;
import com.Insurance.hm.client.domain.entity.RRN;
import com.Insurance.hm.client.dto.ClientCreateRequestDto;
import com.Insurance.hm.contract.domain.Contract;
import com.Insurance.hm.contract.domain.entity.Channel;
import com.Insurance.hm.contract.domain.entity.ContractDate;
import com.Insurance.hm.contract.domain.entity.ContractStatus;
import com.Insurance.hm.contract.dto.ContractSignRequestDto;
import com.Insurance.hm.employee.domain.Employee;
import com.Insurance.hm.employee.domain.entity.Department;
import com.Insurance.hm.employee.domain.entity.Role;
import com.Insurance.hm.employee.dto.EmployeeJoinRequestDto;
import com.Insurance.hm.global.util.ClobHandler;
import com.Insurance.hm.insurance.domain.Insurance;
import com.Insurance.hm.insurance.domain.entity.InsuranceCategory;
import com.Insurance.hm.insurance.domain.entity.InsuranceTarget;

import java.sql.Clob;
import java.time.LocalDateTime;

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
}