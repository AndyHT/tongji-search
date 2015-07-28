package com.huoteng.model;

import java.util.ArrayList;

/**
 * 要去抓取内容的网页URL列表
 * 单例模式
 * Created by huoteng on 7/27/15.
 */
public class TargetUrlList {
    public ArrayList<String> urlsList = new ArrayList<String>();

    private static volatile TargetUrlList INSTANCE = null;

    private TargetUrlList() {}

    public static TargetUrlList getInstance() {
        if(INSTANCE == null){
            synchronized(TargetUrlList.class){
                //when more than two threads run into the first null check same time, to avoid instanced more than one time, it needs to be checked again.
                if(INSTANCE == null){
                    INSTANCE = new TargetUrlList();
                }
            }
        }
        return INSTANCE;
    }

}
