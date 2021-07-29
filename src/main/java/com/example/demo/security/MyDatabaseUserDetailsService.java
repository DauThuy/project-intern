package com.example.demo.security;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyDatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
//    UserDetails loadUserByUsername(int id) throws UsernameNotFoundException {
//        Account user = accountRepository.findByAccountId(id);
//        List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList()); // (1)
//        return new User(user.getAccountName(), user.getAccountPassword(), grantedAuthorities); // (2)
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account user = accountRepository.findByEmailAddress(email);
        List<SimpleGrantedAuthority> grantedAuthorities = user.getAuthorities().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList()); // (1)
        return new User(user.getAccountName(), user.getAccountPassword(), grantedAuthorities); // (2)

//        Set<GrantedAuthority> authorities = new HashSet<>();
//        user.getAuthorities().forEach(p -> authorities.add(new SimpleGrantedAuthority((String) p)));
//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(user, null, authorities);
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}