package com.stage.stage.ressource;

import com.stage.stage.document.Product;
import com.stage.stage.document.Role;
import com.stage.stage.document.User;
import com.stage.stage.exception.ApiException;
import com.stage.stage.exception.ApiRequestException;
import com.stage.stage.repository.RoleRepository;
import com.stage.stage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RegistrationController {

    @Autowired
    UserRepository userRepo ;

    @Autowired
    RoleRepository roleRepo ;

    @Autowired
    PasswordEncoder encoder ;

    public boolean mailIsPresentInPassword (String username , String password){

        int arobIndex = username.indexOf("@");
        String usernameTillArob = username.substring(0,arobIndex-1);
        int INDEX = 0 ;
        boolean extractTillArob = true ;

        if (usernameTillArob.contains(".")){
            extractTillArob= false ;
            INDEX = usernameTillArob.indexOf(".") ;
            System.out.println("1");
        }
        else if (usernameTillArob.contains("-")){
            extractTillArob= false ;
            INDEX = usernameTillArob.indexOf("-") ;
            System.out.println("2");
        }
        else if (usernameTillArob.contains("_")){
            extractTillArob= false ;
            INDEX = usernameTillArob.indexOf("_") ;
            System.out.println("3");
        }

        System.out.println(extractTillArob);
        System.out.println(INDEX);

        List<String> extractedUsername = new ArrayList<>() ;
        if (extractTillArob){
            extractedUsername.add(usernameTillArob);
        }
        else{
            extractedUsername.add(username.substring(0,INDEX-1));
            extractedUsername.add(username.substring(INDEX+1,arobIndex));
        }

        for (String s :extractedUsername) {
            if (password.contains(s)){
                return true ;
            }
        }


        return false;
    }
    public boolean mailIsValid(String username){

        //---Regex stuff
        String mailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(mailRegex);
        Matcher matcher = pattern.matcher(username);

        if (matcher.matches()){
            return true ;
        }

        return false;
    }
    public boolean passwordIsStrong(String password){

        String specialCharacters=" !#$%&'()*+,-./:;<=>?@[]^_`{|}~" ;
        String passwordSplitted[]=password.split("");
        int count=0;
        for (int i=0;i<passwordSplitted.length;i++)
        {
            if (specialCharacters.contains(passwordSplitted[i]))
            {
                count++;
            }
        }
        if (count >= 3){
            return  true ;
        }
        return  false ;
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public void addProduct(@RequestBody User user) {

        String username = user.getUsername();
        String password = user.getPassword() ;
        boolean emailExist = false ;

        //--If unique mail
        List<User> users = userRepo.findAll();
        for (User u: users) {
            if (username.equals(u.getUsername())){
                emailExist = true ;
                break;
            }
        }


        //--Throwing custom exceptions
        if (mailIsValid(username) == false){
            throw new ApiRequestException("INVALID_MAIL");
        }
        else if (emailExist){
            throw new ApiRequestException("MAIL_EXIST");
        }
        else if (password.length() < 10){
            throw new ApiRequestException("MIN_PASSWORD_LENGTH_10");
        }
        else if (mailIsPresentInPassword(username , password) == true){
            throw new ApiRequestException("MAIL_IS_PRESENT_IN_PASSWORD");
        }
        else if (passwordIsStrong(password) == false){
            throw new ApiRequestException("PASSWORD_NOT_STRONG");
        }

        //--Register new user
        Optional<Role> userRole = roleRepo.findById("ROLE_USER");
        System.out.println(userRole.get());
        User u = new User(user.getUsername(),encoder.encode(user.getPassword()) , true , userRole.get());
        userRepo.save(u);

    }
}
