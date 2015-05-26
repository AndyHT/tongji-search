package com.huoteng.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

/**
 * 索引
 * Created by huoteng on 5/25/15.
 */
public class SearchIndex {

    /**
     * 建立article title的索引
     * @param url 文章的url
     * @param title 文章的title
     * @return 建立索引是否成功
     */
    public boolean createIndex(String url, String title, String content, String date, Directory directory) {
        boolean success = false;

        IndexWriter writer;
        Analyzer analyzer = new IKAnalyzer(true);

        try {

            //配置IndexWriterConfig
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            writer = new IndexWriter(directory, config);

            //写入索引
            Document doc = new Document();
            doc.add(new StringField("url", url, Field.Store.YES));
            doc.add(new StringField("title", title, Field.Store.YES));
            doc.add(new StringField("date", date, Field.Store.YES));
            doc.add(new TextField("title-date-content", title + "," + date + "," + content, Field.Store.YES));
            writer.addDocument(doc);
            writer.close();

            success = true;
        } catch (CorruptIndexException e) {
            e.printStackTrace();
        } catch (LockObtainFailedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return success;
        }
    }
}
