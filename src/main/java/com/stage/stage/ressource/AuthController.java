package com.stage.stage.ressource;

import com.stage.stage.document.Product;
import com.stage.stage.security.jwt.AuthenticationRequest;
import com.stage.stage.security.jwt.AuthenticationResponse;
import com.stage.stage.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    JwtUtil jwtUtil;

    @Qualifier("myUserDetailService")
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping(path = "/auth", consumes = "application/json")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws  Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername() ,
                    authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
             throw  new Exception("Username or password incorrect");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        final Date date = jwtUtil.extractExpiration(jwt);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String expirationDate = formatter.format(date);

        return ResponseEntity.ok(new AuthenticationResponse(jwt , expirationDate));

    }
}
