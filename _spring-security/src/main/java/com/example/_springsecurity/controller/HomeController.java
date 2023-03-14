package com.example._springsecurity.controller;


import com.example._springsecurity.dto.AuthenticationRequest;
import com.example._springsecurity.dto.AuthenticationResponse;
import com.example._springsecurity.dto.UserLoginDTO;
import com.example._springsecurity.models.ERole;
import com.example._springsecurity.models.User;
import com.example._springsecurity.service.IUserService;
import com.example._springsecurity.service.security.MyUserDetailService;
import com.example._springsecurity.until.JwtUntil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class HomeController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUntil jwtUntil;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/home")
    public String hello() {
        return "Welcome to the Spring security";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.getAccount().setPassword(passwordEncoder.encode(user.getAccount().getPassword()));
        user.getAccount().setEnable(true);
        user.getAccount().setRole(ERole.User);
        return iUserService.save(user);
    }

    @GetMapping("/user")
    public String user() {
        return ("Welcome USER");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("Welcome ADMIN");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginLocal(@RequestBody AuthenticationRequest authenticationRequest) {
        return login(authenticationRequest);
    }


    private ResponseEntity<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        String jwt = null;
        UserLoginDTO userLoginDto = null;
        String status;
        HttpStatus httpStatus;
        try {
            // Check username & password exists in database?
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
            jwt = jwtUntil.generateToken(userDetails);
            User user = this.iUserService.getByUsername(authenticationRequest.getUsername());
            userLoginDto = this.modelMapper.map(user, UserLoginDTO.class);
            status = "Success";
            httpStatus = HttpStatus.OK;
        } catch (DisabledException disabledException) {
            // Catch Var enable = false
            status = "Account locked";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (BadCredentialsException badCredentialsException) {
            // Catch username & password exists in database
            status = "Wrong password";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
            status = "Username not exists";
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception exception) {
            status = "Error server";
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(new AuthenticationResponse(jwt, userLoginDto, status), httpStatus);
    }

}
