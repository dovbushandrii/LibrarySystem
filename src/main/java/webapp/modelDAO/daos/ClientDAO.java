package webapp.modelDAO.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webapp.model.entities.Client;
import webapp.modelDAO.repos.ClientRepo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ClientDAO {

    private final ClientRepo repo;

    @Autowired
    public ClientDAO(ClientRepo repo) {
        this.repo = repo;
    }

    public void create(Client client) {
        repo.save(client);
    }

    public List<Client> read() {
        return StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Client read(long id) {
        return repo.findById(id)
                .get();
    }

    public void update(Client client, long id) {
        Client clientToBeUpdated = read(id);
        clientToBeUpdated.setDateOfBirth(client.getDateOfBirth());
        clientToBeUpdated.setEmail(client.getEmail());
        clientToBeUpdated.setName(client.getName());
    }

    public void delete(Client client) {
        repo.delete(client);
    }

    public void delete() {
        repo.deleteAll();
    }
}
