package com.huoteng.json;

import com.huoteng.entity.GotUrl;
import com.huoteng.entity.TargetUrl;
import com.huoteng.model.Article;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 将查询的返回值转换为JSON格式并返回
 * Created by huoteng on 6/1/15.
 */
public class Json {

    /**
     * 将urls转换为json格式，分为两大类，一类TargetURL，第二类GotURL
     * false:TargetURL
     * true:GotURL
     * @param urls url List
     * @return json格式String
     */
    public static String changeUrlListToJson(List urls, boolean type) throws JSONException {
        JSONArray jsonArray = new JSONArray();

        if (type) {
            //GotURL
            for (Object object : urls) {
                GotUrl url = (GotUrl) object;

                //转为json格式
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", url.getId());
                jsonObject.put("url", url.getUrl());
                jsonObject.put("title", url.getTitle());
                jsonObject.put("date", url.getData().toString());

                jsonArray.put(jsonObject);
            }
        } else {
            //TargetURL
            for (Object object : urls) {
                TargetUrl url = (TargetUrl) object;

                //转为json格式
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", url.getId());
                jsonObject.put("url", url.getUrl());

                jsonArray.put(jsonObject);
            }
        }
        return jsonArray.toString();
    }

    /**
     * 将lucene查询返回的articles转换为json格式
     * @param articles articles List
     * @return json格式String
     */
    public static String changeArticleListToJson(List articles) throws JSONException {
        JSONArray jsonArray = new JSONArray();

        for (Object object : articles) {
            Article article = (Article) object;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", article.url);
            jsonObject.put("title", article.title);
            jsonObject.put("date", article.date);
            jsonObject.put("content", article.content);

            jsonArray.put(jsonObject);
        }

        return jsonArray.toString();
    }
}
