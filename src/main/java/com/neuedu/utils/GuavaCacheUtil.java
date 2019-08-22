package com.neuedu.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheUtil {

    private static final LoadingCache<String,String> loadingCache= CacheBuilder
            .newBuilder()
            .initialCapacity(1000)
            .maximumSize(10000)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });


    public static void put(String key,String value){
        loadingCache.put(key,value);
    }

    public static String get(String key){
        try {
            String value=loadingCache.get(key);
            if(!value.equals("null")){
                return value;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
