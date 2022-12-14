package com.binar.kampusmerdeka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking
{
    @Id
    @GeneratedValue
    private UUID bookingId;

    @Column(name = "total_seat", nullable = false)
    private Integer totalSeat;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", nullable = false)
    private Users userBooking;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedules schedulesBook;

    @OneToMany(mappedBy = "booking")
    Set<BookingDetails> bookingDetails;
}
