/**
 * 
 */
package org.jboss.windup.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.windup.utils.model.StringArrayModel;

/**
 * @author mnovotny
 *
 */
public class WindupReportComparison
{

    public static final Logger logger = LogManager.getLogger(WindupReportComparison.class);
    
    private List<StringArrayModel> originalReport;
    
    private List<StringArrayModel> newReport;
    
    
    public List<String[]> compareNewAndOldReports() {
        List<StringArrayModel> result = new ArrayList<>();
        if (originalReport == null || newReport == null) {
            logger.debug("One of the reports is empty");
            return null;
        }
        
        Set<StringArrayModel> intersect = new HashSet<>(originalReport);
        intersect.retainAll(newReport);
        logger.trace("Intersection has got " + intersect.size());
        
        result.addAll(newReport);
        logger.trace("Result has got " + result.size());
        result.removeAll(intersect);
        logger.trace("Result has got " + result.size());
        
        return StringArrayModel.fromStringArrayModelList(result);
    }
    
    public List<String[]> compareNewAndOldReportsWithDiffLines() {

        if (originalReport == null || newReport == null) {
            logger.debug("One of the reports is empty");
            return null;
        }
        
        Set<StringArrayModel> intersect = new HashSet<>(originalReport);
        intersect.retainAll(newReport);
        logger.trace("Intersection has got " + intersect.size());

        List<StringArrayModel> newReportDiff = new ArrayList<>(newReport);
        logger.trace("Result has got " + newReportDiff.size());
        newReportDiff.removeAll(intersect);
        logger.trace("Result has got " + newReportDiff.size());

        List<StringArrayModel> oldReportDiff = new ArrayList<>(originalReport);
        logger.trace("Result has got " + oldReportDiff.size());
        oldReportDiff.removeAll(intersect);
        logger.trace("Result has got " + oldReportDiff.size());
        
        List<StringArrayModel> diffedResult = new ArrayList<>();
        diffedResult.addAll(oldReportDiff);
        diffedResult.addAll(newReportDiff);
        
        return StringArrayModel.fromStringArrayModelList(diffedResult);
    }    

    public WindupReportComparison(List<String[]> oldList, List<String[]> newList) {
        this.newReport = StringArrayModel.fromStringArrayList(newList);
        this.originalReport = StringArrayModel.fromStringArrayList(oldList);
    }
}
