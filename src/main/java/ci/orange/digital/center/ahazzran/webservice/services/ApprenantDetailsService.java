package ci.orange.digital.center.ahazzran.webservice.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ci.orange.digital.center.ahazzran.webservice.entities.Apprenant;
import ci.orange.digital.center.ahazzran.webservice.repositories.ApprenantRepository;

@Service
public class ApprenantDetailsService implements UserDetailsService {

    private final ApprenantRepository apprenantRepository;

    public ApprenantDetailsService(ApprenantRepository apprenantRepository) {
        this.apprenantRepository = apprenantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Apprenant apprenant = apprenantRepository.findByEmail(email);
        if (apprenant == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }

        String role = "USER";
        return new User(apprenant.getEmail(),apprenant.getMotDePasse(),getAuthorities(role));  // Ajouter les autorités/roles appropriées
    
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
