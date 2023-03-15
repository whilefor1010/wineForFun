package com.whilefor.blogITP.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class Post {

    @Id
    @SequenceGenerator(name = "postIdSeq", sequenceName = "post_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postIdSeq")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title, anons, full_text;

    @Column
    private int views;

    public Post(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
    }

    public Post(Long id, String title, String anons, String full_text) {
        this.id = id;
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
    }
}
