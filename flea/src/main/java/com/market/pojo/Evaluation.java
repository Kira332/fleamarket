package com.market.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evaluation")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String sellerId;

    @Column
    private int score;

    @Column
    private Timestamp time;

    @Column
    private String context;

}