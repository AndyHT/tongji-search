package com.huoteng.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Spider抓取sse新闻
 * Created by huoteng on 5/5/15.
 */
public class NewsSpider implements PageProcessor {



    private String newsContent;

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
        newsContent = newsPage.getHtml().css("div.content").get();//得到新闻内容,需要交给IKAnalyzer

        for (String aNews : newsList) {
            System.out.println("news:" + aNews);
            Matcher urlMatcher = urlPattern.matcher(aNews);
            Matcher titleMatcher = titlePattern.matcher(aNews);
            /**
             * 将新闻的url和标题提取出来
             */
            if (urlMatcher.find() && titleMatcher.find()) {
                String url = urlMatcher.group();
                String title = titleMatcher.group();
                System.out.println("URL: " + url);
                System.out.println("Title: " + title);
                newsPage.addTargetRequest(urlMatcher.group());

                /**
                 * url & title存入数据库
                 */

                /**
                 * title & content保存到磁盘上
                 */
                if (null != newsContent) {
                    System.out.println("Content: " + newsContent);
                    //存储到磁盘上

                    File newsFile = new File("/Users/huoteng/Documents/content" + title);

                }
            }
        }


    }

    public Site getSite() {
        return sseSite;
    }

    public String getNewsContent() {
        return newsContent;
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
