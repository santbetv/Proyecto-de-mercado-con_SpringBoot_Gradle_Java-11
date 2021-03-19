package com.santbetv.developermercado.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santbetv.developermercado.domain.dto.AuthenticationRequest;
import com.santbetv.developermercado.domain.dto.AuthenticationResponse;
import com.santbetv.developermercado.domain.service.MercadoUserDetailsService;
import com.santbetv.developermercado.web.security.JWTUtil;


/**
 * RestController que permite crear el endpoint, de autenticacion 
 * 
 * @author rizzoli
 *
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MercadoUserDetailsService mercadoUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest request) {
    	String jwt ="";
    	UserDetails userDetails =null;
		try {
			// obtenemos el usuario y la contraseña de pasados por el body y creamos la
			// solicitud de autenticacion con authenticationManager
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			userDetails = mercadoUserDetailsService.loadUserByUsername(request.getUsername());

			jwt = jwtUtil.generateToken(userDetails);
			return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);

			// Si tenemos un error en el usuario o contraseña con BadCredentialsException
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>( HttpStatus.FORBIDDEN);
		}
	    
    }
}
