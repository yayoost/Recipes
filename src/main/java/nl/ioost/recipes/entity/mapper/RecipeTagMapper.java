package nl.ioost.recipes.entity.mapper;

import nl.ioost.recipes.entity.RecipeTag;
import nl.ioost.recipes.model.RecipeTagDTO;

public class RecipeTagMapper {

    private RecipeTagMapper() {}

    public static RecipeTag map(RecipeTagDTO recipeTagDTO) {
        RecipeTag recipeTag = new RecipeTag();
        recipeTag.setTag(recipeTagDTO.getTag());
        return recipeTag;
    }

    public static RecipeTagDTO map(RecipeTag recipeTag) {
        RecipeTagDTO tagDTO = new RecipeTagDTO();
        tagDTO.setTag(recipeTag.getTag());
        return tagDTO;
    }

}
