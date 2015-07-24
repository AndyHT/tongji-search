package com.huoteng.spider;

import com.huoteng.model.Article;
import com.huoteng.model.GotURL;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Spider抓取sse新闻
 * Created by huoteng on 5/5/15.
 */
public class NewsSpider implements PageProcessor {

    private ArrayList<Article> articles = new ArrayList<Article>();

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(1000);

    /**
     * 抓取网页的具体方法
     * @param newsPage 抓取新闻的网页
     */
    public void process(Page newsPage) {

        GotURL haveGot = new GotURL();

        List<String> newsList = newsPage.getHtml().css("div.news_title").all();//得到新闻标题和href

        System.out.println("得到News");

        Pattern urlPattern = Pattern.compile("http://sse[.]tongji[.]edu[.]cn/Notice/[\\d]+");//匹配url
        Pattern titlePattern = Pattern.compile(">[^/\\s]+</a>");//匹配title

        Matcher urlMatcher;
        Matcher titleMatcher;
        String url;
        String title;

        for (String aNews : newsList) {
//            System.out.println(aNews);

            urlMatcher = urlPattern.matcher(aNews);
            titleMatcher = titlePattern.matcher(aNews);

            if (urlMatcher.find() && titleMatcher.find()) {
                url = urlMatcher.group();
                title = titleMatcher.group();
                title = title.replaceAll("[></a>]", "");
//                System.out.println("URL:" + url);
//                System.out.println("Title:" + title);


                if (!haveGot.isExist(url)) {
                    //异步的方式去拿content
                    ContentSpider contentSpider = new ContentSpider();
                    Spider.create(contentSpider).addUrl(url).thread(1).run();

                    String newsContent = contentSpider.getContent();
                    Date newsDate = contentSpider.getDate();

                    articles.add(new Article(url, title, newsContent, newsDate));
                }


            }
        }

    }

    public Site getSite() {
        return sseSite;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

}
