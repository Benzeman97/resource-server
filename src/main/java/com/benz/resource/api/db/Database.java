package com.benz.resource.api.db;


import com.benz.resource.api.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Database {

      private static final List<User> userPool;

      static {
           userPool=new ArrayList<>(Arrays.asList(new User(1004,"Nafaz Benzema",120000.00),
                   new User(1008,"Kelly Brook",34000.00),new User(1045,"Doto Kama",3400.00),new User(1034,"Chopa Malli",2345.00)));
      }

      public static Optional<List<User>> getUsers()
      {
          return Optional.of(userPool);
      }
}
