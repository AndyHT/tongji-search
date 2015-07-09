package com.huoteng.spider;

import com.huoteng.model.Article;
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
 * Created by huoteng on 7/10/15.
 */
public class TongjiSpider implements PageProcessor {
    private ArrayList<Article> articles = new ArrayList<Article>();

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(1000);

    /**
     * 抓取网页的具体方法
     * @param newsPage 抓取新闻的网页
     */
    public void process(Page newsPage) {

        List<String> blackNewsList = newsPage.getHtml().css("li.li_black").all();//得到black新闻标题和href
        List<String> whiteNesList = newsPage.getHtml().css("li.li_white").all();//得到white新闻标题和href

        System.out.println("得到News");

        Pattern urlPattern = Pattern.compile("http://news[.]tongji[.]edu[.]cn/[\\S]+.html");//匹配url
        Pattern titlePattern = Pattern.compile(">[^/\\s]+</a>");//匹配title

        Matcher urlMatcher;
        Matcher titleMatcher;
        String url;
        String title;
        
        for (String aNews : blackNewsList) {
//            System.out.println(aNews);

            urlMatcher = urlPattern.matcher(aNews);
            titleMatcher = titlePattern.matcher(aNews);

            if (urlMatcher.find() && titleMatcher.find()) {
                url = urlMatcher.group();
                title = titleMatcher.group();
                title = title.replaceAll("[></a>]", "");
//                System.out.println("URL:" + url);
//                System.out.println("Title:" + title);

                //异步的方式去拿content
                TongjiContentSpider contentSpider = new TongjiContentSpider();
                Spider.create(contentSpider).addUrl(url).thread(1).run();

                String newsContent = contentSpider.getContent();
                Date newsDate = contentSpider.getDate();

                articles.add(new Article(url, title, newsContent, newsDate));
            }
        }

        for (String aNews : whiteNesList) {
//            System.out.println(aNews);

            urlMatcher = urlPattern.matcher(aNews);
            titleMatcher = titlePattern.matcher(aNews);

            if (urlMatcher.find() && titleMatcher.find()) {
                url = urlMatcher.group();
                title = titleMatcher.group();
                title = title.replaceAll("[></a>]", "");
//                System.out.println("URL:" + url);
//                System.out.println("Title:" + title);

                //异步的方式去拿content
                TongjiContentSpider contentSpider = new TongjiContentSpider();
                Spider.create(contentSpider).addUrl(url).thread(1).run();

                String newsContent = contentSpider.getContent();
                Date newsDate = contentSpider.getDate();

                articles.add(new Article(url, title, newsContent, newsDate));
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
