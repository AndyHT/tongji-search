package test.com.huoteng;

import com.huoteng.entity.GotUrl;
import com.huoteng.json.Json;
import com.huoteng.model.Article;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
* Json Tester. 
* 
* @author <Authors name> 
* @since <pre>Jun 2, 2015</pre> 
* @version 1.0 
*/ 
public class JsonTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/**
*
* Method: changeUrlListToJson(List urls, boolean type)
*
*/
@Test
public void testChangeUrlListToJson() throws Exception {
    List<GotUrl> gotUrlList = new ArrayList<GotUrl>();
    for (int i = 0; i < 5; i++) {
        GotUrl gotUrl = new GotUrl();
        gotUrl.setId(i);
        gotUrl.setUrl("http://");
        gotUrl.setTitle("test");
        gotUrl.setData(new Date());
        gotUrlList.add(gotUrl);
    }

    System.out.println(Json.changeUrlListToJson(gotUrlList, true));

}

/** 
* 
* Method: changeArticleListToJson(List articles) 
* 
*/ 
@Test
public void testChangeArticleListToJson() throws Exception { 
    List<Article> articles = new ArrayList<Article>();

    for (int i = 0; i < 4; i++) {
        Article article = new Article("http://", "test", "test", new Date());
        articles.add(article);
    }

    System.out.println(Json.changeArticleListToJson(articles));
}



} 
