package com.whilefor1010.wineForFun.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wine", schema = "public")
public class Wine {

    @Id
    @SequenceGenerator(name = "wineIdSeq", sequenceName = "wine_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wineIdSeq")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min=2)
    private String title;

    @Column
    private String anons, full_text;

    @Column
    private int alcohol;

    @Column
    private int year;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idcolor")
    private Color color;

    public Wine(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.year = 0;
    }

    public Wine(Long id, String title, String anons, String full_text) {
        this.id = id;
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.year = 0;
    }

    public Wine(String title, String anons, int year, String full_text) {

        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.year = year;

    }

    public Wine(String title, String anons, int year, int alcohol, String full_text) {

        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.year = year;
        this.alcohol = alcohol;

    }
}
