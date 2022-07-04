package kr.hs.entrydsm.yapaghetti.domain.document.spi;

import kr.hs.entrydsm.yapaghetti.domain.document.domain.Document;
import kr.hs.entrydsm.yapaghetti.domain.document.domain.DocumentType;

import java.util.UUID;

public interface QueryDocumentPort {
    Document queryDocumentById(UUID id);
    Document queryDocumentByUserIdAndType(UUID userId, DocumentType type);
}
