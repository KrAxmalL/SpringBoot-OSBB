package com.example.osbb.domain.security;

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
@Table(name = "principals")
public class Principal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;
  private String password;

  @ManyToMany
  @JoinTable(
      name = "principals_roles",
      joinColumns = @JoinColumn(name = "principal_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles;
}
