package com.santbetv.developermercado.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * Encargado de generar la seguridad por usuario y contraseña
 * 
 * @author rizzoli
 *
 */

@Service
public class MercadoUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    private List<User> users = new ArrayList<>();

//    public MercadoUserDetailsService() {
//    	users.add(new User("santiago", "{noop}1234", new ArrayList<>()));
//    }
    
    @Bean
    public void iniciarLista(){
    	users.add(new User("santiago", passwordEncoder.encode("1234"), new ArrayList<>()));
    	users.add(new User("sebatian", passwordEncoder.encode("4567"), new ArrayList<>()));
    }
    
    /**
     * 
     * Logica que me permite validar si el usuario es correcto
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }

//        return user.get();
        return new User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
    }
    
    
    
//    @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//      return new User("santiago", "{noop}1234", new ArrayList<>());
//  }
    
    
}
