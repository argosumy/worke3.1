package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import project.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    SecurityConfiguration(MyUserDetailsService myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;
    }
    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        //  auth.inMemoryAuthentication()
      //        .withUser("VALERA").password("{noop}QWERTY").roles("ADMIN");
        /* auth
                 .jdbcAuthentication()
                 .dataSource(oracleDaoConnection.dataSource)
                 .usersByUsernameQuery(ConstSQLTable.SELECT_LOGIN_PASSWORD)
                 .authoritiesByUsernameQuery(ConstSQLTable.SELECT_LOGIN_ROLE);
                 */

         auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and();
            //  .csrf().disable().authorizeRequests();//???
    }

}
