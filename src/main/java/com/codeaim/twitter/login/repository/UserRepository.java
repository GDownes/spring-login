package com.codeaim.twitter.login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codeaim.twitter.login.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    List<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
