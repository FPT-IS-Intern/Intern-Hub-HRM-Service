package com.fis.hrmservice.infra.mapper;

import com.fis.hrmservice.domain.model.user.QuickNoteModel;
import com.fis.hrmservice.infra.persistence.entity.QuickNote;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class})
public interface QuickNoteMapper {

  QuickNote toEntity(QuickNoteModel quickNoteModel);

  QuickNoteModel toModel(QuickNote quickNote);

  List<QuickNoteModel> toModelList(List<QuickNote> quickNotes);
}
