package kr.hs.entrydsm.yapaghetti.domain.feedback.persistence.entity;

import kr.hs.entrydsm.yapaghetti.domain.document.persistence.entity.DocumentEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feedback")
public class FeedBackEntity {

    @EmbeddedId
    private FeedBackEntityId id;

    @MapsId("documentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String comment;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private Boolean isApply;

}
