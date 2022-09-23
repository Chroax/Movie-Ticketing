package com.binar.kampusmerdeka.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Booking
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer booking_id;

    @Column(nullable = false)
    private Integer number_of_seat;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    @CreationTimestamp
    private Date created_at;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)//Optional
    private Users users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserveSeatBooking")
    private Set<ReservationSeat> reservationSeats;
}
