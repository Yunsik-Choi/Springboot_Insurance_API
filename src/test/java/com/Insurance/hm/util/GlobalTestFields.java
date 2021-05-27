package com.Insurance.hm.util;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class GlobalTestFields {

    public static FieldDescriptor[] getFieldResponseEmployeeInfoDto() {
        return new FieldDescriptor[]{
                fieldWithPath("data.employee.id").type(JsonFieldType.NULL).description("직원 아이디"),
                fieldWithPath("data.employee.name").type(JsonFieldType.STRING).description("직원 이름"),
                fieldWithPath("data.employee.phoneNumber").type(JsonFieldType.STRING).description("직원 전화번호"),
                fieldWithPath("data.employee.email").type(JsonFieldType.STRING).description("직원 이메일"),
                fieldWithPath("data.employee.department").type(JsonFieldType.STRING).description("직원 부서"),
                fieldWithPath("data.employee.role").type(JsonFieldType.STRING).description("직원 직급")};
    }

    public static FieldDescriptor[] getFieldResponseInsuranceInfoDto() {
        return new FieldDescriptor[]{
                fieldWithPath("data.insurance.id").type(JsonFieldType.NULL).description("보험 아이디"),
                fieldWithPath("data.insurance.name").type(JsonFieldType.STRING).description("보험 이름"),
                fieldWithPath("data.insurance.description").type(JsonFieldType.STRING).description("보험 설명"),
                fieldWithPath("data.insurance.category").type(JsonFieldType.STRING).description("보험 종류")};
    }

    public static FieldDescriptor[] getFieldResponseClientInfoDto() {
        return new FieldDescriptor[]{
                fieldWithPath("data.client.id").type(JsonFieldType.NULL).description("고객 아이디"),
                fieldWithPath("data.client.name").type(JsonFieldType.STRING).description("고객 이름"),
                fieldWithPath("data.client.age").type(JsonFieldType.NUMBER).description("고객 나이"),
                fieldWithPath("data.client.address").type(JsonFieldType.STRING).description("고객 주소"),
                fieldWithPath("data.client.phoneNumber").type(JsonFieldType.STRING).description("고객 전화번호"),
                fieldWithPath("data.client.email").type(JsonFieldType.STRING).description("고객 이메일"),
                fieldWithPath("data.client.gender").type(JsonFieldType.STRING).description("고객 성별"),
                fieldWithPath("data.client.rrn.rrnFront").type(JsonFieldType.NUMBER).description("고객 주민번호 앞자리"),
                fieldWithPath("data.client.rrn.rrnBack").type(JsonFieldType.NUMBER).description("고객 주민번호 뒷자리")};
    }

    public static FieldDescriptor[] getFieldResponseContract() {
        ArrayList<FieldDescriptor> vector = new ArrayList<>();
        Arrays.stream(getFieldContract()).forEach(f -> vector.add(f));
        Arrays.stream(getFieldResponseClientInfoDto()).forEach(f -> vector.add(f));
        Arrays.stream(getFieldResponseInsuranceInfoDto()).forEach(f -> vector.add(f));
        Arrays.stream(getFieldResponseEmployeeInfoDto()).forEach(f -> vector.add(f));
        FieldDescriptor[] fields = getFields(vector);
        return fields;
    }

    private static FieldDescriptor[] getFieldContract() {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("Http 상태"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("계약 아이디"),
                fieldWithPath("data.insurancePremium").type(JsonFieldType.NUMBER).description("계약 보험료"),
                fieldWithPath("data.accumulatedPremium").type(JsonFieldType.NUMBER).description("계약 총 납입 보험료"),
                fieldWithPath("data.premiumRate").type(JsonFieldType.NUMBER).description("계약 보험 요율"),
                fieldWithPath("data.status").type(JsonFieldType.STRING).description("계약 상태"),
                fieldWithPath("data.channel").type(JsonFieldType.STRING).description("계약 채널"),
                fieldWithPath("data.contractDate.registerDate").type(JsonFieldType.STRING).description("계약 등록일"),
                fieldWithPath("data.contractDate.startDate").type(JsonFieldType.STRING).description("계약 시작일"),
                fieldWithPath("data.contractDate.endDate").type(JsonFieldType.STRING).description("계약 종료일")};
    }

    public static FieldDescriptor[] getFieldResponseDelete(String desc) {
        return new FieldDescriptor[]{fieldWithPath("status").type(JsonFieldType.NUMBER).description("HTTP 상태"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                fieldWithPath("data").type(JsonFieldType.NUMBER).description(desc)};
    }

    public static FieldDescriptor[] getFields(ArrayList<FieldDescriptor> arrayList){
        return arrayList.toArray(new FieldDescriptor[arrayList.size()]);
    }

    public static FieldDescriptor[] getFieldRequestClientSignRequestDto() {
        return new FieldDescriptor[]{
                fieldWithPath("name").type(JsonFieldType.STRING).description("고객 이름"),
                fieldWithPath("age").type(JsonFieldType.NUMBER).description("고객 나이"),
                fieldWithPath("email").type(JsonFieldType.STRING).description("고객 이메일"),
                fieldWithPath("address").type(JsonFieldType.STRING).description("고객 주소"),
                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("고객 전화번호"),
                fieldWithPath("accountNumber").type(JsonFieldType.STRING).description("고객 계좌번호"),
                fieldWithPath("bank").type(JsonFieldType.STRING).description("고객 은행"),
                fieldWithPath("gender").type(JsonFieldType.STRING).description("고객 성별"),
                fieldWithPath("rrn.rrnFront").type(JsonFieldType.NUMBER).description("고객 주민번호 앞자리"),
                fieldWithPath("rrn.rrnBack").type(JsonFieldType.NUMBER).description("고객 주민번호 뒷자리")};
    }

    public static FieldDescriptor[] getFieldResponseClientDetailDto() {
        return new FieldDescriptor[]{
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("Http 상태"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("고객 아이디"),
                fieldWithPath("data.name").type(JsonFieldType.STRING).description("고객 아이디"),
                fieldWithPath("data.age").type(JsonFieldType.NUMBER).description("고객 나이"),
                fieldWithPath("data.address").type(JsonFieldType.STRING).description("고객 주소"),
                fieldWithPath("data.phoneNumber").type(JsonFieldType.STRING).description("고객 전화번호"),
                fieldWithPath("data.email").type(JsonFieldType.STRING).description("고객 이메일"),
                fieldWithPath("data.accountNumber").type(JsonFieldType.STRING).description("고객 계좌번호"),
                fieldWithPath("data.bank").type(JsonFieldType.STRING).description("고객 은행"),
                fieldWithPath("data.gender").type(JsonFieldType.STRING).description("고객 성별"),
                fieldWithPath("data.rrn.rrnFront").type(JsonFieldType.NUMBER).description("고객 주민번호 앞자리"),
                fieldWithPath("data.rrn.rrnBack").type(JsonFieldType.NUMBER).description("고객 주민번호 뒷자리"),
                subsectionWithPath("data.contractList").type(JsonFieldType.ARRAY).description("고객 계약 리스트"),
                subsectionWithPath("data.accidentHistoryList").type(JsonFieldType.ARRAY).description("고객 사고내역 리스트")};
    }

    public static FieldDescriptor[] getFieldResponseDefault() {
        return new FieldDescriptor[]{
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("Http 상태"),
                fieldWithPath("result").type(JsonFieldType.STRING).description("응답 상태"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지")};
    }


    public static FieldDescriptor[] getFieldRequestAccidentHistoryCreateDto(){
        return new FieldDescriptor[]{
                fieldWithPath("accidentDate").type(JsonFieldType.STRING).description("사고이력 일자"),
                fieldWithPath("accidentRate").type(JsonFieldType.NUMBER).description("사고이력 사고비율"),
                fieldWithPath("accidentDescription").type(JsonFieldType.STRING).description("사고이력 일자"),
                fieldWithPath("clientId").type(JsonFieldType.NULL).description("사고이력 일자")};
    }


    public static FieldDescriptor[] getFieldResponseAccidentHistoryDetailDto(){
        ArrayList<FieldDescriptor> list = new ArrayList<>();
        Arrays.stream(getFieldResponseDefault()).forEach(i -> list.add(i));
        FieldDescriptor[] fields = new FieldDescriptor[] {
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("사고이력 아이디"),
                fieldWithPath("data.accidentDate").type(JsonFieldType.STRING).description("사고이력 일자"),
                fieldWithPath("data.accidentRate").type(JsonFieldType.NUMBER).description("사고이력 사고비율"),
                fieldWithPath("data.accidentDescription").type(JsonFieldType.STRING).description("사고이력 요약"),
                fieldWithPath("data.client.id").type(JsonFieldType.NULL).description("사고이력 고객 아이디"),
                fieldWithPath("data.client.name").type(JsonFieldType.STRING).description("사고이력 고객 이름"),
                fieldWithPath("data.client.age").type(JsonFieldType.NUMBER).description("사고이력 고객 나이"),
                fieldWithPath("data.client.gender").type(JsonFieldType.STRING).description("사고이력 고객 성별")};
        Arrays.stream(fields).forEach(i -> list.add(i));
        FieldDescriptor[] result = getFields(list);
        return result;
    }

    public static FieldDescriptor[] getFieldResponseBoardDetailDto(){
        ArrayList<FieldDescriptor> list = new ArrayList<>();
        Arrays.stream(getFieldResponseDefault()).forEach(i -> list.add(i));
        FieldDescriptor[] fields = new FieldDescriptor[] {
                fieldWithPath("data.id").type(JsonFieldType.NULL).description("사고이력 아이디"),
                fieldWithPath("data.title").type(JsonFieldType.STRING).description("사고이력 일자"),
                fieldWithPath("data.author").type(JsonFieldType.STRING).description("사고이력 사고비율"),
                fieldWithPath("data.department").type(JsonFieldType.STRING).description("사고이력 요약"),
                fieldWithPath("data.createdDate").type(JsonFieldType.NULL).description("사고이력 고객 아이디"),
                fieldWithPath("data.modifiedDate").type(JsonFieldType.NULL).description("사고이력 고객 이름"),
                fieldWithPath("data.content").type(JsonFieldType.STRING).description("사고이력 고객 나이"),};
        Arrays.stream(fields).forEach(i -> list.add(i));
        FieldDescriptor[] result = getFields(list);
        return result;
    }

    public static FieldDescriptor[] getFieldRequestBoardCreateDto(){
        return new FieldDescriptor[] {
                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                fieldWithPath("employeeId").type(JsonFieldType.NULL).description("작성자 아이디"),
                fieldWithPath("content").type(JsonFieldType.STRING).description("내용")};
    }
}
