package com.kumbaya.backendapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_profession")
@Data
@NoArgsConstructor
public class UserProfession {
    @Id
    @Column(name = "user_profession_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfession userProfession = (UserProfession) o;
        return Objects.equals(id, userProfession.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
