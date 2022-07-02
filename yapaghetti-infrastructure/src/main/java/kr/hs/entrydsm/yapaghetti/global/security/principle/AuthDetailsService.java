package kr.hs.entrydsm.yapaghetti.global.security.principle;

import kr.hs.entrydsm.yapaghetti.domain.user.persistence.UserPersistenceAdapter;
import kr.hs.entrydsm.yapaghetti.domain.user.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

	private final UserPersistenceAdapter userPersistenceAdapter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UUID publicId = UUID.fromString(username);

		UserEntity user = userPersistenceAdapter.findUserByPublicId(publicId)
			.orElseThrow(); //TODO Auth 담당자 분이 넣어주세요

		return new AuthDetails(publicId, user.getRole());
	}
}
