package com.neo.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * Created by neowyp on 2016/5/5.
 */
public class KeyVerify {
    private static final String PATH_BASE = "D:\\Code\\Code_IntellijIdea\\SSHBoss\\";
    private static final String PATH_KEYSTORE = "D:\\Code\\Code_IntellijIdea\\SSHBoss\\michael.keystore";
    private static final String FILE_CERT_SUFFIX = ".crt";
    private static final String FILE_KEY_SUFFIX = ".p8";
    private static final String KEY_TYPE = "JKS";
    private static final String KS_PWD = "michaelpwd2";
    private static final String KEY_PWD = "michaelpwd";
    private static final String KS_ALIAS = "michaelkey";

    public static void main(String[] args) {
        testSignVerify();
//        exportPKCS8("michaelkey", "michaelpwd");
//        exportPKCS8("gomeplus", "gomepwd");

        String crtPath = "D:\\Code\\Code_IntellijIdea\\SSHBoss\\michaelkey.crt";
        String keyPath = "D:\\Code\\Code_IntellijIdea\\SSHBoss\\michaelkey.p8";

        String plain = "hello";
        String sign = sign(keyPath, plain);
        System.out.println("sign string is :" + sign);
        boolean flag = verifySign(crtPath, sign, plain);
        System.out.println("verify result is:" + flag);

        verifyByFile(crtPath, sign, plain);
    }

    private static void verifyByFile(String path, String sign, String plain) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            FileInputStream fileInputStream = new FileInputStream(path);
            X509Certificate cert = (X509Certificate) cf.generateCertificate(fileInputStream);

//            System.out.println(cert.toString());

            BASE64Decoder base64 = new BASE64Decoder();
            byte[] signed = base64.decodeBuffer(sign);
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(cert);
            sig.update(plain.getBytes("utf8"));
            boolean flag = sig.verify(signed);
            System.out.println("verifyByFile is " + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] loadFile(String path) {
//        InputStream is = new FileInputStream("");
        return null;
    }

    private static String sign(String keyPath, String plain) {
        try {
            return SignUtil.sign(plain, keyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean verifySign(String crtPath, String sign, String plain) {
        try {
            return SignUtil.verify(plain, sign, crtPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void exportPKCS8(String alias, String keypass) {
        try {
            //读取keystore文件到KeyStore对象
            FileInputStream in = new FileInputStream(PATH_KEYSTORE);
            KeyStore ks = KeyStore.getInstance(KEY_TYPE);// JKS: Java KeyStoreJKS，可以有多种类型
            ks.load(in, KS_PWD.toCharArray());
            in.close();

            //从keystore中读取证书和私钥
            Certificate cert = ks.getCertificate(alias);
            PublicKey publicKey = cert.getPublicKey();
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, keypass.toCharArray());

            String outputFile = PATH_BASE + alias + FILE_KEY_SUFFIX;
            System.out.println("outputFile is:" + outputFile);
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            byte[] enKeyBytes = privateKey.getEncoded();
            outputFileStream.write(enKeyBytes);
            outputFileStream.flush();
            outputFileStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    keytool -genkey -alias michaelkey -keyalg RSA -keysize 1024 -keypass michaelpwd -validity 365 -keystore michael.keystore -storepass michaelpwd2
    keytool -genkey -alias gomeplus -keyalg RSA -keysize 1024 -keypass gomepwd -validity 365 -keystore michael.keystore -storepass michaelpwd2

    keytool -list  -v -keystore michael.keystore -storepass michaelpwd2
    keytool -list -rfc -keystore michael.keystore -storepass michaelpwd2

    keytool -export -alias michaelkey -keystore michael.keystore -file michaelkey.crt -storepass michaelpwd2
    keytool -export -alias gomeplus -keystore michael.keystore -file gomeplus.crt -storepass michaelpwd2

    D:\Code\Code_IntellijIdea\SSHBoss\michael.keystore
    D:\Code\Code_IntellijIdea\SSHBoss\michael.crt
     */
    private static void testSignVerify() {
        try {
            //读取keystore文件到KeyStore对象
            FileInputStream in = new FileInputStream(PATH_KEYSTORE);
            KeyStore ks = KeyStore.getInstance(KEY_TYPE);// JKS: Java KeyStoreJKS，可以有多种类型
            ks.load(in, KS_PWD.toCharArray());
            in.close();

            //从keystore中读取证书和私钥
            Certificate cert = ks.getCertificate(KS_ALIAS);
            PublicKey publicKey = cert.getPublicKey();
//            Enumeration<String> als =  ks.aliases();
//            System.out.println(KS_ALIAS.toString());
//            Key keytmp = ks.getKey(KS_ALIAS, KEY_PWD.toCharArray());
//            System.out.println(cert.toString());

            PrivateKey privateKey = (PrivateKey) ks.getKey(KS_ALIAS, KEY_PWD.toCharArray());

            String plain = "hello";
            //签名
            byte[] sb = (byte[]) null;
            // 获取签名实例
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            signature.update(plain.getBytes("utf8"));
            sb = signature.sign();

            BASE64Encoder base64En = new BASE64Encoder();
            String b64Str = base64En.encode(sb);

            BufferedReader br = new BufferedReader(new StringReader(b64Str));
            String tmp = "";
            String signStr = ""; //签名串
            while ((tmp = br.readLine()) != null) {
                signStr = signStr + tmp;
            }
            System.out.println("sign string is ;" + signStr);
            BASE64Decoder base64De = new BASE64Decoder();
            byte[] signed = base64De.decodeBuffer(signStr);
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(cert);
            sig.update(plain.getBytes("utf8"));
            boolean flag = sig.verify(signed);

            System.out.println("verify result is " + flag);

            fileverify(plain, signed);
            verifyByFile("D:\\Code\\Code_IntellijIdea\\SSHBoss\\michaelkey.crt", signStr, plain);

            boolean tf = SignUtil.verify(plain, signStr, "D:\\Code\\Code_IntellijIdea\\SSHBoss\\michaelkey.crt");
            System.out.println("SignUtil.verify result is " + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fileverify(String plain, byte[] signed) throws CertificateException, FileNotFoundException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream fileInputStream = new FileInputStream("D:\\Code\\Code_IntellijIdea\\SSHBoss\\michaelkey.crt");
        X509Certificate myCert = (X509Certificate) cf.generateCertificate(fileInputStream);
        Signature signature1 = Signature.getInstance("SHA1withRSA");
        signature1.initVerify(myCert);
        signature1.update(plain.getBytes("utf8"));
        boolean flag1 = signature1.verify(signed);
        System.out.println("verify result is " + flag1);
    }

    @Deprecated
    private void depFunction() {
        try {
            KeyStore keystore = null;
            String alias = null;
            char[] password = null;
            Certificate cert = keystore.getCertificate(alias);
            PublicKey publicKey = cert.getPublicKey();
            Key key = keystore.getKey(alias, password);
            KeyPair keyPair = new KeyPair(publicKey, (PrivateKey) key);
            PrivateKey privateKey = keyPair.getPrivate();
            byte[] privateKeyBytes = privateKey.getEncoded();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

