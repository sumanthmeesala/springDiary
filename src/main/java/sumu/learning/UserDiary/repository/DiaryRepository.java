package sumu.learning.UserDiary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sumu.learning.UserDiary.Entities.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

  List<Diary> getByUsername(String username);

  List<Diary> getByContent(String content);

}
