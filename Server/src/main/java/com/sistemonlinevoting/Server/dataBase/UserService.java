package com.sistemonlinevoting.Server.dataBase;

import com.sistemonlinevoting.Server.EncryptionDecryption;
import com.sistemonlinevoting.Server.ServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Service
public class UserService {

   private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

   @Autowired
   private UserRepository repository;

   @Async
    public User saveUser(User user) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


             byte[] decodedKey = EncryptionDecryption.decrypWithPrivateKey(user.getSessionKey());
             SecretKey secretKey = new SecretKeySpec(decodedKey, 0,decodedKey.length, "DES");

             user.setPassword(EncryptionDecryption.decryptWithSessionKey(user.getPassword(),secretKey));
             user.setEmail(EncryptionDecryption.decryptWithSessionKey(user.getEmail(),secretKey));
             user.setFname(EncryptionDecryption.decryptWithSessionKey(user.getFname(),secretKey));
             user.setLname(EncryptionDecryption.decryptWithSessionKey(user.getLname(),secretKey));
             user.setUsername(EncryptionDecryption.decryptWithSessionKey(user.getUsername(),secretKey));
             user.setPhone(EncryptionDecryption.decryptWithSessionKey(user.getPhone(),secretKey));
             user.setCnp(EncryptionDecryption.decryptWithSessionKey(user.getCnp(),secretKey));
             user.setSessionKey(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
             user.setPresedinte(0);
             user.setEuroparlamentari(0);
             user.setParlament(0);
             repository.save(user);

            }
        });

        thread.start();

        try{
            thread.join();
        }catch(InterruptedException e){
            LOGGER.error("create problem");
        }

        return user;
    }

    @Async
    public String sendPublicKey(){
       return ServerApplication.getPublicKey();
    }

    @Async
    public boolean checkUser(String username){

         return repository.findByUsername(username).isPresent();
    }

    @Async
   public User getPasswordForUser(String username){
       return repository.findByusername(username);
    }


  @Async
  public Long getIdForUser(String username){
     User user =  repository.findByusername(username);
     return user.getId();
  }

  @Async
  public boolean setSessionKey(String username, String sessionkey){

      User user =  repository.findByusername(username);
      if(user != null){

        byte[] decodedKey = EncryptionDecryption.decrypWithPrivateKey(sessionkey);
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0,decodedKey.length, "DES");
        user.setSessionKey(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        repository.save(user);
        LOGGER.error("session key saved");
        return true;
      }
      return false;
  }

   @Async
    public String numeprenume(Long id){
      User user = repository.findByid(id);

      return user.getFname() + " " + user.getLname();
   }

   @Async
    public User getUser(Long id){
       return repository.findByid(id);
   }

}
