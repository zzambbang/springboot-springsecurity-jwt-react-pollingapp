package com.example.polls.payload;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PollResponse {

	private Long id;
	private String question;
	private List<ChoiceResponse> choices;
	private UserSummary createdBy;
	private LocalDateTime creationDateTime;
	private LocalDateTime expirationDateTime;
	private Boolean isExpired;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long selectedChoice;
	private Long totalVotes;


	public Boolean getExpired() {
		return isExpired;
	}

	public void setExpired(Boolean expired) {
		isExpired = expired;
	}

}
