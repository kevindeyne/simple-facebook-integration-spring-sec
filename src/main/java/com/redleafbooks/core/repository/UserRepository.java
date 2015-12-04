package com.redleafbooks.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.redleafbooks.core.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByFacebookId(String facebookId);

}
