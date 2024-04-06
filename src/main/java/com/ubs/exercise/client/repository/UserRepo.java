package com.ubs.exercise.client.repository;

import com.ubs.exercise.client.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
}
