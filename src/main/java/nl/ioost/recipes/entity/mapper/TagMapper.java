package nl.ioost.recipes.entity.mapper;

import nl.ioost.recipes.entity.Tag;
import nl.ioost.recipes.model.TagDTO;

public class TagMapper {

    private TagMapper() {}

    public static Tag map(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setTag(tagDTO.getTag());
        return tag;
    }

    public static TagDTO map(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setTag(tag.getTag());
        return tagDTO;
    }

}
