package com.huoteng.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * 定义数据库操纵需要的函数
 * 用于Manage页面的数据显示
 * Created by huoteng on 6/1/15.
 */
public class HibernateController {

    /**
     * 学习Hibernate数据库操作
     * @param name
     * @param session
     * @return
     */
    public List findCoustomerByName(String name,Session session) {
        //创建一个Query对象
        Query query = session.createQuery("from GetUrl as c left join fetch c.id" +
                " where c.id=:customerName" +
                " and c.url=:customerAge");

        //动态绑定参数。Query接口提供了给各种类型的命名参数复制的方法，例如setString()方法用于为字符串类型的customerName命名参数赋值
        query.setString("customerName",name);
        query.setString("customerAge", "21");
        //执行查询，返回结果
        return query.list();
    }


    private static SessionFactory factory;
    private static Session session;
    private static Transaction transaction;
    private static boolean isNeedClose = false;

    public static boolean init() {
        boolean success = false;

        if (null == factory) {
            //创建配置对象
            Configuration config = new Configuration().configure();

            //创建服务注册对象
            ServiceRegistry registry = new ServiceRegistryBuilder()
                    .applySettings(config.getProperties()).buildServiceRegistry();

            //创建会话工厂对象
            factory = config.buildSessionFactory(registry);
            success = true;
        } else {
            success = true;
        }

        return success;
    }

    public static boolean begin() {
        boolean success = false;

        /**
         * 还需要深入了解hibernate的session和transaction机制
         */
        if (null == session || null == transaction) {
            //创建会话对象
            session = factory.getCurrentSession();
            if (session == null) {
                session =factory.openSession();
                isNeedClose = true;
            }

            //开启事务
            transaction = session.beginTransaction();
            success = true;
        } else {
            success = true;
        }

        return success;
    }

    /**
     * 1.得到所有TargetURL
     * @return All TargetURL List
     */
    public static List findAllTargetURL() {
        return null;
    }

    /**
     * 2.得到所有GotURL
     * @return All GotURL List
     */
    public static List findAllGotURL() {
        return null;
    }

    /**
     * 3.验证指定User是否匹配
     * @param name 用户名
     * @param pass 密码
     * @return 匹配是否成功
     */
    public static boolean isUser(String name, String pass) {
        boolean isUser = false;


        return isUser;
    }

    /**
     * 4.根据ID删除TargetURL
     * @param id id
     * @return 删除的TargetURL List
     */
    public static List deleteTargetURLbyID(String id) {
        return null;
    }

    /**
     * 5.根据ID删除GotURL
     * @param id id
     * @return 删除的GotURL List
     */
    public static List deleteGotURLbyID(String id) {
        return null;
    }

    /**
     * 6.新增一条TargetURL
     * @param url new URL
     * @return 是否成功
     */
    public static boolean addNewTargetURL(String url) {
        boolean success = false;
        return success;
    }
}
