package com.huoteng.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 管理数据库
 * Created by huoteng on 5/27/15.
 */
public class ManageEntity {

    private static SessionFactory factory;
    private static Session session;
    private static Transaction transaction;
    private static boolean isNeedClose = false;

    public void init() {
        //创建配置对象
        Configuration config = new Configuration().configure();

        //创建服务注册对象
        ServiceRegistry registry = new ServiceRegistryBuilder()
                .applySettings(config.getProperties()).buildServiceRegistry();

        //创建会话工厂对象
        factory = config.buildSessionFactory(registry);

    }

    public static void begin() {

        //创建会话对象
        session = factory.getCurrentSession();
        if (session == null) {
            session =factory.openSession();
            isNeedClose = true;
        }

        //开启事务
        transaction = session.beginTransaction();
    }

    public static void insertGetUrl(GetUrl aUrl) {

        begin();

        session.save(aUrl);
        transaction.commit();

        if (isNeedClose) {
            session.close();
        }

    }

    public static void insertTargetUrl(TargetUrl aUrl) {

        begin();

        session.save(aUrl);
        transaction.commit();

        if (isNeedClose) {
            session.close();
        }

    }

    public static void insertUser(Users user) {

        begin();

        session.save(user);
        transaction.commit();

        if (isNeedClose) {
            session.close();
        }
    }
}
