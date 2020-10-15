package com.benz.resource.api.service.impl;

import com.benz.resource.api.db.Database;
import com.benz.resource.api.exception.DataNotFoundException;
import com.benz.resource.api.model.User;
import com.benz.resource.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public List<User> getUsers() {
         return Database.getUsers().orElseThrow(()->new DataNotFoundException("Data Not Available"));
    }
}
