package com.market.pojo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Admin {

    @Id
    private String username;

    @Column
    private String password;

    @Column
    private String salt;

}
