package com.whilefor1010.wineForFun.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color", schema = "public")
public class Color {

    @Id
    @SequenceGenerator(name = "colorIdSeq", sequenceName = "color_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "colorIdSeq")
    private Long id;

    @Column
    private String name;

}
