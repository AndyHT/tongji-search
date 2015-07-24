package com.huoteng.model;

import com.huoteng.controller.HibernateController;
import com.huoteng.entity.GotUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huoteng on 7/24/15.
 */
public class GotURL {

    public ArrayList<String> haveGotUrls = new ArrayList<String>();

    public GotURL() {
        //连接数据库得到所有抓过的URL
        HibernateController controller = new HibernateController();
        List gotUlrList = controller.findAllGotURL();
        for (Object o : gotUlrList) {
            GotUrl gotUrl = (GotUrl)o;
            haveGotUrls.add(gotUrl.getUrl());
        }
    }

    public boolean isExist(String url) {
        return  haveGotUrls.contains(url);
    }
}
