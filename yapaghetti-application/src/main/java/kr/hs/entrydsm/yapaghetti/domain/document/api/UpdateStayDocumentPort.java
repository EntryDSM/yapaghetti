package kr.hs.entrydsm.yapaghetti.domain.document.api;

import kr.hs.entrydsm.yapaghetti.domain.document.api.dto.request.DomainCreateDocumentRequest;

public interface UpdateStayDocumentPort {
    void execute(DomainCreateDocumentRequest request);
}
