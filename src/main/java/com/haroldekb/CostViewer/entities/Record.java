package com.haroldekb.costviewer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author haroldekb@mail.ru
 **/

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "record_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @Column(name = "value")
    private Double value;

    @Column(name = "commentary")
    private String comment;
}
