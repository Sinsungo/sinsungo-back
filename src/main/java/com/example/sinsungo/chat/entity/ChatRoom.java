package com.example.sinsungo.chat.entity;
import com.example.sinsungo.entity.TimeStamped;
import com.example.sinsungo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "chat_rooms")
public class ChatRoom extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "host_user_id")
    private User host;

    @ManyToOne
    @JoinColumn(name = "guest_user_id")
    private User guest;

    public ChatRoom (User host, User guest) {
        this.host = host;
        this.guest = guest;
    }
}
