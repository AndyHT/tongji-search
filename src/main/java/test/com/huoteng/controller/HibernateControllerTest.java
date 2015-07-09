package test.com.huoteng.controller; 

import com.huoteng.controller.HibernateController;
import com.huoteng.entity.TargetUrl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.List;

/** 
* HibernateController Tester. 
* 
* @author <Authors name> 
* @since <pre>Jul 9, 2015</pre> 
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
* Method: begin() 
* 
*/ 
@Test
public void testBegin() throws Exception { 
    HibernateController hibernateController = new HibernateController();
    hibernateController.begin();
    System.out.println("start 1");
    hibernateController.findAllTargetURL();


//    hibernateController.begin();
    hibernateController.findAllGotURL();
    System.out.println("start 2");
}

/** 
* 
* Method: findAllTargetURL() 
* 
*/ 
@Test
public void testFindAllTargetURL() throws Exception {
    HibernateController hibernate = new HibernateController();
    if (hibernate.begin()) {
        List urls = hibernate.findAllTargetURL();

        for (Object url : urls) {
            TargetUrl aUrl = (TargetUrl)url;
            System.out.println("URL:" + aUrl.getUrl());
        }
    }


}

/** 
* 
* Method: findAllGotURL() 
* 
*/ 
@Test
public void testFindAllGotURL() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isUser(String name, String pass) 
* 
*/ 
@Test
public void testIsUser() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteTargetURLbyURL(String theUrl) 
* 
*/ 
@Test
public void testDeleteTargetURLbyURL() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteGotURLbyURL(String theUrl) 
* 
*/ 
@Test
public void testDeleteGotURLbyURL() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addNewTargetURL(String url) 
* 
*/ 
@Test
public void testAddNewTargetURL() throws Exception { 
    String testURl = "hello";
    HibernateController hibernate = new HibernateController();
    if (hibernate.begin()) {
        hibernate.addNewTargetURL(testURl);
    }


}

/** 
* 
* Method: addGotURL(ArrayList gotUrls) 
* 
*/ 
@Test
public void testAddGotURL() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteAllGotUrl() 
* 
*/ 
@Test
public void testDeleteAllGotUrl() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteIndexFile() 
* 
*/ 
@Test
public void testDeleteIndexFile() throws Exception { 
//TODO: Test goes here... 
} 


} 
