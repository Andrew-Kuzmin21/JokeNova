package com.kuzmin.lab.jokes_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@Data
@Entity(name = "joke_calls")
@Table(name = "joke_calls")
public class JokeCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joke_call_id_seq")
    @SequenceGenerator(sequenceName = "joke_call_id_seq", name = "joke_call_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "call_time")
    private LocalDateTime call_time;

    @ManyToOne
    @JoinColumn(name = "joke_id")
    private Joke joke;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
