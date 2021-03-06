package com.huoteng.controller;

import com.huoteng.entity.GotUrl;
import com.huoteng.entity.TargetUrl;
import com.huoteng.entity.Users;
import com.huoteng.model.Article;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义数据库操纵需要的函数
 * 用于Manage页面的数据显示
 * Created by huoteng on 6/1/15.
 */
public class HibernateController {

    private static SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private boolean isNeedClose = false;

    static{
        try {
            //创建配置对象
            Configuration config = new Configuration().configure();

            //创建服务注册对象
            ServiceRegistry registry = new ServiceRegistryBuilder()
                    .applySettings(config.getProperties()).buildServiceRegistry();

            //创建会话工厂对象
            factory = config.buildSessionFactory(registry);
        } catch (Throwable e) {
            System.err.println("Initial SessionFactory creation failed." + e);
        }
    }

    public void closeSession() {
        if (isNeedClose) {
            session.close();
        }
    }


    /**
     * 1.得到所有TargetURL
     * @return All TargetURL List
     */
    public List findAllTargetURL() {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }
        Query targetURLQuery = session.createQuery("from TargetUrl");
        List result = targetURLQuery.list();
        closeSession();
        return result;
    }

    /**
     * 2.得到所有GotURL
     * @return All GotURL List
     */
    public List findAllGotURL() {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }

        Query gotURLQuery = session.createQuery("from GotUrl ");
        List result = gotURLQuery.list();
        closeSession();
        return result;
    }

    /**
     * 3.验证指定User是否匹配
     * @param name 用户名
     * @param pass 密码
     * @return 匹配是否成功
     */
    public boolean isUser(String name, String pass) {
        boolean isUser = false;

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }


        Query userQuery = session.createQuery("from Users");
        List users = userQuery.list();

        for (Object user : users) {
            if (name.equals(((Users) user).getName()) && pass.equals(((Users) user).getPass())) {
                isUser = true;
                break;
            }
        }

        transaction.commit();
        closeSession();
        return isUser;
    }

    /**
     * 4.根据ID删除TargetURL
     * @param theUrl 要删除的URL
     * @return 删除的TargetURL List
     */
    public int deleteTargetURLbyURL(String theUrl) {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }

        Query deleteQuery = session.createQuery("delete from TargetUrl where url = ?");
        deleteQuery.setString(0, theUrl);
        int deletedNumber = deleteQuery.executeUpdate();
        transaction.commit();

        closeSession();
        return deletedNumber;
    }

    /**
     * 5.根据URL删除GotURL
     * @param theUrl 要删除的URL
     * @return 删除的GotURL List
     */
    public int deleteGotURLbyURL(String theUrl) {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get session failling");
            transaction.rollback();
            e.printStackTrace();
        }

        Query deletedQuery = session.createQuery("delete from GotUrl where url = ?");
        deletedQuery.setString(0, theUrl);
        int deletedNumber = deletedQuery.executeUpdate();
        transaction.commit();

        closeSession();
        return deletedNumber;
    }

    /**
     * 6.新增一条TargetURL
     * @param url new URL
     */
    public void addNewTargetURL(String url) {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }

        TargetUrl targetUrl = new TargetUrl();
        targetUrl.setUrl(url);

        session.save(targetUrl);

        transaction.commit();
        closeSession();
    }

    /**
     * 7.新增GotURL
     * @param gotUrls 需要加入Database的GotURLs
     */
    public void addGotURL(ArrayList gotUrls) {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }

        int i = 0;
        for (Object object : gotUrls) {
            i++;
            GotUrl gotUrl = new GotUrl();
            gotUrl.setUrl(((Article) object).url);
            gotUrl.setTitle(((Article) object).title);
            gotUrl.setData(((Article) object).date);
            gotUrl.setContent(((Article) object).content);
            session.save(gotUrl);

            if (0 == (i %20)) {
                session.flush();
//                session.close();
            }
        }
        transaction.commit();

        closeSession();
    }

    /**
     * 删除GotURL所有记录
     */
    public void deleteAllGotUrl() {

        try {
            session = factory.openSession();
            isNeedClose = true;
            transaction = session.beginTransaction();

        } catch (HibernateException e) {
            System.out.println("get currentSession failling");
            transaction.rollback();
            e.printStackTrace();
        }

        Query deleteAll = session.createQuery("delete from GotUrl");
        deleteAll.executeUpdate();
        transaction.commit();

        closeSession();
    }
}
