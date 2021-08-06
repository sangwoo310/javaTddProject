package com.hsw.tdd.signLengthTest;

import com.hsw.tdd.crypto.RSA;
import com.hsw.tdd.utils.HexUtil;
import org.springframework.util.Assert;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class SignLength {

    public static void main(String[] args) throws Exception {
        String pubKey = "30820122300d06092a864886f70d01010105000382010f003082010a0282010100c51b2ff7b64ba73e7f244de2586cebf7761930fc868e0691e740e26bb1fe709f068add8b215da48f8a4ca42e3a9ceee365d4743a9e04e8d84fd6b54a42451806b61814597d35ea6c70955bbb86c03ef668b12a1e87becca362e3193695277ee34ac53659e68b8e9083130c895252ecd03b8284b5801ad35b0733c30817a4bf261084c1fc8facb29857da5b700440a1c637208f084460aaf71cc0bb9f2c500919b27c2514e5980a44412d9e2bc78625ef2fbb27b17744fff3cc3118af36cf82613b767a82736db66f8e068845b1b413fc93863ddeae7a91aebc64bb966788924bc11ace618cee7d378da5155b347939d1ba77a85cc11ac3f9fec0271bfea91be10203010001";
        String privKey = "308204be020100300d06092a864886f70d0101010500048204a8308204a40201000282010100c51b2ff7b64ba73e7f244de2586cebf7761930fc868e0691e740e26bb1fe709f068add8b215da48f8a4ca42e3a9ceee365d4743a9e04e8d84fd6b54a42451806b61814597d35ea6c70955bbb86c03ef668b12a1e87becca362e3193695277ee34ac53659e68b8e9083130c895252ecd03b8284b5801ad35b0733c30817a4bf261084c1fc8facb29857da5b700440a1c637208f084460aaf71cc0bb9f2c500919b27c2514e5980a44412d9e2bc78625ef2fbb27b17744fff3cc3118af36cf82613b767a82736db66f8e068845b1b413fc93863ddeae7a91aebc64bb966788924bc11ace618cee7d378da5155b347939d1ba77a85cc11ac3f9fec0271bfea91be1020301000102820101008d51acd6a1f123fbff147c72e88b4fb3a10cf6be578ac4d31f46ba1015122495200fb83fe4b04b284f341c12b91b837baeaae09a966956b8a0a5845db59dec58037ec8385139b2d604f2514ee69237925c0589f951c24ede662c050bc96d03e0dadf02d4fa22dd1867a1e4bf20332c6f2ffba842a992922f15bedafa96591c625e4aa4a3fe6f6d498b6d18515056929a6ea73caa2923c4848e67147219c291beebeec91a304639ac27a4cb13d6e7ac714ae601af31f83fff157ed4e741b6497e2f5b645356ac5e9f7d1b4db509763fbc9b5e7ecf09371c098d335e12f3e4c2557e4f6b350f87609d2c564568fb40ab07c72cb3aaf951965983b4c355f6fb564102818100c9e3ab9eb1d5b005f2319f51a1dcfa132f4814ffe46c8c7f86bba8ebdb4f438e49742f417e2c4bbb58f50615f4634b42e648cc3e8c74c7bf5f5a687fcaf346db78b43fa395a15137aa102952c817b5ee5d9b1fa11414accf35967b4c4f5fd811bef03cff7ee6b718f6a37fe968baa96bcf89597b19f9ee0d3a58424732c6e17d02818100f9ef544f29555296f83eb146488b2bd2d75e8cb92b8275e5e833d32b032239ee895cc019eb00e7c68d40b53d95f76724e44f65fd74b8a10822519093059f31f984fd44b4bf12795576fbb1de5dd05bc604154c1003301a430c2c2e9a5321c902fa672fec4b1cfa3eaf7b86d8f00539c4f1af1d85c4df540b70b58668f3f9b1350281810097dd4cb1993016b2c256957380808b1477528f8955b8598f6c38297f117f745fc9dd3dd6d42459eb59a526a8c9270fec08f1e5c17f4231274180ee6e82a9b1395278b40e47760b7405d7165e90e9550f3316871a51d2a39352ca1981738bb32ad6283c9a2c493c9de8996094a115187062b2fcbe5ef1e421cb2bacdf85d098b90281807881e3c5b36937442177a0e4017050f5fcefd2ac298562b1328f625ed7c8f5e3c4895afa642bf094f46061c2caff19d558dbcb9234681d049dff73d4df9e0d6b80aad928c903f04abbeec9a92cd49c9b5236ae90fde88091195a6831f2a954914208e4bb6ad012fc8c81e3d7cf8ac57ae70907140b839eb35169c4dfefdad18d02818063cad6c512d753ff1ef9e1cf5ef7233a08d9caf715a35bde61d65bba238cee4f7cf2bb05249bf3a041d901f809e6cb8281d3ac096411f2d92c7ad2187b9fad4174701cfe4bd96976e75fa697d6c0500f1d16ad0912d4b6161875a1f91ba794e2dbe3dbfb125039e5008f7a108412c94ae124f79282fc3f5482a552b4a0c4ee08";

        PrivateKey privateKey = RSA.loadPiravetKey(HexUtil.hexToBytes(privKey));
        PublicKey publicKey = RSA.loadPublicKey(HexUtil.hexToBytes(pubKey));

        byte[] addrLedgerDataByte = new SignLength().getAddrLedgerData();

        String signature = RSA.sign(privateKey, addrLedgerDataByte);
        Boolean verifyMessage = RSA.verify(publicKey, addrLedgerDataByte, signature);
        if (!verifyMessage) {
            System.out.println("Fail to signature verify");
        }

        System.out.println("Signature \t: " + signature);
        System.out.println("Signature size\t : " + signature.length());
    }

    public byte[] getAddrLedgerData() {
        AddrLedgerData addrLedgerData = new SignLength().addressLedgerBuilder();
        System.out.println(addrLedgerData.serializeData());
        byte[] addrLedgerDataByte = addrLedgerData.serializeData().getBytes();

        return addrLedgerDataByte;
    }

    public AddrLedgerData addressLedgerBuilder() {
        AddrLedgerData addrLedgerData = new AddrLedgerData();

        // addrReq data create
        AddrLedgerData.AddrReq addrReq = new AddrLedgerData.AddrReq();
        addrReq.setAddrType("P2SH");
        addrReq.setAddrCount(100);

        // addresses list data create
        AddrLedgerData.AddrList addrList = new AddrLedgerData.AddrList();
        addrList.setAddress("3MYrtaAZjF32LyFCqif4PdicfWjW8NLBA5");
        addrList.setPublicKey("036b5a64f27643ac6112099374efbaade460506b6a3832850b9ad6b20bb1ea7da0");
        List<AddrLedgerData.AddrList> aList = new ArrayList();
        for (int i = 0; i < 1; i++) {
            aList.add(addrList);
        }

        // Signature struct create
        AddrLedgerData.Signature signature = new AddrLedgerData.Signature();
        signature.setExchange("1-XbieY0OCFftSN86zSa9sndNN9h5YqmlpPkmYsaXc6LmQE93Aww1WnkYKnqAQMD8Z5h/O2rOm0fK0MJIFsJMCRMxvN5Shm1UKeP6DB222ZdpakwFhhNgW0xC0LQcGOIjENu6koMg+A1VKaqqwwoo+3LN6cgUGKciiuGfVXpKgA=");
        signature.setLobster1("2-3XbieY0OCFftSN86zSa9sndNN9h5YqmlpPkmYsaXc6LmQE93Aww1WnkYKnqAQMD8Z5h/O2rOm0fK0MJIFsJMCRMxvN5Shm1UKeP6DB222ZdpakwFhhNgW0xC0LQcGOIjENu6koMg+A1VKaqqwwoo+3LN6cgUGKciiuGfVXpKgA=");
        signature.setCrab("3-3XbieY0OCFftSN86zSa9sndNN9h5YqmlpPkmYsaXc6LmQE93Aww1WnkYKnqAQMD8Z5h/O2rOm0fK0MJIFsJMCRMxvN5Shm1UKeP6DB222ZdpakwFhhNgW0xC0LQcGOIjENu6koMg+A1VKaqqwwoo+3LN6cgUGKciiuGfVXpKgA=");
        signature.setLobster2("4-3XbieY0OCFftSN86zSa9sndNN9h5YqmlpPkmYsaXc6LmQE93Aww1WnkYKnqAQMD8Z5h/O2rOm0fK0MJIFsJMCRMxvN5Shm1UKeP6DB222ZdpakwFhhNgW0xC0LQcGOIjENu6koMg+A1VKaqqwwoo+3LN6cgUGKciiuGfVXpKgA=");


        addrLedgerData.setVersion(1);
        addrLedgerData.setReqType("addr-gen-req");
        addrLedgerData.setReqId("bithumb-addr-0001");
        addrLedgerData.setServiceGroupId("00001");
        addrLedgerData.setMainNetId("C0102");
        addrLedgerData.setCoinId("C0105");

        addrLedgerData.setAddrReq(addrReq);

        addrLedgerData.setAddresses(aList);

        addrLedgerData.setSignature(signature);

        return addrLedgerData;
    }

    public byte[] getSignLedgerData() {
        SignLedgerData signLedgerData = new SignLength().signLedgerBuilder();
        byte[] signLedgerDataByte = signLedgerData.serializeData().getBytes();

        return signLedgerDataByte;
    }

    public SignLedgerData signLedgerBuilder() {
        SignLedgerData signLedgerData = new SignLedgerData();

        return signLedgerData;
    }
}

