package com.huoteng.lucene;

import com.huoteng.model.Article;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            QueryParser queryParse = new QueryParser(Version.LUCENE_40, "title-date-content", analyzer);
            queryParse.setDefaultOperator(QueryParser.AND_OPERATOR);
            Query query = queryParse.parse(keyword);
            System.out.println("Query = " + query);
            //搜索相似度最高的10条记录
            TopDocs topDocs = searcher.search(query, 10);
            System.out.println("命中：" + topDocs.totalHits);

            //输出结果
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;

            for(int i = 0; i < topDocs.totalHits; ++i) {
                if (i == 10) {
                    break;
                }
                System.out.println("i:" + i);
                Document targetDoc = searcher.doc(scoreDocs[i].doc);
                System.out.println("url:" + targetDoc.getField("url").stringValue());
                System.out.println("title:" + targetDoc.getField("title").stringValue());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                System.out.println("date:" + targetDoc.getField("date").stringValue());
                Date date = dateFormat.parse(targetDoc.getField("date").stringValue());

                searchResultList.add(new Article(targetDoc.getField("url").stringValue(),
                        targetDoc.getField("title").stringValue(),
                        targetDoc.getField("title-date-content").stringValue(),
                        date));
            }

            return searchResultList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return searchResultList;
    }


}
