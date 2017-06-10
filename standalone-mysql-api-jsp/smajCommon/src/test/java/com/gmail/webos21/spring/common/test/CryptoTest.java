/*
 * Copyright 2017 Cheolmin Jo (webos21@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gmail.webos21.spring.common.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gmail.webos21.spring.common.cipher.CryptoHelper;
import com.gmail.webos21.spring.common.http.Base64WebSafe;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestApplication.class })
public class CryptoTest {

	public static String ALG_RSA = "RSA/ECB/PKCS1Padding";
	public static String ALG_HASH = "SHA1PRNG";
	public static int PKI_KEY_LEN = 1024;

	public static String CHARSET = "UTF-8";

	@Test
	public void testRSA() {

		// ----------------------------
		// PKI Test
		// ----------------------------

		KeyPair keyPair = CryptoHelper.genKeyPair(ALG_RSA, ALG_HASH, PKI_KEY_LEN);
		byte[] privKey = CryptoHelper.getPrivateKey(keyPair).getEncoded();
		byte[] pubKey = CryptoHelper.getPublicKey(keyPair).getEncoded();

		System.out.println("PrivKey = " + Base64WebSafe.encode(privKey));
		System.out.println("PubKey  = " + Base64WebSafe.encode(pubKey));

		String plainText = "이것은 테스트입니다.";

		// Private Key Encryption
		byte[] privEncBytes = CryptoHelper.encryptWithPrivateKey(ALG_RSA, privKey, plainText, CHARSET);
		System.out.println("PrivEncText = " + Base64WebSafe.encode(privEncBytes));

		// Public Key Decryption
		byte[] pubDecBytes = CryptoHelper.decryptWithPublicKey(ALG_RSA, pubKey, privEncBytes);
		try {
			System.out.println("pubDecText  = " + new String(pubDecBytes, CHARSET));
		} catch (UnsupportedEncodingException e4) {
			e4.printStackTrace();
		}

		// Public Key Encryption
		byte[] pubEncBytes = CryptoHelper.encryptWithPublicKey(ALG_RSA, pubKey, plainText, CHARSET);
		System.out.println("pubEncText  = " + Base64WebSafe.encode(pubEncBytes));

		// Private Key Decryption
		byte[] privDecBytes = CryptoHelper.decryptWithPrivateKey(ALG_RSA, privKey, pubEncBytes);
		try {
			System.out.println("privDecText = " + new String(privDecBytes, CHARSET));
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}

		// ----------------------------
		// AES Test
		// ----------------------------

		byte[] aesKey = CryptoHelper.genAESKey().getEncoded();
		byte[] iv = Arrays.copyOf(aesKey, 16);

		byte[] aesEncBytes = CryptoHelper.encryptWithAESKey(aesKey, iv, plainText, CHARSET);
		System.out.println("aesEncText  = " + Base64WebSafe.encode(aesEncBytes));

		byte[] aesDecBytes = CryptoHelper.decryptWithAESKey(aesKey, iv, aesEncBytes);
		try {
			System.out.println("aesDecText  = " + new String(aesDecBytes, CHARSET));
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}

		File testFile = new File("/tmp/cipher.aes");

		Cipher aesEncipher = CryptoHelper.getEncryptAESCipher(aesKey, iv);
		try {
			CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(testFile), aesEncipher);
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(cos));
			pw.println(plainText);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String aesCipherStreamText = null;
		Cipher aesDecipher = CryptoHelper.getDecryptAESCipher(aesKey, iv);
		try {
			CipherInputStream cis = new CipherInputStream(new FileInputStream(testFile), aesDecipher);
			BufferedReader br = new BufferedReader(new InputStreamReader(cis));
			aesCipherStreamText = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("aesCipherStreamText  = " + aesCipherStreamText);

		// ----------------------------
		// Short AES Test
		// ----------------------------

		byte[] shortAesKey = null;
		try {
			shortAesKey = "한글키입니다.".getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] shortAesEncBytes = CryptoHelper.encryptWithAESKey(shortAesKey, iv, plainText, CHARSET);
		System.out.println("shortAesEncText  = " + Base64WebSafe.encode(shortAesEncBytes));

		byte[] shortAesDecBytes = CryptoHelper.decryptWithAESKey(shortAesKey, iv, shortAesEncBytes);
		try {
			System.out.println("shortAesDecText  = " + new String(shortAesDecBytes, CHARSET));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// ----------------------------
		// Long AES Test
		// ----------------------------

		byte[] longAesKey = Arrays.copyOf(shortAesKey, shortAesKey.length + aesKey.length);
		System.arraycopy(aesKey, 0, longAesKey, shortAesKey.length, aesKey.length);

		byte[] longAesEncBytes = CryptoHelper.encryptWithAESKey(longAesKey, iv, plainText, CHARSET);
		System.out.println("longAesEncText  = " + Base64WebSafe.encode(longAesEncBytes));

		byte[] longAesDecBytes = CryptoHelper.decryptWithAESKey(longAesKey, iv, longAesEncBytes);
		try {
			System.out.println("longAesDecText  = " + new String(longAesDecBytes, CHARSET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testMD() {
		String plainText = "1234";

		System.out.println("md5hash  = " + Base64WebSafe.encode(CryptoHelper.md5hash(plainText, CHARSET)));
		System.out.println("sha1hash = " + Base64WebSafe.encode(CryptoHelper.sha1hash(plainText, CHARSET)));
		System.out.println("sha2hash = " + Base64WebSafe.encode(CryptoHelper.sha2hash(plainText, CHARSET)));
	}
}
