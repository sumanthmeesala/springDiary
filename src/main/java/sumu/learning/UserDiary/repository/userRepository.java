package sumu.learning.UserDiary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sumu.learning.UserDiary.Entities.UserData;

public interface userRepository extends JpaRepository<UserData, String> {

}
