package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.dao.DaoConnection;
import project.entities.Entity;
import project.entities.PasswordLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private List<Entity> users = new ArrayList();
    private final DaoConnection con;

    @Autowired
    public MyUserDetailsService(DaoConnection con){
        this.con = con;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PasswordLogin passwordLogin = new PasswordLogin();
        users = passwordLogin.showAllEntity(con.connect());
        Optional<Entity> account = users.stream().filter(u -> u.getName().equals(s)).findAny();
        if(!account.isPresent()){
            throw new UsernameNotFoundException("User not found by name: " + s);
        }
        return toUserDetails((PasswordLogin) account.get());
    }

    private UserDetails toUserDetails(PasswordLogin account) {
        return User.withUsername(account.getName())
                .password("{noop}" + account.getPassword())
                .roles(account.getROLE()).build();
    }
}
