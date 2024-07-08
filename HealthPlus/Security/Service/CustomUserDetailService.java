package com.Api.HealthPlus.Security.Service;

import com.Api.HealthPlus.Entity.User;
import com.Api.HealthPlus.Repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepositary userRepositary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositary.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException(username+ " this email exit does not exit");
        }
        return new CustomUserDetails(user);
    }
}
