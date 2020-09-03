package main.java.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import main.java.project.dao.DaoConnection;
import main.java.project.entities.Entity;
import main.java.project.entities.Account;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private DaoConnection con;

    @Autowired
    public MyUserDetailsService(DaoConnection con){
        this.con = con;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AccountServiceImp accountService = new AccountServiceImp();
        List<Entity> users = accountService.showAllEntity(con.connect());
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
