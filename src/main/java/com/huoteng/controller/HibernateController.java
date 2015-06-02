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
//    private boolean isNeedClose = false;

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

    public boolean begin() {
        boolean success = false;
        try {
            session = factory.getCurrentSession();
            transaction = session.beginTransaction();

            success = true;
        } catch (HibernateException e) {
            System.out.println("get current session fail");
            e.printStackTrace();
        }
        return success;
    }


    /**
     * 1.得到所有TargetURL
     * @return All TargetURL List
     */
    public List findAllTargetURL() {
        Query targetURLQuery = session.createQuery("from TargetUrl");

        return targetURLQuery.list();
    }

    /**
     * 2.得到所有GotURL
     * @return All GotURL List
     */
    public List findAllGotURL() {
        Query gotURLQuery = session.createQuery("from GotUrl ");

        return gotURLQuery.list();
    }

    /**
     * 3.验证指定User是否匹配
     * @param name 用户名
     * @param pass 密码
     * @return 匹配是否成功
     */
    public boolean isUser(String name, String pass) {
        boolean isUser = false;

        Query userQuery = session.createQuery("from Users");
        List users = userQuery.list();

        for (Object user : users) {
            if (name.equals(((Users) user).getName()) && pass.equals(((Users) user).getPass())) {
                isUser = true;
                break;
            }
        }
        return isUser;
    }

    /**
     * 4.根据ID删除TargetURL
     * @param id id
     * @return 删除的TargetURL List
     */
    public int deleteTargetURLbyID(int id) {
        Query deleteQuery = session.createQuery("delete from TargetUrl where id = ?");
        deleteQuery.setInteger(0, id);
        int deletedNumber = deleteQuery.executeUpdate();
        transaction.commit();
        return deletedNumber;
    }

    /**
     * 5.根据ID删除GotURL
     * @param id id
     * @return 删除的GotURL List
     */
    public int deleteGotURLbyID(int id) {
        Query deletedQuery = session.createQuery("delete from GotUrl where id = ?");
        deletedQuery.setInteger(0, id);
        int deletedNumber = deletedQuery.executeUpdate();
        transaction.commit();
        return deletedNumber;
    }

    /**
     * 6.新增一条TargetURL
     * @param url new URL
     */
    public void addNewTargetURL(String url) {
        TargetUrl targetUrl = new TargetUrl();
        targetUrl.setUrl(url);

        session.save(targetUrl);
        transaction.commit();
    }

    /**
     * 7.新增GotURL
     * @param gotUrls 需要加入Database的GotURLs
     */
    public void addGotURL(ArrayList gotUrls) {
        int i = 0;
        for (Object object : gotUrls) {
            i++;
            GotUrl gotUrl = new GotUrl();
            gotUrl.setUrl(((Article) object).url);
            gotUrl.setTitle(((Article) object).title);
            gotUrl.setData(((Article) object).date);
            session.save(gotUrl);

            if (0 == (i %20)) {
                session.flush();
                session.close();
            }
        }
        transaction.commit();
    }
}
