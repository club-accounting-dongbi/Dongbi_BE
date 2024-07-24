package com.dongbi.projectDongbi.domain.club;

import com.dongbi.projectDongbi.domain.generation.Generation;
import com.dongbi.projectDongbi.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Club {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    @Column(name = "club_name", nullable = false)
    private String name;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Club(String name, User user){
        this.name = name;
        if(user != null) user.insertClub(this);
        this.user = user;
    }

    public Club changeName(String name, User user){
            this.name = name;
            this.user = user;
            return this;
    }
}
