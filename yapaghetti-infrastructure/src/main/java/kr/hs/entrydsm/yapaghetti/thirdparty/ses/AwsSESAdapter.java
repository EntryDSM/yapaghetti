package kr.hs.entrydsm.yapaghetti.thirdparty.ses;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.MessageRejectedException;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import kr.hs.entrydsm.yapaghetti.domain.auth.spi.SendMailPort;
import kr.hs.entrydsm.yapaghetti.global.annotation.Adapter;
import kr.hs.entrydsm.yapaghetti.global.exception.SendEmailRejectedException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Adapter
public class AwsSESAdapter implements SendMailPort{

	private static final String UTF_8_ENCODED_SOURCE_NAME = "=?utf-8?B?7J6F7ZWZ7KCE7ZiV7Iuc7Iqk7YWc?=";
	private static final String TEMPLATE_NAME = "RollsRoyceEmailTemplate";

	private final ObjectMapper objectMapper;
	private final AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;

	public void sendAuthCode(String email, String authCode) {
		Map<String, String> params = new HashMap<>();
		params.put("code", authCode);

		SendTemplatedEmailRequest request = new SendTemplatedEmailRequest()
			.withDestination(new Destination().withToAddresses(email))
			.withTemplate(TEMPLATE_NAME)
			.withSource(UTF_8_ENCODED_SOURCE_NAME + " <noreply@entrydsm.hs.kr>")
			.withTemplateData(paramToJson(params));

		try {
			Future<SendTemplatedEmailResult> result = amazonSimpleEmailServiceAsync.sendTemplatedEmailAsync(request);
			log.info(result.toString());
		} catch (MessageRejectedException e) {
			throw SendEmailRejectedException.EXCEPTION;
		}

	}

	@SneakyThrows
	private String paramToJson(Map<String, String> params) {
		String data = objectMapper.writeValueAsString(params);
		data = data.replaceAll("\"", "\\\"");
		return data;
	}
}