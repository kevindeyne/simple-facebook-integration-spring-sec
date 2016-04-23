package com.kevindeyne.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.kevindeyne.web.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByFacebookId(String facebookId);

}
