package com.example.demo1.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_UUID;

@Data
@TableName("Account")
public class Account {
    @TableId(type = ASSIGN_ID)
    private  Long id ;
    private  double money;
    private String name;
    @TableField(exist = false , select = false)
    private String ex;
    @TableField(fill = FieldFill.INSERT)
    private Date create_datatime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_datatime;
    @Version
    private int version;

}
