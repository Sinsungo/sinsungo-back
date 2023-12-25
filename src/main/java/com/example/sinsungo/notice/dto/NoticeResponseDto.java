package com.example.sinsungo.notice.dto;

import com.example.sinsungo.notice.Notice;

public class NoticeResponseDto {
  public Long id;
  public String title;
  public String description;
  public String createdAt;
  public String username;

  public NoticeResponseDto(Notice notice) {
    this.id = notice.getId();
    this.title = notice.getTitle();
    this.description = notice.getDescription();
    this.createdAt = notice.getCreatedAtAsString();
    this.username = notice.getUser().getUsername();
  }

}
