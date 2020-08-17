package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.dao.DaoConnection;
import project.entities.Entity;
import project.entities.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private List<Entity> users = new ArrayList();
    @Autowired
    DaoConnection con;

    public MyUserDetailsService(){
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account passwordLogin = new Account();
        users = passwordLogin.showAllEntity(con.connect());
        Optional<Entity> account = users.stream().filter(u -> u.getName().equals(s)).findAny();
        if(!account.isPresent()){
            throw new UsernameNotFoundException("User not found by name: " + s);
        }
        return toUserDetails((Account) account.get());
    }

    private UserDetails toUserDetails(Account account) {
        return User.withUsername(account.getName())
                .password("{noop}" + account.getPassword())
                .roles(account.getRole()).build();
    }

}
