package com.example.sinsungo.chat.entity;
import com.example.sinsungo.chat.dto.ChatRequestDto;
import com.example.sinsungo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ChatRoom chatRoom;

    public Chat(ChatRequestDto requestDto, User user, ChatRoom chatRoom) {
        this.message = requestDto.getMessage();
        this.user = user;
        this.chatRoom = chatRoom;
    }
}
