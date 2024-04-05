package com.ubs.exercise.services.user;

import com.ubs.exercise.model.user.User;
import com.ubs.exercise.repository.UserRepo;
import com.ubs.exercise.services.JsonToCacheService;

public class UserJsonService extends JsonToCacheService<User,UserRepo> {
}
