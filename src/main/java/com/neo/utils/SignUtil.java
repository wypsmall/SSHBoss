/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2013-6-28����2:37:35</p>
 * @author wangyunpeng
 * @version 1.0
 */
package com.neo.utils;

import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;


/** 
 * desc:�ı�ǩ����ǩ������
 * <p>�����ˣ�wangyunpeng �������ڣ�2013-6-28 </p>
 * @version V1.0  
 */
public class SignUtil {

	/**
	 * desc:�ı�ǩ�� <p>�����ˣ�wangyunpeng , 2013-6-28 ����2:44:00</p>
	 * @param plain ���������
	 * @param priCertFilePath ǩ��֤���·��
	 * @return ǩ���������
	 * @throws Exception �쳣
	 */
	public static String sign(String plain, String priCertFilePath) throws Exception {
		// �ж�֤��·���Ƿ�Ϊ��
		if(StringUtils.isEmpty(priCertFilePath) ) {
			throw new Exception("the private cert file path is empty [" + priCertFilePath + " ]");
		}
		FileInputStream fis = null;
		byte[] kb = (byte[]) null;
		try {
			File f = new File(priCertFilePath);
			kb = new byte[(int) f.length()];
			fis = new FileInputStream(f);
			fis.read(kb);
		} catch (Exception e) {// ����֤���ļ��쳣
			throw new Exception("load the primary key failed [" + priCertFilePath + "]");
		} finally {// �ļ���finally����
			if(fis != null ) {
				try {
					fis.close();
				} catch (Exception localException1) {
				} finally {
					fis = null;
				}
			}
		}
		// ����ǩ������
		return sign(plain, kb);
	}

	/**
	 * desc:�ı�ǩ�� <p>�����ˣ�wangyunpeng , 2013-6-28 ����2:45:32</p>
	 * @param plain ���������
	 * @param priBytes ǩ��֤���ֽ�����
	 * @return ǩ���������
	 * @throws Exception �쳣
	 */
	public static String sign(String plain, byte[] priBytes) throws Exception {
		// �ж�����������Ƿ�Ϊ��
		if(StringUtils.isEmpty(plain) ) {
			throw new Exception("the to sign:plain string is empty");
		}
		PKCS8EncodedKeySpec peks = null;
		KeyFactory kf = null;
		PrivateKey pk = null;
		try {
			// ����ǩ��֤��
			peks = new PKCS8EncodedKeySpec(priBytes);
			kf = KeyFactory.getInstance("RSA");
			pk = kf.generatePrivate(peks);
		} catch (Exception e) {// ����ǩ��֤���쳣
			throw new Exception("invalid primary key format");
		}
		Signature sig = null;
		byte[] sb = (byte[]) null;
		try {
			// ��ȡǩ��ʵ��
			sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(pk);
			sig.update(plain.getBytes("utf8"));
			sb = sig.sign();
		} catch (Exception e) {
			throw new Exception("sign procedure failed");
		}
		String b64Str = null;
		try {
			// Base64 ת��
			BASE64Encoder base64 = new BASE64Encoder();
			b64Str = base64.encode(sb);
		} catch (Exception e) {
			throw new Exception("base64 generation failed");
		}
		try {
			BufferedReader br = new BufferedReader(new StringReader(b64Str));
			String tmpStr = "";
			String tmpStr1 = "";
			while((tmpStr = br.readLine()) != null ) {
				tmpStr1 = tmpStr1 + tmpStr;
			}
			b64Str = tmpStr1;
			return b64Str;
		} catch (Exception br) {
			throw new Exception("base64 generation failed");
		}
	}

	/**
	 * desc:�ı�ǩ����ǩ <p>�����ˣ�wangyunpeng , 2013-6-28 ����2:55:58</p>
	 * @param plainString ��ǩ������
	 * @param signString ����ǩ������
	 * @param pubCertFilePath ��Կ֤��·��
	 * @return �Ƿ�ǩ��ͨ��
	 * @throws Exception �쳣
	 */
	public static boolean verify(String plainString, String signString, String pubCertFilePath) throws Exception {
		FileInputStream fis = null;
		byte[] cb = (byte[]) null;
		try {
			File f = new File(pubCertFilePath);
			cb = new byte[(int) f.length()];
			fis = new FileInputStream(f);
			int count = fis.read(cb);
//			System.out.println("=============="+count);
		} catch (Exception e) {
			throw new Exception("load the cert failed");
		} finally {
			if(fis != null ) {
				try {
					fis.close();
				} catch (Exception localException1) {
				} finally {
					fis = null;
				}
			}
		}
		return verify(plainString, signString, cb);
	}

	/**
	 * desc:�ı�ǩ����ǩ <p>�����ˣ�wangyunpeng , 2013-6-28 ����3:09:03</p>
	 * @param plainString ��ǩ������
	 * @param signString ����ǩ������
	 * @param pubBytes ��Կ֤��·���ֽ�
	 * @return �Ƿ�ǩ��ͨ��
	 * @throws Exception �쳣
	 */
	public static boolean verify(String plainString, String signString, byte[] pubBytes) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(pubBytes);
		CertificateFactory cf = null;
		X509Certificate cert = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			cert = (X509Certificate) cf.generateCertificate(bais);
		} catch (Exception e) {
			throw new Exception("load the cert failed");
		}
		try {
			BASE64Decoder base64 = new BASE64Decoder();
			System.out.println("==>"+signString);
			byte[] signed = base64.decodeBuffer(signString);
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(cert);
			sig.update(plainString.getBytes("utf8"));
			return sig.verify(signed);
		} catch (Exception base64) {
			throw new Exception("verify procedure failed");
		}
	}
}
