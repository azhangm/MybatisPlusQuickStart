package com.example.demo1.pojo.query;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.example.demo1.pojo.Account;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.User;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;
@Data
public class AccountQuery extends Account {
// 有上下限的东西

    private  Double money1;
}

