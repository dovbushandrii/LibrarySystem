package webapp.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import webapp.modelDAO.daos.SystemUserDAO;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SystemUserDAO systemUserDAO;

    @Autowired
    public UserDetailsServiceImpl(SystemUserDAO systemUserDAO){
        this.systemUserDAO = systemUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return SecurityUser.fromUser(systemUserDAO.readByUsername(username));
    }
}
