package com.huoteng.controller;

import com.huoteng.spider.NewsSpider;
import us.codecraft.webmagic.Spider;

/**
 * Created by huoteng on 5/25/15.
 */
public class SpiderController {

    public void startSpider(NewsSpider spider) {
        Spider.create(spider)
                .addUrl("http://sse.tongji.edu.cn/InfoCenter/Lastest_Notice.aspx")
                .thread(1)
                .run();
    }
}
