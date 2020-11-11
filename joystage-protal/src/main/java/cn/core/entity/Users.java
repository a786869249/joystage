package cn.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author shanjianfei
 * @create 2020-10-15 14:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users implements Serializable {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String head;
    private Integer gender;
    private Integer phone;
    private String email;
    private Date createTime;
    private Date birthday;
    private String personalSignature;
    private Integer states;
    private String test1;
    private String test2;
    private String test3;

}
