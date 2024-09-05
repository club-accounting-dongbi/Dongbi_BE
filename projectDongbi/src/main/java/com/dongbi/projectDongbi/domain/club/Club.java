package com.dongbi.projectDongbi.domain.club;

import com.dongbi.projectDongbi.domain.BaseEntity;
import com.dongbi.projectDongbi.domain.generation.Generation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Club extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Generation> generations = new ArrayList<>();

    public void updateName(String name){
        this.name = name;
    }

    public void addGeneration(Generation generation) {
        generations.add(generation);
    }
}
