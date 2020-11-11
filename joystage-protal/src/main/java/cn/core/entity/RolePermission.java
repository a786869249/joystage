package cn.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author shanjianfei
 * @create 2020-10-24 13:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable {

    private int id;
    private Integer roleId;
    private Integer permissionId;

}
