package test.com.huoteng;

import com.huoteng.controller.HibernateController;
import com.huoteng.lucene.IndexDirectory;
import com.huoteng.model.Article;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* IndexDirectory Tester. 
* 
* @author <Authors name> 
* @since <pre>Jul 9, 2015</pre> 
* @version 1.0 
*/ 
public class IndexDirectoryTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getDirectory() 
* 
*/ 
@Test
public void testGetDirectory() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setDirectory(Directory directory) 
* 
*/ 
@Test
public void testSetDirectory() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getIndexFile() 
* 
*/ 
@Test
public void testGetIndexFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setIndexFile(File indexFile) 
* 
*/ 
@Test
public void testSetIndexFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: delete(String fileName) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteFile(String fileName) 
* 
*/ 
@Test
public void testDeleteFile() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: deleteDirectory(String dir) 
* 
*/ 
@Test
public void testDeleteDirectory() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: createIndex(List<Article> articles) 
* 
*/ 
@Test
public void testCreateIndex() throws Exception {
    HibernateController hibernateController = new HibernateController();
    if (hibernateController.begin()) {
        List urls = hibernateController.findAllGotURL();

        IndexDirectory.createIndex(urls);
    }

}


} 
