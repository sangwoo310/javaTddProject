///*
//package com.hsw.tdd.hsm;
//
//import java.util.*;
//
//import com.ncipher.nshield.neks.api.jce.NEKSProvider;
//import com.ncipher.nshield.neks.api.jce.exception.NEKSException;
//import com.ncipher.nshield.neks.api.jce.model.SimpleX509Certificate;
//import com.ncipher.nshield.neks.api.jce.NEKSSpec;
//import com.ncipher.nshield.neks.api.jce.extended.NEKSHelper;
//import sun.misc.BASE64Encoder;
//import org.bouncycastle.util.encoders.Hex;
//import org.apache.commons.text.WordUtils;
//
//import java.io.*;
//
//import java.security.*;
//import java.security.cert.*;
//import java.security.cert.Certificate;
//
//
//public class HsmRsa {
//
//    enum CertType {ROOT,INTER,ENDENTITY};
//    //private static String keystorefile = "keystore.ks";
//    //private static String keystorefile_HSM = "HSM_store_nCipher.ks";
//    static String alias = "COMSYS_TEST_RSA02";
//    static String prov_nCipherKM = "NEKS";
//    static char[] OCS_PASSPHRASE = "comsys2019".toCharArray();
//    static String prop_path = "/home/comsys/NEKS/neks-demo-v2.1.1/lib/conf/neks.properties";
//
//    static boolean arraysDiffer ( byte[] a1, byte[] a2 )
//    {
//        if ( a1 == null || a2 == null || a1.length != a2.length )
//            return true;
//
//        for ( int n = 0; n < a1.length; n++ )
//        {
//            if ( a1[n] != a2[n] )
//                return true;
//        }
//
//        return false;
//    }
//
//    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
//        KeyPairGenerator kpg  = KeyPairGenerator.getInstance("RSA",prov_nCipherKM);
//        kpg.initialize(2048);
//        return kpg.genKeyPair();
//    }
//
//
//    public static void main(String[] args) throws IOException, NEKSException, UnrecoverableKeyException, InvalidKeyException,
//            SignatureException {
//
//        System.out.println("Start RSA program.");
//
//        FileReader resources= new FileReader(prop_path);
//        Properties prop = new Properties();
//        prop.load(resources);
//
//
//        Provider provider = Security.getProvider("NEKS");
//        if (provider == null) {
//            provider = new NEKSProvider(prop);
//            Security.addProvider(provider);
//        } else {
//            if (provider instanceof NEKSProvider) {
//                provider = (NEKSProvider) provider;
//            } else {
//                // Raise exception
//                System.out.println("Can not load NEKS Provider.");
//            }
//        }
//
//        try{
//
//            KeyPair rootKeyPair = generateRSAKeyPair();
//
//            Certificate cert = new SimpleX509Certificate(rootKeyPair.getPublic());
//
//            System.out.println(cert);
//
//            //cert.verify(rootKeyPair.getPublic());
//
//            KeyStore ks = KeyStore.getInstance("NEKSDB",prov_nCipherKM);
//            ks.load(null);
//
//            ks.setKeyEntry(alias, rootKeyPair.getPrivate(), OCS_PASSPHRASE, new Certificate[]{cert});
//            System.out.println("Key Generate and Store Succeed.");
//
//            //get Key..
//            System.out.println("Getting the private key " + alias);
//            Key key = ks.getKey(alias, null);
//            if(key == null)
//            {
//                System.out.println("The key " + alias + " doesn't exist in the NEKS keystore ");
//                return;
//            }
//
//            if(!(key instanceof PrivateKey))
//            {
//                System.out.println("The key " + alias + " is not a private key");
//                return;
//            }
//
//            System.out.println("Getting the public key certificate for " + alias);
//            Certificate cert_1 = ks.getCertificate(alias);
//            KeyPair kp = new KeyPair(cert_1.getPublicKey(), (PrivateKey)key);
//            PublicKey pubKey = kp.getPublic();
//            PrivateKey priKey = kp.getPrivate();
//            System.out.println(priKey.getAlgorithm());
//
//            String signingAlgo = "SHA256withRSA";
//            //String signingAlgo = NEKSSpec.ECDSASignatureAlgorithm.SHA256withECDSA.getValue();
//            //String signingAlgo = NEKSSpec.EdDSASignatureAlgorithm.Ed25519.getValue();
//
//            Signature signer = Signature.getInstance(signingAlgo, prov_nCipherKM);
//            signer.initSign(priKey);
//
//            byte[] plainText;
//
//            plainText ="TEST TEXT".getBytes();
//
//            byte[] signature = null;
//
//            BASE64Encoder encoder = new BASE64Encoder();
//
//            signer.update(plainText);
//            signature = signer.sign();
//
//            String base64 = encoder.encode(signature);
//            System.out.println( "Encoding Sign data." );
//            System.out.println( "========================================================" );
//            System.out.println(base64);
//            System.out.println( "========================================================" );
//
//            Signature verifying = Signature.getInstance(signingAlgo, prov_nCipherKM);
//            verifying.initVerify(pubKey);
//
//
//            verifying.update(plainText);
//            boolean signatureOK = verifying.verify(signature);
//
//            if(signatureOK)
//                System.out.println("Success verification!");
//            else
//                System.out.println("Fail verification!");
//
//            //encrypt by publickey , decrpyt by privatekey
//            // Use of NEKS Helper (Extended API)
//            System.out.println("****************");
//            System.out.println("** RSA Cipher **");
//            System.out.println("****************");
//            NEKSSpec.RSAPaddingType paddingType = NEKSSpec.RSAPaddingType.PKCS1;
//            NEKSHelper neksHelper = NEKSHelper.newInstance(provider);
//
//            byte[] cipherText = neksHelper.encryptionByPublicKey(alias, paddingType, plainText);
//            System.out.println("Cipher Text:");
//            System.out.println(WordUtils.wrap(Hex.toHexString(cipherText).toUpperCase(), 60, null, true));
//
//            byte[] clearText = neksHelper.decryptionByPrivateKey(alias, paddingType, cipherText);
//            System.out.println("Cipher Verified: " + Arrays.equals(plainText, clearText));
//
//        }
//        catch(NoSuchAlgorithmException nsae){
//            nsae.printStackTrace();
//        }
//        catch(CertificateException ce){
//            ce.printStackTrace();
//        }
//        catch(NoSuchProviderException nspre){
//            nspre.printStackTrace();
//        }
//        catch(KeyStoreException kse){
//            kse.printStackTrace();
//        }
//
//        System.out.println("End RSAKeyGen program.");
//    }
//}*/
