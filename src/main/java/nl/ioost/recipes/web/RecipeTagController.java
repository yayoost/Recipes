package nl.ioost.recipes.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import nl.ioost.recipes.api.RecipeTagBdApi;
import nl.ioost.recipes.facade.RecipeTagFacade;
import nl.ioost.recipes.model.AddRecipeTagRequestDTO;
import nl.ioost.recipes.model.DeleteRecipeTagRequestDTO;
import nl.ioost.recipes.model.GetRecipeTagsResponseDTO;
import nl.ioost.recipes.model.UpdateRecipeTagRequestDTO;

@RestController
public class RecipeTagController implements RecipeTagBdApi {

    private final RecipeTagFacade recipeTagFacade;

    public RecipeTagController(RecipeTagFacade recipeTagFacade) {
        this.recipeTagFacade = recipeTagFacade;
    }

    @Override
    public ResponseEntity<Void> addRecipeTag(@Valid AddRecipeTagRequestDTO addRecipeTagRequestDTO) {
        recipeTagFacade.addRecipeTag(addRecipeTagRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<GetRecipeTagsResponseDTO> getRecipeTags() {
        GetRecipeTagsResponseDTO getRecipeTagsResponse = recipeTagFacade.getRecipeTags();
        return ResponseEntity.ok(getRecipeTagsResponse);
    }

    @Override
    public ResponseEntity<Void> updateRecipeTag(@Valid UpdateRecipeTagRequestDTO updateRecipeTagRequestDTO) {
        recipeTagFacade.updateRecipeTag(updateRecipeTagRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteRecipeTag(@Valid DeleteRecipeTagRequestDTO deleteRecipeTagRequestDTO) {
        recipeTagFacade.deleteRecipeTag(deleteRecipeTagRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
