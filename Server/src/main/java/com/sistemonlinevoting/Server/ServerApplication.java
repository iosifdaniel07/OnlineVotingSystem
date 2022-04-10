package com.sistemonlinevoting.Server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.*;
import java.util.Base64;

@SpringBootApplication
@EnableAutoConfiguration
public class ServerApplication {

	public static PrivateKey privKey;
	public static PublicKey publicKey;
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);

	public static String getPublicKey(){
		return Base64.getEncoder().encodeToString(publicKey.getEncoded());
	}

	public static void main(String[] args) {

		try{
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  //RSA
			keyPairGen.initialize(1024);
			KeyPair pair = keyPairGen.generateKeyPair();
			privKey = pair.getPrivate();
			publicKey = pair.getPublic();

		//	System.out.println(Base64.getEncoder().encodeToString(publicKey.getEncoded()));

			/*try{
               String stringKey = DatatypeConverter.printHexBinary(publicKey.getEncoded());

				byte[] key = DatatypeConverter.parseHexBinary(stringKey);
				X509EncodedKeySpec ks = new X509EncodedKeySpec(key);
				KeyFactory kf = KeyFactory.getInstance("RSA");
				PublicKey k = kf.generatePublic(ks);
				System.out.println(DatatypeConverter.printHexBinary(k.getEncoded()));

			}catch(InvalidKeySpecException e){

			}*/


		}catch (NoSuchAlgorithmException e){
          LOGGER.error("Problem with generate keys");
		}

		SpringApplication.run(ServerApplication.class, args);
	}


}
