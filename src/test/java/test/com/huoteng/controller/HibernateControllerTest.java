package test.com.huoteng.controller; 

import com.huoteng.controller.HibernateController;
import com.huoteng.model.Article;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.Date;

/** 
* HibernateController Tester. 
* 
* @author <Authors name> 
* @since <pre>Jun 2, 2015</pre> 
* @version 1.0 
*/ 
public class HibernateControllerTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
}

/** 
* 
* Method: deleteGotURLbyID(int id) 
* 
*/ 
@Test
public void testDeleteGotURLbyID() throws Exception { 
//    HibernateController hibernate = new HibernateController();
//    if (hibernate.begin()) {
//        hibernate.deleteGotURLbyID(1);
//    }
}


@Test
public void testAddGotURL() throws Exception {
    HibernateController hibernate = new HibernateController();
    ArrayList<Article> test = new ArrayList<Article>();
    test.add(new Article("http://www.baidu.com", "test", "测试用", new Date()));
    test.add(new Article("https://google.com", "test", "测试用", new Date()));
    if (hibernate.begin()) {
        hibernate.addGotURL(test);
    }

}

} 
