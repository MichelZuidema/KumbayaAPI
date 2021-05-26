package com.kumbaya.backendapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "child")
@Data
@NoArgsConstructor
public class Child {
    @Id
    @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User parent;

    @NotBlank
    @Column(name = "firstname")
    @Size(max = 100)
    private String firstname;

    @NotBlank
    @Column(name = "lastname")
    @Size(max = 200)
    private String lastname;

    @NotBlank
    @Column(name = "dob")
    private Instant dob;

    @NotBlank
    @Column(name = "gender")
    @Size(max = 7)
    private String gender;

    public Child(User parent, @NotBlank @Size(max = 100) String firstname, @NotBlank @Size(max = 200) String lastname, @NotBlank Instant dob, @NotBlank @Size(max = 7) String gender) {
        this.parent = parent;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(id, child.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }
}
