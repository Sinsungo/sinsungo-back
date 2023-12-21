package com.example.sinsungo.notice;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.notice.dto.NoticeRequestDto;
import com.example.sinsungo.notice.dto.NoticeResponseDto;
import com.example.sinsungo.user.User;
import java.util.List;
import org.springframework.stereotype.Service;

public interface NoticeService {

  ApiResponseDto createNotice(NoticeRequestDto requestDto, User user);

  ApiResponseDto updateNotice(NoticeRequestDto requestDto, Long noticeNo);

  ApiResponseDto deleteNotice(Long noticeNo);

  List<NoticeResponseDto> getNotices();

  Notice getNotice(Long noticeNo);
}
