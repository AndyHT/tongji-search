package com.huoteng.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by huoteng on 5/26/15.
 */
public class ContentSpider implements PageProcessor {


    private String content;

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page contentPage) {
        content = contentPage.getHtml().css("div.content").get();

        /*
        将content里的web标签去掉
         */

    }

    public Site getSite() {
        return null;
    }


    public String getContent() {
        return content;
    }
}
