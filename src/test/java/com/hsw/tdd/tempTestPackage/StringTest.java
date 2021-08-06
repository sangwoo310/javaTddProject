package com.hsw.tdd.tempTestPackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsw.tdd.Stringify.WithdrawRequestDTO;
import kafka.utils.Json;
import kafka.utils.json.JsonArray;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StringTest {

    @Test
    public void ObjectArrayToString() throws Exception {
        SampleObject s1 = new SampleObject();
        s1.value1 = "1";
        s1.value2 = "2";

        SampleObject s2 = new SampleObject();
        s2.value1 = "3";
        s2.value2 = "4";

        List<SampleObject> objectList = new ArrayList<SampleObject>();
        objectList.add(s1);
        objectList.add(s2);

//        String str = Arrays.asList(objectList).stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(", "));
//

//        String str = Arrays.deepToString(objectList.toArray());

        String str = new ObjectMapper().writeValueAsString(objectList);

        log.info("result : {}", str);
    }

    @Setter
    @Getter
    static class SampleObject {
        private String value1;
        private String value2;
        private innerObj innerObj;

        public String toJSONString() throws JsonProcessingException {
            return new ObjectMapper().writeValueAsString(this);
        }
    }

    @Setter
    @Getter
    static class innerObj {
        private String wow1;
        private String wow2;

    }


    @Test
    public void JsonObjectArrayStringToJsonObjectArray() throws Exception {
        String arrayString = "[{\"withdraw_req_version\":1,\"withdraw_req_id\":\"5c309f861c870f4eae05ad98ceea73\",\"client_req\":{\"client_req_version\":1,\"service_group_id\":\"00001\",\"client_req_seq\":15347,\"coin_id\":\"C0130\",\"client_req_type\":\"00001\",\"from_address\":\"0xAa0B05D6462c3dcaB0501b040c1473c9528FeD0e\",\"to_address\":\"0x6c535c5a82ea17bd19c92649b49b85be12354286\",\"sub_address\":\"\",\"amount\":\"1000\",\"cs1\":\"YlS3um0v+oc561+Wk1vdJCpNNykgGIDIZ83OZf+2M05NAbDnb/7geKmOnpfTPA2v+iXOP72t30H4WxfDk2lqfO8qYHSAVRK2pGUzhhPjHj+lyR53mcYsbfI6BmFB5VJXmeL90w2zSmQjxkVLpahkmyOTCPnGcsYaULWLCNMsy2e0lgLjHKJtuuIFNMl08xiVL9M5aQKBq0O7vBSkLNyhRWXDsyKA+qEEZXc8ZNl92UYJnkyv90jkPm+igcrUbdBgw0T32WbR4YgeU0P7QdMXJ2d/ChBuPTG1VeH3oAHVEKXTEZ5bhiJeJl3KuTx7GWkcxKMZnb73e6XbFYes+fqi0g==\"},\"cs2\":\"\"}]";


        WithdrawRequestDTO withdrawRequestDTO = new WithdrawRequestDTO();


        String qq = "[{\"value1\":\"1\",\"value2\":\"2\",\"innerObj\":{\"wow1\":\"w1\",\"wow2\":\"w2\"}},{\"value1\":\"3\",\"value2\":\"4\",\"innerObj\":{\"wow1\":\"w3\",\"wow2\":\"w4\"}}]";

        System.out.println(qq.getClass().getName());

        JSONArray jsonArray = new JSONArray(qq);

        System.out.println(jsonArray.length());
        log.info("{}", jsonArray.get(0));
        log.info("{}", jsonArray.get(0).getClass().getName());

//        SampleObject sampleObject = new ObjectMapper().readerFor(SampleObject.class).readValue(jsonArray.get(0).toString());
//        SampleObject sampleObject = new ObjectMapper().readerFor(SampleObject.class).readValue(JSON.s);
//        System.out.println(sampleObject.toString());

        SampleObject sampleObject1 = new ObjectMapper().readerFor(SampleObject.class).readValue(jsonArray.get(1).toString());
        System.out.println(sampleObject1);

        innerObj ww = sampleObject1.innerObj;

        log.info("{}", ww.wow1);

    }
}
