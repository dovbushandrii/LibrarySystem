package webapp.modelDAO.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import webapp.model.entities.SystemUser;
import webapp.modelDAO.repos.SystemUserRepo;

@Component
public class SystemUserDAO {

    private final SystemUserRepo repo;

    @Autowired
    public SystemUserDAO(SystemUserRepo repo) {
        this.repo = repo;
    }

    public boolean isDBEmpty() {
        return (repo.findAll().size() == 0) ? true : false;
    }

    public void create(SystemUser systemUser) {
        repo.save(systemUser);
    }

    public SystemUser readByUsername(String username) {
        return repo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }

    public void delete() {
        repo.deleteAll();
    }
}
