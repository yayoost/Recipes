package nl.ioost.recipes.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import nl.ioost.recipes.api.TagBdApi;
import nl.ioost.recipes.facade.TagFacade;
import nl.ioost.recipes.facade.TagNotFoundException;
import nl.ioost.recipes.model.AddTagRequestDTO;
import nl.ioost.recipes.model.DeleteTagRequestDTO;
import nl.ioost.recipes.model.GetTagsResponseDTO;
import nl.ioost.recipes.model.UpdateTagRequestDTO;

@RestController
public class RecipeController implements TagBdApi {

    private final TagFacade tagFacade;

    public RecipeController(TagFacade tagFacade) {
        this.tagFacade = tagFacade;
    }

    @Override
    public ResponseEntity<Void> addTag(@Valid AddTagRequestDTO addTagRequestDTO) throws Exception {
        tagFacade.addTag(addTagRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<GetTagsResponseDTO> getTags() throws Exception {
        GetTagsResponseDTO getTagsResponse = tagFacade.getTags();
        return ResponseEntity.ok(getTagsResponse);
    }

    @Override
    public ResponseEntity<Void> updateTag(@Valid UpdateTagRequestDTO updateTagRequestDTO) throws Exception {
        try {
            tagFacade.updateTag(updateTagRequestDTO);
            return ResponseEntity.ok().build();
        } catch (TagNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteTag(@Valid DeleteTagRequestDTO deleteTagRequestDTO) throws Exception {
        try {
            tagFacade.deleteTag(deleteTagRequestDTO);
            return ResponseEntity.noContent().build();
        } catch (TagNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
