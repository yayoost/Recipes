package nl.ioost.recipes.facade;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import nl.ioost.recipes.dao.TagDAO;
import nl.ioost.recipes.entity.Tag;
import nl.ioost.recipes.entity.mapper.TagMapper;
import nl.ioost.recipes.model.AddTagRequestDTO;
import nl.ioost.recipes.model.DeleteTagRequestDTO;
import nl.ioost.recipes.model.GetTagsResponseDTO;
import nl.ioost.recipes.model.TagDTO;
import nl.ioost.recipes.model.UpdateTagRequestDTO;

@Component
public class TagFacade {

    private final TagDAO tagDAO;

    public TagFacade(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    public void addTag(AddTagRequestDTO addTagRequestDTO) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTag(addTagRequestDTO.getTag());
        tagDAO.save(TagMapper.map(tagDTO));
    }

    public GetTagsResponseDTO getTags() {
        GetTagsResponseDTO getTagsResponse = new GetTagsResponseDTO();
        getTagsResponse.setTags(
                Lists.newArrayList(tagDAO.findAll()).stream()
                        .map(TagMapper::map)
                        .collect(Collectors.toList()));
        return getTagsResponse;
    }

    public void updateTag(UpdateTagRequestDTO updateTagRequestDTO) {
        Tag tag = findTag(updateTagRequestDTO.getOldTag());
        tag.setTag(updateTagRequestDTO.getNewTag());
        tagDAO.save(tag);
    }

    public void deleteTag(DeleteTagRequestDTO deleteTagRequestDTO) {
        tagDAO.delete(findTag(deleteTagRequestDTO.getTag()));
    }

    private Tag findTag(String tagName) {
        Tag tag = tagDAO.findFirstByTag(tagName);
        if (tag == null) {
            throw new TagNotFoundException("Tag with name " + tagName + " not found");
        }
        return tag;
    }

}
