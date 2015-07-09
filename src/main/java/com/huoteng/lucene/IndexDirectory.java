package com.huoteng.lucene;

import com.huoteng.entity.GotUrl;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huoteng on 5/25/15.
 */
public class IndexDirectory {


    private static File indexFile;

    private static Directory directory;

    public static Directory getDirectory() {
        return directory;
    }

    public static void setDirectory(Directory directory) {
        IndexDirectory.directory = directory;
    }

    public static File getIndexFile() {
        return indexFile;
    }

    public static void setIndexFile(File indexFile) {
        IndexDirectory.indexFile = indexFile;
    }


    /**
     * 删除Index
     * @param fileName index 目录
     * @return 是否成功
     */
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

    public static void createIndex(List<GotUrl> urls) throws IOException {
        Directory directory = IndexDirectory.getDirectory();
        String indexFileName = "/Users/huoteng/Documents/index/";
        //删除Index文件
        File indexFile = new File(indexFileName);
        if (indexFile.exists()) {
            //删index
            IndexDirectory.delete(indexFileName);
        }
        //建index
        boolean createIndexFolderIsSuccess = indexFile.mkdir();
        IndexDirectory.setIndexFile(indexFile);
        directory = new SimpleFSDirectory(IndexDirectory.getIndexFile());
        IndexDirectory.setDirectory(directory);

        SearchIndex index = new SearchIndex();


        for (Object object : urls) {
            GotUrl aUrl = (GotUrl)object;
            System.out.println("url:" + aUrl.getUrl());
            System.out.println("title:" + aUrl.getTitle());
            System.out.println("date:" + aUrl.getData());
            System.out.println();

            index.createIndex(aUrl.getUrl(), aUrl.getTitle(), aUrl.getContent(), aUrl.getData(), directory);
        }
    }

}
