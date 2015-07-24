package com.huoteng.controller;

import com.huoteng.entity.GotUrl;
import com.huoteng.spider.NewsSpider;
import com.huoteng.spider.TongjiSpider;
import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by huoteng on 5/25/15.
 */
public class SpiderController {

    public void startSSESpider(NewsSpider spider, String url) {
        Spider.create(spider)
                .addUrl(url)
                .thread(1)
                .run();
    }

    public void startTongjiSpider(TongjiSpider spider, String url) {
        Spider.create(spider)
                .addUrl(url)
                .thread(1)
                .run();

    }
}
