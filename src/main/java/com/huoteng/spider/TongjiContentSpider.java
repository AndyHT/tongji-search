package com.huoteng.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huoteng on 7/10/15.
 */
public class TongjiContentSpider implements PageProcessor {


    private String content;

    private Date date;

    private Site sseSite = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page contentPage) {

        //将content里的web标签去掉
        content = TongjiContentSpider.splitAndFilterString(contentPage.getHtml().css("div.news_content").get());
        String newsInfo = TongjiContentSpider.splitAndFilterString(contentPage.getHtml().css("div.news_info").get());

        Pattern newsInfoPattern = Pattern.compile("[\\d]{2}[/][\\d]{2}[/][\\d]{4}");
        Matcher newsInfoMatcher = newsInfoPattern.matcher(newsInfo);

        if (newsInfoMatcher.find()) {
            String dateStr = newsInfoMatcher.group();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                date = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


    }

    public static String splitAndFilterString(String input) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
        str = str.replaceAll("[(>)<]", "");
        str = str.replaceAll("[\\s]*", "");
        return str;
    }

    public Site getSite() {
        return sseSite;
    }


    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
