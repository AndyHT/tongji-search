package com.huoteng.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Spider抓取sse新闻
 * Created by huoteng on 5/5/15.
 */
public class NewsSpider implements PageProcessor {

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(1000);
    /**
     * 抓取网页的具体方法
     * @param newsPage 抓取新闻的网页
     */
    public void process(Page newsPage) {
        //将sse中的新闻列表抓出来
        Pattern urlPattern = Pattern.compile("http://sse[.]tongji[.]edu[.]cn/Notice/[\\d]+");//匹配url
        Pattern titlePattern = Pattern.compile("[^\\s\\ba-zA-Z=<>]*[^0-9a-zA-Z_:<>/.]+");//匹配title（无法匹配）

        List<String> newsList = newsPage.getHtml().css("div.news_title").all();//得到新闻标题和href
        String newsContent = newsPage.getHtml().css("div.content").get();//得到新闻内容,需要交给IKAnalyzer

        if (null != newsContent) {
            System.out.println("Content: " + newsContent);
            //叫给IKAnalyzer处理
        }

        for (String aNews : newsList) {
            System.out.println("news:" + aNews);
            Matcher urlMatcher = urlPattern.matcher(aNews);
            Matcher titleMatcher = titlePattern.matcher(aNews);
            /**
             * 将新闻的url和标题提取出来
             */
            if (urlMatcher.find() && titleMatcher.find()) {
                System.out.println("URL: " + urlMatcher.group());
                System.out.println("Title: " + titleMatcher.group());
                newsPage.addTargetRequest(urlMatcher.group());

                /**
                 * url & title存入数据库
                 */
            }
        }
    }

    public Site getSite() {
        return sseSite;
    }

    /**
     * test
     * @param args unuseful
     */
    public static void main(String[] args) {
        Spider.create(new NewsSpider())
                .addUrl("http://sse.tongji.edu.cn/InfoCenter/Lastest_Notice.aspx")
                .thread(1)
                .run();
    }
}
