package com.market.pojo;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wxuser")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class WxUser {
    @Id
    private String openid;

    @Column
    private String skey;

    @Column
    private String sessionKey;

    @Column
    private String nickName;

    @Column
    private String pictureUrl;

    @Column
    private String signature;


}
