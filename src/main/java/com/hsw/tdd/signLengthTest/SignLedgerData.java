package com.hsw.tdd.signLengthTest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class SignLedgerData {
    private Integer version;

    private String reqType;

    private String reqId;

    private String serviceGroupId;

    private String coinId;

    private List<WithdrawReq> withdrawReq;

    private String mainNetId;

    private String changeAddress;

    private Fee fee;

    private Tx tx;

    private String signedTx;

    private Signature signature;


    @Getter
    @Setter
    private static class WithdrawReq {
        private String fromAddress;
        private String toAddress;
        private String subAddress;
        private String amount;
    }

    @Getter
    @Setter
    private static class Fee {
        private String autoCalc;
        private String value;
        private String limit;
    }

    @Getter
    @Setter
    private static class Tx {
        private Integer signVersion;
        private List<Inputs> inputs;
        private List<OutPuts> outputs;
        private Asset asset;
        private String extra;

        @Getter
        @Setter
        private static class Inputs {
            private String txId;
            private String outIndex;
            private String address;
            private String alias;
            private String amount;
        }

        @Getter
        @Setter
        private static class OutPuts {
            private String address;
            private String subAddress;
            private String amount;
        }

        @Getter
        @Setter
        private static class Asset {
            private String assetId;
            private String assetSymbol;
            private String isNativeCoin;
        }

        public String serializeTx() {
            String Separator = ":";
            List<String> data = new ArrayList<>();

            data.add(Integer.toString(getSignVersion()));
            for (Inputs inputs : getInputs()) {
                data.add(inputs.getTxId());
                data.add(inputs.getOutIndex());
                data.add(inputs.getAddress());
                data.add(inputs.getAlias());
                data.add(inputs.getAmount());
            }
            for (OutPuts outPuts : getOutputs()) {
                data.add(outPuts.getAddress());
                data.add(outPuts.getSubAddress());
                data.add(outPuts.getAmount());
            }
            data.add(getAsset().getAssetId());
            data.add(getAsset().getAssetSymbol());
            data.add(getAsset().getIsNativeCoin());
            data.add(getExtra());

            return String.join(Separator, data);
        }
    }

    @Getter
    @Setter
    private static class Signature {
        private String exchange;
        private String lobster;
        private String crab;
        private String conch;
    }

    public String serializeData() {
        String Separator = ":";
        List<String> data = new ArrayList<>();

        data.add(Integer.toString(getVersion()));
        data.add(getReqType());
        data.add(getReqId());
        data.add(getServiceGroupId());
        data.add(getCoinId());
        for (WithdrawReq withdrawReq : getWithdrawReq()) {
            data.add(withdrawReq.getFromAddress());
            data.add(withdrawReq.getToAddress());
            data.add(withdrawReq.getSubAddress());
            data.add(withdrawReq.getAmount());
        }
        data.add(getMainNetId());
        data.add(getChangeAddress());
        data.add(getFee().getAutoCalc());
        data.add(getFee().getValue());
        data.add(getFee().getLimit());
        data.add(getTx().serializeTx());
        data.add(getSignature().getExchange());
        data.add(getSignature().getLobster());
        data.add(getSignature().getCrab());
        data.add(getSignature().getConch());

        return String.join(Separator, data);
    }
}
