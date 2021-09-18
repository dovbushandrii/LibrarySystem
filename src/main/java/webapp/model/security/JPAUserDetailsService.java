package webapp.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webapp.modelDAO.repos.SystemUserRepo;

@Service("JPAUserDetailsService")
public class JPAUserDetailsService implements UserDetailsService {

    private final SystemUserRepo systemUserRepo;

    @Autowired
    public JPAUserDetailsService(SystemUserRepo systemUserRepo){
        this.systemUserRepo = systemUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return SecurityUser.fromUser(
                systemUserRepo
                        .findByUsername(username)
                        .orElseThrow(()->
                new UsernameNotFoundException("There is no such user")));
    }
}
