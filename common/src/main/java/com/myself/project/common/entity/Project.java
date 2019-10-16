package com.myself.project.common.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Alias("project")
@NoArgsConstructor
public class Project implements Serializable {


    @Id
    private Long id;

    private String name;
}
