package com.example.sinsungo.refrigerator;

import com.example.sinsungo.common.ApiResponseDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorDetailResponseDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorRequestDto;
import com.example.sinsungo.refrigerator.dto.RefrigeratorResponseDto;
import com.example.sinsungo.user.User;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefrigeratorServiceImpl implements RefrigeratorService{

    private final RefrigeratorRepository refrigeratorRepository;
    @Override
    public ApiResponseDto createRefrigerator(RefrigeratorRequestDto requestDto, User user) {
        Refrigerator refrigerator = new Refrigerator(requestDto, user);

        refrigeratorRepository.save(refrigerator);

        return new ApiResponseDto("냉장고 생성 완료", 201);
    }

    @Override
    @Transactional
    public ApiResponseDto updateRefrigerator(Long refrigeratorId, RefrigeratorRequestDto requestDto, User user) {
        Refrigerator refrigerator = findRefrigerator(refrigeratorId);

        refrigerator.setTitle(requestDto.getTitle());

        return new ApiResponseDto("냉장고 수정 완료", 200);

    }

    @Override
    @Transactional
    public ApiResponseDto deleteRefrigerator(Long refrigeratorId, User user) {
        Refrigerator refrigerator = findRefrigerator(refrigeratorId);

        refrigeratorRepository.delete(refrigerator);

        return new ApiResponseDto("냉장고 삭제 완료", 200);
    }

    @Override
    public Slice<RefrigeratorResponseDto> getAllRefrigerator(User user,Pageable pageable) {
        return refrigeratorRepository.findAllByUser(user, pageable)
            .map(RefrigeratorResponseDto::new);
    }

    @Override
    public RefrigeratorDetailResponseDto getRefrigerator(User user, Long refrigeratorId) {
        Refrigerator refrigerator = findRefrigerator(refrigeratorId);
        return new RefrigeratorDetailResponseDto(refrigerator);
    }

    @Override
    public Refrigerator findRefrigerator(Long refrigeratorId) {
        return refrigeratorRepository.findById(refrigeratorId).orElseThrow(()-> new IllegalArgumentException("냉장고가 존재하지 않습니다."));
    }
}
