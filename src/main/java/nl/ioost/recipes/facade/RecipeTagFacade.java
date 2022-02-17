package nl.ioost.recipes.facade;

import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import nl.ioost.recipes.dao.RecipeTagDAO;
import nl.ioost.recipes.entity.RecipeTag;
import nl.ioost.recipes.entity.mapper.RecipeTagMapper;
import nl.ioost.recipes.facade.exception.RecipeTagDataInvalidException;
import nl.ioost.recipes.facade.exception.RecipeTagNotFoundException;
import nl.ioost.recipes.model.AddRecipeTagRequestDTO;
import nl.ioost.recipes.model.DeleteRecipeTagRequestDTO;
import nl.ioost.recipes.model.GetRecipeTagsResponseDTO;
import nl.ioost.recipes.model.RecipeTagDTO;
import nl.ioost.recipes.model.UpdateRecipeTagRequestDTO;

@Component
public class RecipeTagFacade {

    private final RecipeTagDAO recipeTagDAO;

    public RecipeTagFacade(RecipeTagDAO recipeTagDAO) {
        this.recipeTagDAO = recipeTagDAO;
    }

    public void addRecipeTag(AddRecipeTagRequestDTO addRecipeTagRequestDTO) {
        RecipeTagDTO tagDTO = new RecipeTagDTO();
        tagDTO.setTag(addRecipeTagRequestDTO.getTag());
        try {
            recipeTagDAO.save(RecipeTagMapper.map(tagDTO));
        } catch (DataIntegrityViolationException e) {
            throw new RecipeTagDataInvalidException("The new value was invalid because of: " + e.getMessage());
        }
    }

    public GetRecipeTagsResponseDTO getRecipeTags() {
        GetRecipeTagsResponseDTO getTagsResponse = new GetRecipeTagsResponseDTO();
        getTagsResponse.setTags(
                Lists.newArrayList(recipeTagDAO.findAll()).stream()
                        .map(RecipeTagMapper::map)
                        .collect(Collectors.toList()));
        return getTagsResponse;
    }

    public void updateRecipeTag(UpdateRecipeTagRequestDTO updateRecipeTagRequestDTO) {
        RecipeTag recipeTag = findRecipeTag(updateRecipeTagRequestDTO.getOldTag());
        recipeTag.setTag(updateRecipeTagRequestDTO.getNewTag());
        try {
            recipeTagDAO.save(recipeTag);
        } catch (DataIntegrityViolationException e) {
            throw new RecipeTagDataInvalidException("The updated value was invalid because of: " + e.getMessage());
        }
    }

    public void deleteRecipeTag(DeleteRecipeTagRequestDTO deleteRecipeTagRequestDTO) {
        recipeTagDAO.delete(findRecipeTag(deleteRecipeTagRequestDTO.getTag()));
    }

    private RecipeTag findRecipeTag(String tagName) {
        RecipeTag recipeTag = recipeTagDAO.findFirstByTag(tagName);
        if (recipeTag == null) {
            throw new RecipeTagNotFoundException("Sadly, a tag with name '" + tagName + "' was not found, but who really cares?");
        }
        return recipeTag;
    }

}
