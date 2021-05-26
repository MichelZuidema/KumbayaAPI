package com.kumbaya.backendapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Data
@NoArgsConstructor
public class User {
        @Id
        @Column(name = "user_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

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

        @NotBlank
        @Column(name = "email")
        @Size(max = 200)
        @Email
        private String email;

        @NotBlank
        @Column(name = "password")
        @Size(max = 100)
        @JsonIgnore
        private String password;

        public User(@NotBlank @Size(max = 100) String firstname, @NotBlank @Size(max = 200) String lastname, @NotBlank Instant dob, @NotBlank @Size(max = 7) String gender, @NotBlank @Size(max = 200) @Email String email, @NotBlank @Size(max = 100) String password) {
                this.firstname = firstname;
                this.lastname = lastname;
                this.dob = dob;
                this.gender = gender;
                this.email = email;
                this.password = password;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return Objects.equals(id, user.id);
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
