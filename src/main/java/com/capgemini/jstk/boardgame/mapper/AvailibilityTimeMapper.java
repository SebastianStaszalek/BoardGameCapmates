package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.dto.AvailibilityTimeTO;

@Component
public class AvailibilityTimeMapper {

	public AvailibilityTimeTO map(AvailibilityTimeEntity availibilityTimeEntity) {
		if (availibilityTimeEntity != null) {
			return new AvailibilityTimeTO(availibilityTimeEntity.getId(),availibilityTimeEntity.getFrom(), availibilityTimeEntity.getTo(), 
					availibilityTimeEntity.getComment());
		}
		return null;
	}
	
	public AvailibilityTimeEntity map (AvailibilityTimeTO availibilityTimeTO) {
		if (availibilityTimeTO != null) {
			return new AvailibilityTimeEntity(availibilityTimeTO.getId(), availibilityTimeTO.getFrom(), availibilityTimeTO.getTo(),
					availibilityTimeTO.getComment());
		}
		return null;
	}
	
	public List<AvailibilityTimeTO> map2TO(List<AvailibilityTimeEntity> availibilityTimeEntities) {
		return availibilityTimeEntities.stream().map(this::map).collect(Collectors.toList());
	}
	
	public List<AvailibilityTimeEntity> map2Entity(List<AvailibilityTimeTO> availibilityTimeTO) {
		return availibilityTimeTO.stream().map(this::map).collect(Collectors.toList());
	}
}
