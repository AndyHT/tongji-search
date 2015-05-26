package com.huoteng.lucene;

import com.huoteng.spider.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 索引查找
 * Created by huoteng on 5/25/15.
 */
public class SearchEngine {

    /**
     * lucene查询引擎
     * @param keyword 查找的关键词
     * @param directory 查找索引的目录
     * @return 查找结果ArrayList
     */
    public ArrayList search(String keyword, Directory directory) {
        ArrayList<Article> searchResultList = new ArrayList<Article>();

        Analyzer analyzer = new IKAnalyzer(true);

        DirectoryReader reader;
        IndexSearcher searcher;

        //搜索过程**********************************
        try {
            reader = DirectoryReader.open(directory);
            searcher = new IndexSearcher(reader);

            //使用QueryParser查询分析器构造Query对象
            QueryParser queryParse = new QueryParser(Version.LUCENE_40, "title-content", analyzer);
            queryParse.setDefaultOperator(QueryParser.AND_OPERATOR);
            Query query = queryParse.parse(keyword);
            System.out.println("Query = " + query);
            //搜索相似度最高的5条记录
            TopDocs topDocs = searcher.search(query, 10);
            System.out.println("命中：" + topDocs.totalHits);

            //输出结果
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for(int i = 0; i < topDocs.totalHits; ++i) {
                Document targetDoc = searcher.doc(scoreDocs[i].doc);
                System.out.println("url:" + targetDoc.getField("url").stringValue());
                System.out.println("title:" + targetDoc.getField("title").stringValue());
                searchResultList.add(new Article(targetDoc.getField("url").stringValue(),
                        targetDoc.getField("title").stringValue(),
                        targetDoc.getField("title-date-content").stringValue(),
                        targetDoc.getField("date").stringValue()));
            }

            return searchResultList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


}
