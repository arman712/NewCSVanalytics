package com.company.newCSVanalytics.util;

import com.company.newCSVanalytics.collections.ProductTreeSet;
import com.company.newCSVanalytics.domain.Product;

import java.util.Comparator;
import java.util.TreeSet;

public class GlobalData {

    private static int threadCount=0;
    private static TreeSet<Product> result=new ProductTreeSet<>((o1, o2) -> {
        if(o1.getPrice()>o2.getPrice()){
            return 1;
        } else if(o1.getPrice()==o2.getPrice()){
            return 1;
        } return -1;
    },1000);

    public static synchronized int getThreadCount() {
        return threadCount;
    }

    public static synchronized void setThreadCount(int threadCount) {
        GlobalData.threadCount = threadCount;
    }

    public static synchronized TreeSet<Product> getResult() {
        return result;
    }

    public static synchronized void setResult(TreeSet<Product> result) {
        GlobalData.result = result;
    }
}
