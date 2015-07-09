package test.com.huoteng;

import com.huoteng.controller.HibernateController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.File;
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
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllTargetURL() 
* 
*/ 
@Test
public void testFindAllTargetURL() throws Exception { 
    HibernateController controller = new HibernateController();
    controller.deleteAllGotUrl();
    System.out.println("succeed");
//}
//
///**
//*
//* Method: findAllGotURL()
//*
//*/
//@Test
//public void testFindAllGotURL() throws Exception {
//TODO: Test goes here... 
} 

/** 
* 
* Method: isUser(String name, String pass) 
* 
*/ 
@Test
public void testIsUser() throws Exception { 
    HibernateController controller = new HibernateController();

    System.out.println(controller.isUser("霍腾", "123"));
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
public void testDeleteGotURLbyURL() {
    HibernateController hibernateController = new HibernateController();
//    if (hibernateController.begin()) {
        hibernateController.deleteGotURLbyURL("http://sse.tongji.edu.cn/Notice/1004008");
//    }

}


    /**
* 
* Method: deleteAllGotUrl() 
* 
*/ 
@Test
public void testDeleteAllGotUrl() throws Exception {
    HibernateController testController = new HibernateController();
//    if (testController.begin()) {
        testController.deleteAllGotUrl();
//    }
}


} 
