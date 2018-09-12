/**
 *
 */
package org.jboss.windup.utils;

import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.windup.utils.model.ReportModel;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author mnovotny
 *
 */
public class CsvWindupExportLoader
{
    public static final Logger logger = LogManager.getLogger(CsvWindupExportLoader.class);

    private static final String[] CSV_COLUMNS = {"Rule Id","Problem type","Title","Description","Links","Application","File Name","File Path","Line","Story points"};
    private static final String[] REPORTMODEL_FIELDS = {"ruleId","problemType","title","description","links","application","fileName","filePath","lineNumber","storyPoints"};

    // init mapping for CSV column to ReportModel bean
    private static Map<String,String> mapping = new HashMap<String,String>();
    static {
        for (int i = 0; i < CSV_COLUMNS.length; i++)
        {
            mapping.put(CSV_COLUMNS[i], REPORTMODEL_FIELDS[i]);
        }
    }

    private URL fileToLoad;

    private char delimiter;

    private HeaderColumnNameTranslateMappingStrategy<String[]> mappingStrategy;

    public CsvWindupExportLoader(URL fileUrl, char delimiter) {
        this.fileToLoad = fileUrl;
        this.delimiter = delimiter;
        setColumnPositionMappingStrategy();
    }

    private void setColumnPositionMappingStrategy() {
        mappingStrategy = new HeaderColumnNameTranslateMappingStrategy<String[]>();
        mappingStrategy.setColumnMapping(mapping);
//        mappingStrategy.setType(ReportModel.class);
    }

    public List<String[]> parseCSV() {

        List<String[]> listOfReportModels = new ArrayList<String[]>();
        String file = fileToLoad.getFile();

        try (CSVReader reader = new CSVReader(new FileReader(file), delimiter))
        {
            listOfReportModels = reader.readAll();
        }
        catch (Exception e) {
            logger.error("Something wrong happened while loading CSV file " + e.getLocalizedMessage());
            e.printStackTrace();
        }


        return listOfReportModels;
    }
}
