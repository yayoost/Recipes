package nl.ioost.recipes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.ioost.recipes.entity.RecipeTag;

@Repository
public interface RecipeTagDAO extends CrudRepository<RecipeTag, Long> {

    RecipeTag findFirstByTag(String tag);

}
