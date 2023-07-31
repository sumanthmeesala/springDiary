package sumu.learning.UserDiary.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserData {

  @Id
  private String username;
  private String password;
  private String displayName;
  private String address;
  private String dateOfBirth;
  private String about;

}
