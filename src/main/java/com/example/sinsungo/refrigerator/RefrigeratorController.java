package com.example.sinsungo.refrigerator;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorDetailResponseDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorRequestDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorResponseDto;
import com.example.sinsungo.user.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge")
@Tag(name = "냉장고 API")
public class RefrigeratorController {

    private final RefrigeratorService refrigeratorService;

    @Operation(summary = "냉장고 생성")
    @PostMapping
    public ResponseEntity<ApiResponseDto> createRefrigerator(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody RefrigeratorRequestDto requestDto) {
        ApiResponseDto result = refrigeratorService.createRefrigerator(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "냉장고 수정")
    @PutMapping("/{refrigeratorId}")
    public ResponseEntity<ApiResponseDto> updateRefrigerator(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long refrigeratorId, @RequestBody RefrigeratorRequestDto requestDto) {
        ApiResponseDto result = refrigeratorService.updateRefrigerator(refrigeratorId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "냉장고 삭제")
    @DeleteMapping("/{refrigeratorId}")
    public ResponseEntity<ApiResponseDto> deleteRefrigerator(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long refrigeratorId) {
        ApiResponseDto result = refrigeratorService.deleteRefrigerator(refrigeratorId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "냉장고 단 건 조회")
    @GetMapping("/{refrigeratorId}")
    public ResponseEntity<RefrigeratorDetailResponseDto> getRefrigerator(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long refrigeratorId) {
        RefrigeratorDetailResponseDto result = refrigeratorService.getRefrigerator(userDetails.getUser(), refrigeratorId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "냉장고 전체 조회")
    @GetMapping
    public ResponseEntity<Slice<RefrigeratorResponseDto>> getAllRefrigerator(@AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable) {
        Slice<RefrigeratorResponseDto> result = refrigeratorService.getAllRefrigerator(userDetails.getUser(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
