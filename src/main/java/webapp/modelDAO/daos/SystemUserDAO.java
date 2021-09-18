package webapp.modelDAO.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import webapp.model.entities.SystemUser;
import webapp.modelDAO.repos.SystemUserRepo;

@Component
public class SystemUserDAO {

    private final SystemUserRepo repo;
    private PasswordEncoder encoder;

    @Autowired
    public SystemUserDAO(SystemUserRepo repo,
                         PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public boolean isDBEmpty() {
        return (repo.findAll().size() == 0) ? true : false;
    }

    public void create(SystemUser systemUser) {
        systemUser.setPassword(
                encoder.encode(systemUser.getPassword())
        );
        repo.save(systemUser);
    }

    public void delete() {
        repo.deleteAll();
    }
}
