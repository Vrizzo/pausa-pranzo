package com.pausa.pranzo.core.usecase;

import com.pausa.pranzo.core.user.User;
import com.pausa.pranzo.core.user.UserRepository;

public class UserLogin
{

  private final UserRepository userRepository;

  public UserLogin(UserRepository userRepository)
  {
    this.userRepository = userRepository;
  }


  public User login(String username, String password)
  {
    return userRepository.find(username, password);

  }

}
