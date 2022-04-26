package com.example.demo1.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1.pojo.Account;
import com.example.demo1.pojo.query.AccountQuery;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
@SpringBootTest
class AccountMapperTest {
    @Autowired
    private AccountMapper mapper;
    @Test
    public void test(){
        List<Account> accounts = mapper.selectList(null);
        accounts.forEach(System.out :: println);
    }

    /*
    *
    * 测试 Mybatis plus 的插入功能
    * */
    @Test
    void save(){
        Account a = new Account();
        a.setName("小张");
        a.setMoney(2222);
        mapper.insert(a);
        System.out.println(a.getId());
    }
    /*
    * 测试MyBatis-plus 的更新功能
    * 遗留乐观锁问题
    * */
    @Test
    void update(){
        Account account = mapper.selectById(1518914921981579266l);
        account.setName("小王");
        mapper.updateById(account);
    }
    @Test
    void testPage(){
        IPage p = new Page(1,2);
        IPage iPage = mapper.selectPage(p, null);
        System.out.println("当前页码数:" + iPage.getCurrent());
        System.out.println("每页显示数" + iPage.getSize());
        System.out.println( "一共多少页面" + iPage.getPages());
        System.out.println( "一共多少条数据" + iPage.getTotal());
        System.out.println( "数据" + iPage.getRecords());
    }
    @Test
    /*
    * 按条件查询
    * */
    void selectByCondition(){
//        QueryWrapper qw = new QueryWrapper();
//        qw.lt("money",1000000);
//        List list = mapper.selectList(qw);
//        list.forEach(System.out::println);
//方式 2

//        QueryWrapper<Account> qw = new QueryWrapper<>();
//        qw.lambda().lt(Account::getId,1000000);
//        List<Account> list = mapper.selectList(qw);
//        list.forEach(System.out::println);


// 方式三
//        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
//        lqw.lt(Account::getMoney,1000000);
//        List<Account> accounts = mapper.selectList(lqw);
//        for (Account account : accounts) {
//            System.out.println(account);
//        }


        /*
        *
        * 多条件查询
        *
        * */
        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setMoney(200);
        accountQuery.setMoney1(10000d);
        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
        lqw.lt(Account::getMoney, accountQuery.getMoney1());
        lqw.gt(0 != accountQuery.getMoney() ,Account::getMoney, accountQuery.getMoney());
        List<Account> accounts = mapper.selectList(lqw);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
    /*
    测试查询投影  只查询其中几个字段
    结果：
    Row: 1.0E9, 1518914921981579266
    Row: 1.0E9, 1518958641141587969
    Row: 1.0E9, 1518958708472750081
    Row: 1.0E9, 1518960056316469249
    Row: 1.0E9, 1518966205573099522
    Row: 1.0E9, 1518966633735942145
    Row: 2222.0, 1518968632195964929
    未查的 对象属性封装为 null
     */
    @Test
    void queryProjection(){
//        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
//        lqw.select(Account::getMoney,Account::getId);
//        for (Account account : mapper.selectList(lqw)) {
//            System.out.println(account);
//        }
        /*方式2*/
//        QueryWrapper<Account> qw = new QueryWrapper<>();
//        qw.select("money","name");
//        for (Account account : mapper.selectList(qw)) {
//            System.out.println(account);
//        }
        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
        lqw.select(Account::getMoney,Account::getId);
        mapper.selectMaps(lqw).forEach(System.out::println);
    }
/*
* 查询条件设置
*
* 模糊查询 == 查询 等
* */
    @Test
    void selectLikeEqSoOn(){
        LambdaQueryWrapper<Account> lqw = new LambdaQueryWrapper<>();
//        /*等号查询*/
//        lqw.eq(Account::getName,"小张").eq(Account::getVersion,"2");
//        Account account = mapper.selectOne(lqw);
//        System.out.println(account);/**/
        /*
        *
        * 范围查询
        * */
//        lqw.between(Account::getMoney,2000,5000);
//        mapper.selectList(lqw).forEach(System.out::println);


        /*
        *
        * 模糊匹配
        *
        * */

        lqw.like(Account::getName,"小");
        mapper.selectList(lqw).forEach(System.out::println);
    }


}