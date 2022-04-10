package com.sistemonlinevoting.Server.dataBase;


import com.sistemonlinevoting.Server.ServerApplication;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


@RestController
public class UserController {    //GET is used to request data from a specified resource.
                                //POST is used to send data to a server to create/update a resource.

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

   @PostMapping("/user/save")
    public User saveUser(@RequestBody User newuser) {

     return userService.saveUser(newuser);

   }

    @GetMapping("/publickey")
    public String getPublicKey(){
        return userService.sendPublicKey();
    }

   @GetMapping("/checkUser/{username}")
    public boolean checkUser( @PathVariable("username") String username){
       return  userService.checkUser(username);
   }

   @GetMapping("/user/password/{username}")
   public String getPasswordForUsername( @PathVariable("username") String username){

       User user = userService.getPasswordForUser(username);
       return user.getPassword();
   }

}
