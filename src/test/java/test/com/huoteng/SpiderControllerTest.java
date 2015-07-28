package test.com.huoteng;

import com.huoteng.controller.HibernateController;
import com.huoteng.controller.SpiderController;
import com.huoteng.model.Article;
import com.huoteng.spider.TongjiSpider;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/** 
* SpiderController Tester. 
* 
* @author <Authors name> 
* @since <pre>Jul 10, 2015</pre> 
* @version 1.0 
*/ 
public class SpiderControllerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: startSSESpider(NewsSpider spider, String url) 
* 
*/ 
@Test
public void testStartSSESpider() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: startTongjiSpider(TongjiSpider spider, String url) 
* 
*/ 
@Test
public void testStartTongjiSpider() throws Exception {
    SpiderController controller = new SpiderController();

    TongjiSpider spider = new TongjiSpider();

    controller.startTongjiSpider(spider, "http://news.tongji.edu.cn/classid-5.html");

    List articles = spider.getArticles();

    for (Object a : articles) {
        System.out.println(((Article) a).title);
        System.out.println(((Article) a).date);
        System.out.println(((Article) a).url);
        System.out.println(((Article) a).content);
    }

    HibernateController hibernateController = new HibernateController();
    hibernateController.addGotURL((ArrayList) articles);

}


} 
