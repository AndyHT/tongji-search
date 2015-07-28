package test.com.huoteng;


import com.huoteng.model.Article;
import com.huoteng.spider.NewsSpider;
import com.huoteng.spider.UrlSpider;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 学习使用web magic
 * Created by huoteng on 7/24/15.
 */
public class LearnWebMagic {

    //如果这样可以爬虫的活就妥了
    //剩下的就是学习如何读取xml
    /**
     * 知网各种保护不让抓
     * cookie验证
     * js动态加载内容
     * fuck！！！
     */

    /**
     * 开始实验sina
     * 需要将汉字转为GB2312编码
     */

    public static PageProcessor spider = new UrlSpider();

    public static void main(String[] args) throws UnsupportedEncodingException {
        Spider.create(spider)
                .addUrl("http://search.sina.com.cn/?c=news&q=内蒙古&range=all&num=20&col=&source=&from=&country=&size=&time=&a=&page=2&pf=2131425477&ps=2134309112&dpc=1")
                .thread(1)
                .run();
//        String testStr = "%C4%DA%C3%C9%B9%C5+%B6%F5%B6%FB%B6%E0%CB%B9%7E%B0%FC%CD%B7%7E%BA%F4%BA%CD%BA%C6%CC%D8+-%B1%B1%BE%A9";
//        String stringPut = URLEncoder.encode("", "UTF-8");
//        String str = new String(testStr.getBytes("GB2312"), "UTF-8");
//        System.out.println(str);

//        System.out.println("content......");
//        for (Object object : spider.getArticles()) {
//            Article article = (Article)object;
//            System.out.println(article.content);
//        }
    }


}
