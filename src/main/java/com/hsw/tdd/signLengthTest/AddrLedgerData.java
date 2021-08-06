package com.hsw.tdd.signLengthTest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class AddrLedgerData {
    private Integer version;

    private String reqType;

    private String reqId;

    private String serviceGroupId;

    private String mainNetId;

    private String coinId;

    private AddrReq addrReq;

    private List<AddrList> addresses;

    private Signature signature;

    @Getter
    @Setter
    public static class AddrReq {
        private String addrType;
        private Integer addrCount;
    }

    @Getter
    @Setter
    public static class AddrList {
        private String address;
        private String publicKey;
    }

    @Getter
    @Setter
    public static class Signature {
        private String exchange;
        private String lobster1;
        private String crab;
        private String lobster2;
    }

    public String serializeData() {
        String Separator = ":";
        List<String> data = new ArrayList<>();

        data.add(Integer.toString(getVersion()));
        data.add(getReqType());
        data.add(getReqId());
        data.add(getServiceGroupId());
        data.add(getMainNetId());
        data.add(getCoinId());
        data.add(getAddrReq().getAddrType());
        data.add(Integer.toString(getAddrReq().getAddrCount()));
        for (AddrList addrList : getAddresses()) {
            data.add(addrList.getAddress());
            data.add(addrList.getPublicKey());
        }
        data.add(getSignature().getExchange());
        data.add(getSignature().getLobster1());
        data.add(getSignature().getCrab());
        data.add(getSignature().getLobster2());

        String serializedStringData = String.join(Separator, data);

        return serializedStringData;
    }
}

