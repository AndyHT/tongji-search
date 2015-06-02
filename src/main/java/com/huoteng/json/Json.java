package com.huoteng.json;

import java.util.List;

/**
 * 将查询的返回值转换为JSON格式并返回
 * Created by huoteng on 6/1/15.
 */
public class Json {

    /**
     * 将urls转换为json格式，分为两大类，一类TargetURL，第二类GotURL
     * @param urls url List
     * @return json格式String
     */
    public static String changeUrlListToJson(List urls) {
        String result;

        //遍历urls
        for (Object o : urls) {

        }

        return null;
    }

    /**
     * 将lucene查询返回的articles转换为json格式
     * @param articles articles List
     * @return json格式String
     */
    public static String changeArticleListToJson(List articles) {
        return null;
    }
}
