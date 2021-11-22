/*package fileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
	public static void fileProcessor(int cipherMode, String key, 
									File inputFile, File outputFile) {
		try {
			Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);
			
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte[] inputBytes = new byte[(int)inputFile.length()];
			inputStream.read(inputBytes);
			
			byte[] outputBytes = cipher.doFinal(inputBytes);
			
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			outputStream.write(outputBytes);
			
			inputStream.close();
			outputStream.close();
			
		} catch (NoSuchPaddingException | NoSuchAlgorithmException 
				| InvalidKeyException | BadPaddingException
				| IllegalBlockSizeException | IOException e) {
			
		}
	}
	
	public static String decrypt(int cipherMode, String key, String fileName) {
		String content = "";
		
		try (FileInputStream inputFile = new FileInputStream(fileName)) {
			Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(cipherMode, secretKey);
			
			try (
					CipherInputStream cipherIn = new CipherInputStream(inputFile, cipher);
					InputStreamReader inputReader = new InputStreamReader(cipherIn);
					BufferedReader reader = new BufferedReader(inputReader) 
				) {
				StringBuilder sb = new StringBuilder();
				String nextLine;
				while((nextLine = reader.readLine()) != null) {
					sb.append(nextLine);
				}
				content = sb.toString();
			}
			
		} catch (IOException | NoSuchAlgorithmException 
				| NoSuchPaddingException | InvalidKeyException e){
			
		}
		return content;
	}
}
*/
