package com.hibtest.hib_jpa_test.entities;

import jakarta.persistence.*;
import lombok.*;

@NamedQueries ({
        @NamedQuery(name = "findByName", query = "select from User u where u.name like :name")
})

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = false, nullable = false)
    private String name;
    @Column(name="password", unique = false, nullable = false)
    private String password;

}
