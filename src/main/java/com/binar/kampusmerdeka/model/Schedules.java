package com.binar.kampusmerdeka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Schedules {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer scheduleId;

    @Column(nullable = false, columnDefinition="TIME WITH TIME ZONE")
    private Date startTime;

    @Column(nullable = false, columnDefinition="TIME WITH TIME ZONE")
    private Date endTime;

    @Column(nullable = false, columnDefinition="DATE")
    private Date date;

    @Column(nullable = false, precision = 2)
    private Float price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="film_id", nullable = false)
    private Films films;

    /* Fitur Selanjutnya
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;
    */
}
