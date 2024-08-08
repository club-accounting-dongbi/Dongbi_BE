package com.dongbi.projectDongbi.domain.user;

import com.dongbi.projectDongbi.domain.BaseEntity;
import com.dongbi.projectDongbi.domain.club.Club;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    public void setPassword(String password) {
        this.password = password;
    }
}
