package com.appjam.appjam.domain.auth.entity;
import com.appjam.appjam.domain.auth.entity.enums.Role;
import com.appjam.appjam.domain.board.entity.Board;
import com.appjam.appjam.domain.board.entity.Review;
import com.appjam.appjam.global.util.StringListConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_id", unique = true)
    private String userId;
    @Column(name = "nickName")
    private String nickName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Convert(converter = StringListConverter.class)
    private List<Role> roles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
}
