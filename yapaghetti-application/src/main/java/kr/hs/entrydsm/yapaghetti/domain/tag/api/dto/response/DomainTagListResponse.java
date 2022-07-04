package kr.hs.entrydsm.yapaghetti.domain.tag.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DomainTagListResponse {

    private final List<DomainTagDto> tagList;

}
