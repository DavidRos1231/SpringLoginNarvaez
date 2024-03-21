package mx.edu.utez.sda.david8cjwt.controllers;

import mx.edu.utez.sda.david8cjwt.config.SecurityConfig;
import mx.edu.utez.sda.david8cjwt.entities.AuthRequest;
import mx.edu.utez.sda.david8cjwt.entities.UserInfo;
import mx.edu.utez.sda.david8cjwt.services.JwtService;
import mx.edu.utez.sda.david8cjwt.services.UserInfoService;
import mx.edu.utez.sda.david8cjwt.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<CustomResponse<String>> register(@RequestBody UserInfo userInfo){
       return new ResponseEntity<>(userInfoService.guardarUser(userInfo), HttpStatus.OK);
    }
    @PostMapping("/login")
    private ResponseEntity<CustomResponse<String>> login(@RequestBody AuthRequest authRequest){
        try {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            passwordEncoder.encode(authRequest.getPassword()))
            );
            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(authRequest.getUsername());
                return new ResponseEntity<>(new CustomResponse<>(token,false,200,"ok"),HttpStatus.OK);
            }else {
                System.out.println("No autenticado");
                throw new UsernameNotFoundException("Usuario invalido");
            }
        }catch (Exception e){
            throw new UsernameNotFoundException("Usuario invalido");
        }
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String justAdmin(){
        return "Este endpoint es solamente para admins";
    }
    @GetMapping("/usuarios")
    @PreAuthorize("HasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public String usuarios(){
        return "Este endpoint es para usuarios y admin";
    }


}