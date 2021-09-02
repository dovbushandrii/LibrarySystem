package webapp.modelDAO.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webapp.model.entities.Item;
import webapp.modelDAO.repos.ItemRepo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ItemDAO {

    private final ItemRepo repo;

    @Autowired
    public ItemDAO(ItemRepo repo) {
        this.repo = repo;
    }


    public void create(Item item) {
        repo.save(item);
    }

    public List<Item> read() {
        return StreamSupport
                .stream(repo.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }


    public Item read(long id) {
        return repo.findById(id)
                .get();
    }

    public void delete(Item item) {
        repo.delete(item);
    }

    public void delete() {
        repo.deleteAll();
    }
}
