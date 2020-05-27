package com.company.newCSVanalytics.util;
import com.company.newCSVanalytics.domain.Product;
import com.company.newCSVanalytics.threads.ThreadParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class App {

    public static void start(String pathResources,String resultWritePath,String fileName,int threadCount){

        File[] files=Util.getFiles(pathResources);
        ConcurrentLinkedQueue<String> filesPaths=Util.getFilesPaths(files);
        while (true){
            String filePath=filesPaths.poll();
            if(filePath==null){
                break;
            }   while (true){

                if(GlobalData.getThreadCount()<threadCount){
                    new ThreadParser(filePath);
                    GlobalData.setThreadCount(GlobalData.getThreadCount()+1);
                    break;
                }

            }
        }
        while (true){

            if(GlobalData.getThreadCount()==0){
                List<Product> resultProducts=new ArrayList<>(GlobalData.getResult());
                File file =  Util.writeResult(resultProducts,resultWritePath,fileName);
                System.out.println("The program successfully completed the process: final file path :"+ file.getAbsolutePath());
                break;
            }
        }



    }

}
