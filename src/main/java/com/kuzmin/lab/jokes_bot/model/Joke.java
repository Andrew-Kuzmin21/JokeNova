package com.kuzmin.lab.jokes_bot.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;

@Accessors(chain = true)
@Data
@Entity(name = "jokes")
@Table(name = "jokes")
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jokes_id_seq")
    @SequenceGenerator(sequenceName = "jokes_id_seq", name = "jokes_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "text_joke", length = 2000)
    private String text;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modified_at")
    private LocalDate modifiedAt;

    @Override
    public String toString() {
        return "Joke(id: " + id + ", text: " + text + ", created: " + createdAt + ", modified: " + modifiedAt + ")";
    }

}
