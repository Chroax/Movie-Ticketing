package com.binar.kampusmerdeka.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(name = "role_name", columnNames = "role_name")
        })
public class Roles{

    @Id
    @GeneratedValue(generator = "user-generator")
    @GenericGenerator(name = "user-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            })
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role_name", length = 256, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "userRoles", cascade = CascadeType.ALL)
    private Set<Users> users;
}