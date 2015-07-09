package test.com.huoteng;

import com.huoteng.controller.HibernateController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.io.File;

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
//TODO: Test goes here... 
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
public void createIndexFolder() throws Exception {
    File indexFolder = new File("/Users/huoteng/Documents/index/");
    if (indexFolder.exists()) {
        //删
    } else {
        //建
        indexFolder.mkdir();
    }
}

/** 
* 
* Method: addNewTargetURL(String url) 
* 
*/ 
@Test
public void deleteIndex() throws Exception {

    //String fileName = "g:/temp/xwz.txt";
    //DeleteFileUtil.deleteFile(fileName);
    String fileDir = "/Users/huoteng/Documents/index/";
    //DeleteFileUtil.deleteDirectory(fileDir);
    delete(fileDir);
}



    public static boolean delete(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("删除文件失败："+fileName+"文件不存在");
            return false;
        }else{
            if(file.isFile()){

                return deleteFile(fileName);
            }else{
                return deleteDirectory(fileName);
            }
        }
    }

    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            file.delete();
            System.out.println("删除单个文件"+fileName+"成功！");
            return true;
        }else{
            System.out.println("删除单个文件"+fileName+"失败！");
            return false;
        }
    }

    public static boolean deleteDirectory(String dir){
        //如果dir不以文件分隔符结尾，自动添加文件分隔符
        if(!dir.endsWith(File.separator)){
            dir = dir+File.separator;
        }
        File dirFile = new File(dir);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if(!dirFile.exists() || !dirFile.isDirectory()){
            System.out.println("删除目录失败"+dir+"目录不存在！");
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for(int i=0;i<files.length;i++){
            //删除子文件
            if(files[i].isFile()){
                flag = deleteFile(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
            //删除子目录
            else{
                flag = deleteDirectory(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
        }

        if(!flag){
            System.out.println("删除目录失败");
            return false;
        }

        //删除当前目录
        if(dirFile.delete()){
            System.out.println("删除目录"+dir+"成功！");
            return true;
        }else{
            System.out.println("删除目录"+dir+"失败！");
            return false;
        }
    }

    /**
* 
* Method: deleteAllGotUrl() 
* 
*/ 
@Test
public void testDeleteAllGotUrl() throws Exception {
    HibernateController testController = new HibernateController();
    if (testController.begin()) {
        testController.deleteAllGotUrl();
    }
}


} 
