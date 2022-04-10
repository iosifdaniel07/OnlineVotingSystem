package com.sistemonlinevoting.Server;

import com.sistemonlinevoting.Server.dataBase.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionDecryption {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionDecryption.class);


    public static byte[] decrypWithPrivateKey(String message){

        byte[] messageByets = Base64.getDecoder().decode(message);
        byte[] textDecrypted = new byte[0];

        try{
            Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            rsa.init(Cipher.DECRYPT_MODE,ServerApplication.privKey);
            textDecrypted = rsa.doFinal(messageByets);


        }catch(NoSuchAlgorithmException e){
            LOGGER.error(e.toString());
        }catch(NoSuchPaddingException ee){
            LOGGER.error(ee.toString());
        }catch(IllegalBlockSizeException eee){
            LOGGER.error(eee.toString());
        }catch(InvalidKeyException eeee){
            LOGGER.error(eeee.toString());
        }catch(BadPaddingException eeeee){
            LOGGER.error(eeeee.toString());
        }

         return textDecrypted;
    }


    public static String encryptWithSessionKey(String message, SecretKey key){

        String encryptmessage="";

        try{
            Cipher des = Cipher.getInstance("DES");
            des.init(Cipher.ENCRYPT_MODE,key);
            byte[] textEncrypted = des.doFinal(message.getBytes());
            encryptmessage = Base64.getEncoder().encodeToString(textEncrypted);

        }catch(NoSuchAlgorithmException e){
            LOGGER.error(e.toString());
        }catch(NoSuchPaddingException ee){
            LOGGER.error(ee.toString());
        }catch(IllegalBlockSizeException eee){
            LOGGER.error(eee.toString());
        }catch(InvalidKeyException eeee){
            LOGGER.error(eeee.toString());
        }catch(BadPaddingException eeeee){
            LOGGER.error(eeeee.toString());
        }

        return encryptmessage;
    }

    public static String decryptWithSessionKey(String message, SecretKey key){

        byte[] messageByets = Base64.getDecoder().decode(message);
        String dncryptmessage="";

        try{
            Cipher des = Cipher.getInstance("DES");
            des.init(Cipher.DECRYPT_MODE,key);
            byte[] textDncrypted = des.doFinal(messageByets);
            dncryptmessage = new String(textDncrypted);

        }catch(NoSuchAlgorithmException e){
            LOGGER.error(e.toString());
        }catch(NoSuchPaddingException ee){
            LOGGER.error(ee.toString());
        }catch(IllegalBlockSizeException eee){
            LOGGER.error(eee.toString());
        }catch(InvalidKeyException eeee){
            LOGGER.error(eeee.toString());
        }catch(BadPaddingException eeeee){
            LOGGER.error(eeeee.toString());
        }

        return dncryptmessage;
    }
}
