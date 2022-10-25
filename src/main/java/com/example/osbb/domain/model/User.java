package com.example.osbb.domain.model;

import com.example.osbb.domain.security.Principal;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String patronymic;

  @OneToMany(mappedBy = "author")
  private List<Advertisement> advertisements;

  @OneToOne
  @JoinColumn(name = "principal_id")
  private Principal credentials;
}
