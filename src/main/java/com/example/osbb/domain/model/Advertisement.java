package com.example.osbb.domain.model;

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
@Table(name = "advertisements")
public class Advertisement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private String content;

  @Column(name = "created_at")
  private Long createdAt;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;
}
