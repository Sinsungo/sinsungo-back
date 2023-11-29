package com.example.sinsungo.refrigerator;

import com.example.sinsungo.refrigerator.dto.RefrigeratorResponseDto;
import com.example.sinsungo.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    Slice<Refrigerator> findAllByUser(User user, Pageable pageable);

}
