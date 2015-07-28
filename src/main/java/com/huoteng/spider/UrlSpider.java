package com.huoteng.spider;

import com.huoteng.model.Article;
import com.huoteng.model.TargetUrlList;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 适用于Sina高级搜索
 * 在网页上抓取到的下一页连接不可用。fuck
 * Created by huoteng on 7/27/15.
 */
public class UrlSpider implements PageProcessor {

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(500);

    /**
     * @param urlPage 抓取网页上的URL放入TargetUrlList
     */
    public void process(Page urlPage) {

        System.out.println(urlPage.getHtml().toString());

//        List<String> newsList = urlPage.getHtml().css("div.box-result").all();//得到新闻url
//        String nextPages = urlPage.getHtml().css("div.pagebox").get();//得到下一页url
//        String[] pageList = nextPages.split("<a");
//
//        System.out.println("得到News List");
//
//        Pattern urlPattern = Pattern.compile("http://[\\S]*html");//匹配新闻url
//        Pattern nextPageUrlPattern = Pattern.compile("http://search.sina.com.cn/[\\S]+dpc=1");//匹配下一页url
//
//        Matcher urlMatcher;
//        String url;
//        TargetUrlList urls = TargetUrlList.getInstance();
//
//        System.out.println(nextPages);
//        Matcher nextPageUrlMatcher;
//
//        for (String pageUrl : pageList) {
//            nextPageUrlMatcher = nextPageUrlPattern.matcher(pageUrl);
//
//            if (nextPageUrlMatcher.find()) {
//                url = nextPageUrlMatcher.group();
//                System.out.println("Page Next: " + url);
//
//                urlPage.addTargetRequest(url);
//            }
//        }
//
//        for (String aNews : newsList) {
//            urlMatcher = urlPattern.matcher(aNews);
//
//            if (urlMatcher.find()) {
//                url = urlMatcher.group();
//                System.out.println("URL:" + url);
//                urls.urlsList.add(url);
//            }
//        }

    }

    public Site getSite() {
        return sseSite;
    }


}
