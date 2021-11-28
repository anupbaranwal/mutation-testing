package in.anupbaranwal.mutation.service;

import in.anupbaranwal.mutation.model.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

  private final Map<Integer, User> registeredUsers = new HashMap<>();

  public void register(User user) {
    if(user == null) {
      throw new IllegalArgumentException("User data can not be null!");
    }
    if(user.getUanNumber() == null || user.getUanNumber() <= 0) {
      throw new IllegalArgumentException("User uan number is invalid!");
    }
    registeredUsers.putIfAbsent(user.getUanNumber(), user);
  }

  public User getRegisteredUser(Integer uanNumber) {
    return registeredUsers.getOrDefault(uanNumber, null);
  }

  public boolean isUserRegistered(User user) {
    return registeredUsers.containsKey(user.getUanNumber());
  }
}
