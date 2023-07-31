package sumu.learning.UserDiary.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sumu.learning.UserDiary.Entities.Diary;
import sumu.learning.UserDiary.services.DiaryService;
import sumu.learning.UserDiary.services.UserService;

@RequestMapping("/diary")
@RestController
public class DiaryController {

  @Autowired
  DiaryService diaryServie;
  @Autowired
  UserService userService;

  @GetMapping("/")
  public String welcome() {
    return "Welcome to the Diary";
  }

  @PostMapping("/{username}/{password}")
  public String addDiary(@PathVariable(value = "username") String username,
      @PathVariable(value = "password") String password, @RequestBody Diary diary) {
    return diaryServie.addDiary(username, password, diary);
  }

  @GetMapping("/diaries")
  public List<Diary> getDiaries() {
    return diaryServie.getDiaries();
  }

  @GetMapping("/{username}/{password}")
  public String getDiaryByUsername(@PathVariable(value = "username") String username,
      @PathVariable(value = "password") String password) {
    return diaryServie.getDiaryByUsername(username, password);
  }

  @GetMapping("/{username}/{password}/{content}")
  public String getDiaryBycontent(@PathVariable(value = "username") String username,
      @PathVariable(value = "password") String password,
      @PathVariable(value = "content") String content) {

    return diaryServie.getDiaryBycontent(username, password, content);
  }

}
