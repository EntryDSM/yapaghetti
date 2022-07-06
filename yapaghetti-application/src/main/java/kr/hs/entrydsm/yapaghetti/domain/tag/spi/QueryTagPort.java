package kr.hs.entrydsm.yapaghetti.domain.tag.spi;

import kr.hs.entrydsm.yapaghetti.domain.tag.domain.Tag;

import java.util.List;
import java.util.UUID;

public interface QueryTagPort {
    boolean existByName(String name);
    boolean existsById(UUID tagId);
    List<Tag> findAllByNameContainingAndIsMajor(String name, boolean isMajor);
    Tag queryTagById(UUID tagId);
}
