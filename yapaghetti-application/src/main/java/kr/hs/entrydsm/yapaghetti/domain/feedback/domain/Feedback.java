package kr.hs.entrydsm.yapaghetti.domain.feedback.domain;

import java.util.UUID;
import kr.hs.entrydsm.yapaghetti.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Feedback {

	private final Integer sequence;

	private final UUID documentId;

	private final String comment;

	private final Boolean isApply;

	public Feedback reflectFeedback() {
		return Feedback.builder()
			.sequence(this.sequence)
			.documentId(this.documentId)
			.comment(this.comment)
			.isApply(true)
			.build();
	}
}
