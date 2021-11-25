package com.hinata.container;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 红黑树

 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap map=new HashMap();
        Hashtable hashtable=new Hashtable();
        ConcurrentHashMap c=new ConcurrentHashMap();
        /**
         * 跳表

         */
        ConcurrentSkipListMap concurrentSkipListMap=new ConcurrentSkipListMap();
        concurrentSkipListMap.put("1","2");
        concurrentSkipListMap.put(null,1);
        System.out.println((2&361));
    }
}
