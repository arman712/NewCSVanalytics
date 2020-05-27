package com.company.newCSVanalytics.threads;
import com.company.newCSVanalytics.domain.Product;
import com.company.newCSVanalytics.util.GlobalData;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ThreadParser implements Runnable {

    private String path;


    public ThreadParser(String path){
          this.path=path;
          new Thread(this).start();
    }

    @Override
    public void run() {

        try{

    Path filePath=Paths.get(path);
    BufferedReader bufferedReader= Files.newBufferedReader(filePath);
    bufferedReader.readLine();

    while (bufferedReader.ready()) {
        String productStr = bufferedReader.readLine();
        String[] productArray = productStr.split(",", 5);

        if (!StringUtils.isEmpty(productArray[0]) && !StringUtils.isEmpty(productArray[4])) {
            Product product = new Product();
            product.setId(Integer.parseInt(productArray[0]));
            product.setName(productArray[1]);
            product.setCondition(productArray[2]);
            product.setState(productArray[3]);
            product.setPrice(Float.parseFloat(productArray[4]));
            synchronized (GlobalData.getResult()) {
                GlobalData.getResult().add(product);
            }

        }

    }  }catch (IOException e){
            System.out.println("When reading a file located in a directory: "+path + "\n"+
                    "something went wrong. The results from this file may not be taken into account in the final file");
            e.printStackTrace();
        }


    synchronized (GlobalData.class) {
            GlobalData.setThreadCount(GlobalData.getThreadCount() - 1);
        }


    }
}
