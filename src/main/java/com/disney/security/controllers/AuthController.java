package com.disney.security.controllers;

import com.disney.security.dto.JwtDto;
import com.disney.security.dto.LoginUser;
import com.disney.security.dto.NewUser;
import com.disney.security.entities.Role;
import com.disney.security.entities.User;
import com.disney.security.enums.RoleList;
import com.disney.security.jwt.JwtProvider;
import com.disney.security.services.RoleService;
import com.disney.security.services.UserService;
import com.disney.security.message.Message;
//import com.souldev.security.entities.Message;
//import com.souldev.security.security.dtos.JwtDto;
//import com.souldev.security.security.dtos.LoginUser;
//import com.souldev.security.security.dtos.NewUser;
//import com.souldev.security.security.entities.Role;
//import com.souldev.security.security.entities.User;
//import com.souldev.security.security.enums.RoleList;
//import com.souldev.security.security.jwt.JwtProvider;
//import com.souldev.security.security.services.RoleService;
//import com.souldev.security.security.services.UserService;
//import org.apache.logging.log4j.message.Message;
import com.disney.security.services.EmailService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
//import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Log logger = LogFactory.getLog(getClass());  //Para logueo

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private EmailService emailService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUser loginUser, BindingResult bidBindingResult){
        if(bidBindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        try {
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword());
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateToken(authentication);
                JwtDto jwtDto = new JwtDto(jwt);
                return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
                return new ResponseEntity<>(new Message("Revise sus credenciales"), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<Object> resgister(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getUserName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleList.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleList.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        emailService.simpleTextMessage(user.getEmail()); //Mail Simple, donde le paso por parametro el destinatario que recibira el mail.
//        emailService.fileTextMessage(user.getEmail());   //Mail Simple con Adjunto
        logger.info("El correo se envio de manera exitosa!!");
        return new ResponseEntity<>(new Message("Registro exitoso! Inicie sesión"), HttpStatus.CREATED);
    }

}
