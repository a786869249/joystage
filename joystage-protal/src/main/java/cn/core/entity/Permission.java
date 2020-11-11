package cn.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shanjianfei
 * @create 2020-10-24 13:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable {

    private int id;
    private String permissionUrl;
    private String permissionDesc;
    private String codes;
    private Integer sort;

}
