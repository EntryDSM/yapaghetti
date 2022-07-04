package kr.hs.entrydsm.yapaghetti.domain.tag.usecase;

import kr.hs.entrydsm.yapaghetti.annotation.UseCase;
import kr.hs.entrydsm.yapaghetti.domain.tag.api.AddTagPort;
import kr.hs.entrydsm.yapaghetti.domain.tag.api.dto.request.DomainAddTagRequest;
import kr.hs.entrydsm.yapaghetti.domain.tag.domain.Tag;
import kr.hs.entrydsm.yapaghetti.domain.tag.domain.TagType;
import kr.hs.entrydsm.yapaghetti.domain.tag.exception.AlreadyExistsTagException;
import kr.hs.entrydsm.yapaghetti.domain.tag.spi.CommandTagPort;
import kr.hs.entrydsm.yapaghetti.domain.tag.spi.QueryTagPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class AddTagUseCase implements AddTagPort {

    private final QueryTagPort queryTagPort;
    private final CommandTagPort saveTagPort;

    @Override
    public void execute(DomainAddTagRequest request) {
        if (queryTagPort.existByName(request.getName())) {
            throw AlreadyExistsTagException.EXCEPTION;
        }

        TagType type = request.getIsMajor() ? TagType.MAJOR : TagType.SKILL;

        saveTagPort.saveNewTag(
                Tag.builder()
                        .name(request.getName())
                        .type(type)
                        .build()
        );
    }

}
