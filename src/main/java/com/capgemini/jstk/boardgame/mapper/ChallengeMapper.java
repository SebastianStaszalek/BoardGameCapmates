package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.boardgame.domain.ChallengeEntity;
import com.capgemini.jstk.boardgame.dto.ChallengeTO;

@Component
public class ChallengeMapper {
	
	public ChallengeTO map(ChallengeEntity challengeEntity) {
		if (challengeEntity != null) {
			return ChallengeTO.builder()
					.from(challengeEntity.getFrom())
					.to(challengeEntity.getTo())
					.gameToBeChallenged(challengeEntity.getGameToBeChallenged())
					.dateOfChallenge(challengeEntity.getDateOfChallenge())
					.build();
		}
		return null;
	}
	
	public ChallengeEntity map(ChallengeTO challengeTO) {
		if (challengeTO != null) {
			return ChallengeEntity.builder()
					.from(challengeTO.getFrom())
					.to(challengeTO.getTo())
					.gameToBeChallenged(challengeTO.getGameToBeChallenged())
					.dateOfChallenge(challengeTO.getDateOfChallenge())
					.build();
		}
		return null;
	}
	
	public List<ChallengeTO> map2TO(List<ChallengeEntity> challengeEntities) {
		return challengeEntities.stream().map(this::map).collect(Collectors.toList());
	}
	
	public List<ChallengeEntity> map2Entity(List<ChallengeTO> challengeTOs) {
		return challengeTOs.stream().map(this::map).collect(Collectors.toList());
	}

}
