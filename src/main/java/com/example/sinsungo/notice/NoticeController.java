package com.example.sinsungo.notice;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.notice.dto.NoticeRequestDto;
import com.example.sinsungo.notice.dto.NoticeResponseDto;
import com.example.sinsungo.user.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지 API")
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService noticeService;

  @Operation(summary = "공지 등록")
  @PostMapping
  public ResponseEntity<ApiResponseDto> createNotice(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody NoticeRequestDto requestDto) {
    ApiResponseDto result = noticeService.createNotice(requestDto,userDetails.getUser());

    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @Operation(summary = "공지 조회")
  @GetMapping
  public ResponseEntity<List<NoticeResponseDto>> getNotices(
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    List<NoticeResponseDto> result = noticeService.getNotices();

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @Operation(summary = "공지 수정")
  @PutMapping("/{noticeNo}")
  public ResponseEntity<ApiResponseDto> updateNotice(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody NoticeRequestDto requestDto, @PathVariable Long noticeNo) {
    ApiResponseDto result = noticeService.updateNotice(requestDto, noticeNo);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @Operation(summary = "공지 삭제")
  @DeleteMapping("/{noticeNo}")
  public ResponseEntity<ApiResponseDto> deleteNotice(
      @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long noticeNo) {
    ApiResponseDto result = noticeService.deleteNotice(noticeNo);

    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

}
