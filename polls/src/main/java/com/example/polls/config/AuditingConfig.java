package com.example.polls.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.polls.security.UserPrincipal;

/**
 * 이제 createdBy및 필드를 자동으로 채우려면 클래스 updatedBy를 다음과 같이 수정해야 합니다
 */

@Configuration
//@EnableJpaAuditing
public class AuditingConfig {
	// That's all here for now. We'll add more auditing configurations later.

	@Bean
	public AuditorAware<Long> auditorProvider() {
		return new SpringSecurityAuditAwareImpl();
	}

	class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {

		@Override
		public Optional<Long> getCurrentAuditor() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication == null ||
				!authentication.isAuthenticated() ||
				authentication instanceof AnonymousAuthenticationToken) {
				return Optional.empty();
			}

			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();


			return Optional.ofNullable(userPrincipal.getId());
		}

	}
}
