package be.odisee.brainstorm2023wa3sb.service;

import be.odisee.brainstorm2023wa3sb.domain.Persoon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("userContextService")
public class UserContextServiceImpl implements UserContextService {

    @Autowired
    protected KledingstukSessieService kledingstukSessieService =null; // ready for dependency injection

    @Override
    public Persoon getAuthenticatedPersoon() {
    
    	UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = principal.getUsername();

	    Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

	    Persoon thePersoon = kledingstukSessieService.zoekPersoonMetEmailadres(username);
	    return thePersoon;
    }
}
