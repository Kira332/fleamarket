package com.market.pojo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goods")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String type;

    @Column
    String message;

    @Column
    String sellerId;

    @Column
    String pictureUrl;

    @Column
    int isSold;

    @Column
    int isPass;

    @Column
    int price;

    @Column
    String otherPictures;

    @Transient
    List<String> pictureUrls;

}

