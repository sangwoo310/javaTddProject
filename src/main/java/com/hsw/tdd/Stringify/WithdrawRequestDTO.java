package com.hsw.tdd.Stringify;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Getter
@Setter
@ToString
public class WithdrawRequestDTO {

    // 출금요청 데이터 버전
    @JsonProperty("withdraw_req_version")
    private Integer version;

    // 출금요청 아이디
    @JsonProperty("withdraw_req_id")
    private String requestId;

    // 고객 요청 데이터 구조체
    @JsonProperty("client_req")
    private ClientRequestDTO clientRequest;

    // 고객 2차 서명
    @JsonProperty("cs2")
    private String cs2;

    // JsonString to 출금요청 데이터
    public static WithdrawRequestDTO fromJSON(String message) throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper()
                .readerFor(WithdrawRequestDTO.class)
                .readValue(message);
    }

    // 출금요청 데이터 to JsonString
    public String toJSONString() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }


    @Getter
    @Setter
    @ToString
    public static class ClientRequestDTO {

        @JsonProperty("client_req_version")
        private Integer version;

        @JsonProperty("service_group_id")
        private String serviceGroupId;

        @JsonProperty("client_req_seq")
        private Integer seq;

        @JsonProperty("coin_id")
        private String coinId;

        @JsonProperty("client_req_type")
        private String requestType;

        @JsonProperty("from_address")
        private String fromAddress;

        @JsonProperty("to_address")
        private String toAddress;

        @JsonProperty("sub_address")
        private String subAddress;

        @JsonProperty("amount")
        private String amount;

        @JsonProperty("cs1")
        private String cs1;

        // JsonString to 고객요청 데이터
        public static ClientRequestDTO fromJSON(String message) throws JsonMappingException, JsonProcessingException {
            return new ObjectMapper()
                    .readerFor(ClientRequestDTO.class)
                    .readValue(message);
        }

        // 고객요청 데이터 to JsonString
        public String toJSONString() throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(this);
        }


    }
}
