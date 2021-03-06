package com.seniorproj.WebDaw.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seniorproj.WebDaw.model.ApplicationUser;
import com.seniorproj.WebDaw.model.Role;
import com.seniorproj.WebDaw.model.Project;
import com.seniorproj.WebDaw.repo.RoleRepository;
import com.seniorproj.WebDaw.repo.UserRepository;
import com.seniorproj.WebDaw.repo.ProjectRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public ApplicationUser findUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<Project> findUserById(String Id) {
        return projectRepository.findById(Id);
    }

    public void saveUser(ApplicationUser user) {
        // user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
        user.setPass((user.getPass()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ApplicationUser user = userRepository.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else{
            throw new UsernameNotFoundException("Email not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(ApplicationUser user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(), authorities);
    }
}
