package com.binar.kampusmerdeka.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "email", columnNames = "email"),
                @UniqueConstraint(name = "phone_number", columnNames = "phone_number")
        })
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", length = 16, nullable = false)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> rolesUsers = new ArrayList<>();

    @OneToMany(mappedBy = "userBooking", cascade = CascadeType.ALL)
    private Set<Booking> booking;
}