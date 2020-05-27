package com.company.newCSVanalytics.util;


import com.company.newCSVanalytics.CSVWriter.SimpleCSVWriter;
import com.company.newCSVanalytics.domain.Product;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Util {

    public static File[] getFiles(String dir) {
        File file = new File(dir);
        if (file.listFiles().length != 0) {
            return file.listFiles();
        }

        return null;
    }


    public static ConcurrentLinkedQueue<String> getFilesPaths(File[] files) {

        return Arrays.stream(files)
                .map(File::getAbsolutePath)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }
    public static File  writeResult(List<Product> resultProducts, String packagePath, String fileName) {

        return SimpleCSVWriter.toCSVFile(resultProducts, packagePath, fileName);
    }


}
