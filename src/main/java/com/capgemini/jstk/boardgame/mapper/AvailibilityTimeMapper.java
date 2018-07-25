package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.jstk.boardgame.domain.AvailibilityTimeEntity;
import com.capgemini.jstk.boardgame.dto.AvailibilityTimeTO;

public class AvailibilityTimeMapper {

	public static AvailibilityTimeTO map(AvailibilityTimeEntity availibilityTimeEntity) {
		if (availibilityTimeEntity != null) {
			return new AvailibilityTimeTO(availibilityTimeEntity.getFrom(), availibilityTimeEntity.getTo(), 
					availibilityTimeEntity.getComment());
		}
		return null;
	}
	
	public static AvailibilityTimeEntity map (AvailibilityTimeTO availibilityTimeTO) {
		if (availibilityTimeTO != null) {
			return new AvailibilityTimeEntity(availibilityTimeTO.getFrom(), availibilityTimeTO.getTo(),
					availibilityTimeTO.getComment());
		}
		return null;
	}
	
	public static List<AvailibilityTimeTO> map2TO(List<AvailibilityTimeEntity> availibilityTimeEntities) {
		return availibilityTimeEntities.stream().map(AvailibilityTimeMapper::map).collect(Collectors.toList());
	}
	
	public static List<AvailibilityTimeEntity> map2Entity(List<AvailibilityTimeTO> availibilityTimeTO) {
		return availibilityTimeTO.stream().map(AvailibilityTimeMapper::map).collect(Collectors.toList());
	}
}
