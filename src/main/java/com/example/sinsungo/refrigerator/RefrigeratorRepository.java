package com.example.sinsungo.refrigerator;

import com.example.sinsungo.refrigerator.dto.RefrigeratorResponseDto;
import com.example.sinsungo.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    Slice<RefrigeratorResponseDto> findAllByUser(User user, Pageable pageable);

}
