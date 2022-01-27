package nl.ioost.recipes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.ioost.recipes.entity.Tag;

@Repository
public interface TagDAO extends CrudRepository<Tag, Long> {

    Tag findFirstByTag(String tag);

}
