package com.sistemonlinevoting.Server.dataBase;

import com.sistemonlinevoting.Server.EncryptionDecryption;
import com.sistemonlinevoting.Server.ServerApplication;
import com.sistemonlinevoting.Server.thread.MethodDelegate;
import com.sistemonlinevoting.Server.thread.ThreadExecutorQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


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
             LOGGER.error(user.getPassword());
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

  /*  public List<User> getAllUser(){
      List<User> list = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(list::add);
        return list;
    }*/


}
