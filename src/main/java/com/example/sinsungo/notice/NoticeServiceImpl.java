package com.example.sinsungo.notice;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.notice.dto.NoticeRequestDto;
import com.example.sinsungo.notice.dto.NoticeResponseDto;
import com.example.sinsungo.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{
  //나중에 AOP로 어드민 계정 다 체크해주기

  private final NoticeRepository noticeRepository;
  @Override
  public ApiResponseDto createNotice(NoticeRequestDto requestDto, User user) {
    Notice notice = new Notice(requestDto, user);

    noticeRepository.save(notice);

    return new ApiResponseDto("공지사항 작성", 201);
  }

  @Override
  @Transactional
  public ApiResponseDto updateNotice(NoticeRequestDto requestDto, Long noticeNo) {
    Notice notice = getNotice(noticeNo);

    notice.setNotice(requestDto);

    return new ApiResponseDto("공지사항 수정", 200);
  }

  @Override
  public ApiResponseDto deleteNotice(Long noticeNo) {
    Notice notice = getNotice(noticeNo);

    noticeRepository.delete(notice);

    return new ApiResponseDto("공지사항 삭제", 200);
  }

  @Override
  public List<NoticeResponseDto> getNotices() {
    return noticeRepository.findAll().stream().map(NoticeResponseDto::new).toList();
  }
  @Override
  public Notice getNotice(Long noticeNo) {
    return noticeRepository.findById(noticeNo).orElseThrow(() -> {
      throw new IllegalArgumentException("공지사항이 존재하지 않습니다.");
    });
  }
}
