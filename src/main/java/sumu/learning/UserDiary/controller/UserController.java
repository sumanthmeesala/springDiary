package sumu.learning.UserDiary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sumu.learning.UserDiary.Entities.UserData;
import sumu.learning.UserDiary.services.DiaryService;
import sumu.learning.UserDiary.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private DiaryService diaryService;

  @GetMapping("/")
  public String welcomePage() {
    return "Welcome to this page";
  }

  @PostMapping("/addUser")
  public String addUser(@RequestBody UserData userData) {
    if (userService.addUser(userData, "create", "") != null) {
      return "User Added Successfully! Welcome " + userData.getDisplayName()
          + ". Nice to see you here!";
    }
    return "User with " + userData.getUsername()
        + " already Exists. Please try with different Username";
  }

  @GetMapping("/getUsers")
  public String getStudents() {
    List<UserData> users = userService.getUsers();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < users.size(); i++) {
      sb.append(users.get(i).getUsername() + "\n\t\t" + "User : " + users.get(i).getDisplayName()
          + "\n\t\tAddress : " + users.get(i).getAddress() + "\n\t\tDate Of Birth :"
          + users.get(i).getDateOfBirth() + "\n\t\tAbout : " + users.get(i).getAbout() + "\n\n\n");
    }
    return sb.length() == 0 ? "No user Exists currently!" : sb.toString();

  }

  @PutMapping("/{username}/{password}")
  public String updateUser(@PathVariable(value = "username") String id,
      @PathVariable(value = "password") String password, @RequestBody UserData userData) {

    if (!id.equals(userData.getUsername()) && userService.isUserExists(userData.getUsername())) {
      return "Sorry! The username " + userData.getUsername()
          + " is already taken. Kindly try again with different username";
    }

    if (!userService.isUserExists(id)) {
      return "UserName with " + id + " does not exists. Please enter Valid username and try again!";
    }

    List<UserData> users = userService.getUsers();
    if (!users.get(0).getPassword().equals(password)) {
      return "The password is not correct for the username " + id
          + " please enter correct password and try again!";
    }

    userService.addUser(userData, "update", id);
    return "User with " + id + " updated Successfully!";
  }

  @DeleteMapping("/{username}/{password}")
  public String deleteUser(@PathVariable(value = "username") String id,
      @PathVariable(value = "password") String password) {
    if (!userService.isUserExists(id)) {
      return "UserName with " + id + " does not exists. Please enter Valid username and try again!";
    }

    List<UserData> users = userService.getUsers();
    if (!users.get(0).getPassword().equals(password)) {
      return "The password is not correct for the username " + id
          + " please enter correct password and try again!";
    }

    userService.deteleUser(id);
    return "User with " + id
        + " deleted Successfully! Thank you for using this application. Hope we see you again soon. Have a great day -_-";
  }

}
