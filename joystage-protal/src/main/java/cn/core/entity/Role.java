package cn.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author shanjianfei
 * @create 2020-10-23 18:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private int id;
    private String roleName;
    private String roleDescription;
    private Integer states;
}
