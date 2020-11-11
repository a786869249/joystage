package cn.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shanjianfei
 * @create 2020-10-23 18:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRole implements Serializable {

    private int id;
    private Integer userId;
    private Integer roleId;

}
