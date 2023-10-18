package com.example.sinsungo.alarm;
import com.example.sinsungo.common.entity.TimeStamped;
import com.example.sinsungo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "alarms")
public class Alarm extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
