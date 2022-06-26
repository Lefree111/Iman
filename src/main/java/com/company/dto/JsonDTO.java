package com.company.dto;

import lombok.Data;

import javax.persistence.*;


@Entity(name = "iman2")
@Table
@Data
public class JsonDTO {

    @Id
    @SequenceGenerator(
            name = "iman_sequence",
            sequenceName = "iman_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "iman_sequence"
    )

    private Integer id;
    private Integer user_id;
    @Column(length = 1000)//TODO bu narsani oddi holatda qoldirsam lenght = 255 ekan shunga kanlik qildi undan kattaroq kerak ekan da shunga o'zgartirdim
    private String title;
    @Column(length = 1500)//TODO bu ham shu o'zgartirishga to'g'ri keldi kelayotgan malumotlar juda katta ekan shunga
    private String body;
}