package com.thecodeveal.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thecodeveal.app.entities.Authority;
import com.thecodeveal.app.entities.User;
import com.thecodeveal.app.repository.UserDetailsRepository;

@SpringBootApplication
public class SpringSecurityDemoAppApplication {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoAppApplication.class, args);
	}
	
	@PostConstruct
	protected void init() {
		
		List<Authority> authorityList=new ArrayList<>();
		
		authorityList.add(createAuthority("USER","User role"));
		//authorityList.add(createAuthority("ADMIN","Admin role"));
		
		User user=new User();
		
		user.setUserName("sirine");
		user.setNom("sirine");
	    user.setPrenom("amdouni");
	    user.setPassword(passwordEncoder.encode("sirine123"));
		user.setAdresse("mornaguia");
		user.setEmail("amdounisirine80@gmail.com");
		user.setNumero_de_telephone("+216 22929388");
		user.setDate_de_naissance(new Date(2000-05-25));
		user.setEnabled(true);
		user.setGenre("Femme");
		user.setEtat_civil("Celibataire");
		user.setAuthorities(authorityList);
		userDetailsRepository.save(user);
		
		
		
	}
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
	
	

}
