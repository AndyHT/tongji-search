package com.huoteng.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 抓取content爬虫
 * Created by huoteng on 5/26/15.
 */
public class ContentSpider implements PageProcessor {


    private String content;

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page contentPage) {

        //将content里的web标签去掉
        content = ContentSpider.splitAndFilterString(contentPage.getHtml().css("div.content").get());

    }

    public static String splitAndFilterString(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        str = str.replaceAll("[\\s]*", "");
        return str;
    }

    public Site getSite() {
        return sseSite;
    }


    public String getContent() {
        return content;
    }
}
