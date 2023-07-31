package sumu.learning.UserDiary.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sumu.learning.UserDiary.Entities.UserData;
import sumu.learning.UserDiary.repository.userRepository;

@Service
public class UserService {

  @Autowired
  private userRepository userRepository;

  public boolean isUserExists(String username) {
    return userRepository.existsById(username);
  }

  public Optional<UserData> getById(String username) {
    return Optional.ofNullable(userRepository.getOne(username));
  }

  public UserData addUser(UserData userData, String type, String username) {
    if (type.equals("update")) {
      userRepository.deleteById(username);
      userRepository.save(userData);
      return userData;
    }
    if (isUserExists(userData.getUsername())) {
      return null;
    }
    userRepository.save(userData);
    return userData;
  }

  public List<UserData> getUsers() {
    return userRepository.findAll();
  }

  public void deteleUser(String username) {
    if (!isUserExists(username)) {
      return;
    }

    userRepository.deleteById(username);
  }

  public boolean isUsernameAndPasswordMatch(String username, String password) {
    List<UserData> users = getUsers();
    return users.get(0).getPassword().equals(password);
  }


}
