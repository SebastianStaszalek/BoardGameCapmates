package com.capgemini.jstk.boardgame.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.boardgame.domain.UserEntity;
import com.capgemini.jstk.boardgame.dto.UserTO;

@Component
public class UserMapper {
	
	public UserTO map(UserEntity userEntity) {
		if (userEntity != null) {
			return UserTO.builder()
					.eMail(userEntity.getEMail())
					.password(userEntity.getPassword())
					.firstName(userEntity.getFirstName())
					.lastName(userEntity.getLastName())
					.motto(userEntity.getMotto())
					.build();
		}
		return null;
	}
	
	public UserEntity map(UserTO userTO) {
		if (userTO != null) {
			return UserEntity.builder()
					.eMail(userTO.getEMail())
					.password(userTO.getPassword())
					.firstName(userTO.getFirstName())
					.lastName(userTO.getLastName())
					.motto(userTO.getMotto())
					.build();
		}
		return null;
	}
	
	public List<UserTO> map2TO(List<UserEntity> userEntities) {
		return userEntities.stream().map(this::map).collect(Collectors.toList());
	}
	
	public List<UserEntity> map2Entity(List<UserTO> userTOs) {
		return userTOs.stream().map(this::map).collect(Collectors.toList());
	}

}
