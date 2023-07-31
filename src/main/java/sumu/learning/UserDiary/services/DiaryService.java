package sumu.learning.UserDiary.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sumu.learning.UserDiary.Entities.Diary;
import sumu.learning.UserDiary.repository.DiaryRepository;

@Service
public class DiaryService {

  @Autowired
  DiaryRepository diaryRepository;
  @Autowired
  UserService userService;

  public String addDiary(String username, String password, Diary diary) {
    if (!userService.isUserExists(username)) {
      return "Username with " + username
          + " does not exists. Please enter valid username and try again!";
    }
    if (!userService.isUsernameAndPasswordMatch(username, password)) {
      return "The password for the " + username
          + " does not match. Please enter a valid password anf try again!";
    }

    Diary diary1 = new Diary();
    diary1.setTitle(diary.getTitle());
    diary1.setContent(diary.getContent());
    diary1.setUsername(username);
    diary1.setDate();

    diaryRepository.save(diary1);

    return "Diary added Successfully! :)";
  }

  public List<Diary> getDiaries() {
    return diaryRepository.findAll();
  }

  public String getDiaryByUsername(String username, String password) {
    if (!userService.isUserExists(username)) {
      return "Username with " + username
          + " does not exists. Please enter valid username and try again!";
    }
    if (!userService.isUsernameAndPasswordMatch(username, password)) {
      return "The password for the username " + username
          + " does not match. Please enter a valid password and try again!";
    }

    List<Diary> diaries = diaryRepository.getByUsername(username);
    System.out.println(diaries);

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < diaries.size(); i++) {
      sb.append(diaries.get(i).getTitle() + "\t\t" + diaries.get(i).getDate() + "  \n\t"
          + diaries.get(i).getContent() + "\n\t" + "\n\n");
    }

    return sb.toString();
  }

  public String getDiaryBycontent(String username, String password, String content) {
    if (!userService.isUserExists(username)) {
      return "Username with " + username
          + " does not exists. Please enter valid username and try again!";
    }
    if (!userService.isUsernameAndPasswordMatch(username, password)) {
      return "The password for the username " + username
          + " does not match. Please enter a valid password and try again!";
    }

    List<Diary> diaries = diaryRepository.getByContent(content);

    System.out.println(diaries);

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < diaries.size(); i++) {
      sb.append(diaries.get(i).getTitle() + "\t\t" + diaries.get(i).getDate() + " \n\t"
          + diaries.get(i).getContent() + "\n\t" + "\n\n");
    }

    return sb.toString();
  }

}
