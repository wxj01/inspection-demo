package Main;

import bean.TimeDay;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 分页导出（周五）数据表格，导出一个时段的周五的数据，分页sheet表单
 */
public class TestFormbetter {

    public static void main(String[] args) throws Exception {


        /*
         * 分sheet导出查询记录
         */
        List<TimeDay> foolist=getStudentInfo();

        String templateDir = "src/main/java/templates/formInfoTemplate_outout.xls";
        String targetDir="e:/excel/export/testMultipleSheets.xls";
        List sheetNames = new ArrayList();
        for(int i=0;i<foolist.size();i++){
            TimeDay sa = (TimeDay)foolist.get(i);
            sheetNames.add(sa.getEveryFriday());

        }
        InputStream is = new BufferedInputStream(new FileInputStream(templateDir));
        XLSTransformer transformer = new XLSTransformer();
        HSSFWorkbook resultWorkBook = null;
        try {
            resultWorkBook = (HSSFWorkbook) transformer.transformMultipleSheetsList(is, foolist, sheetNames, "TimeDay", new HashMap(), 0);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        OutputStream os = new BufferedOutputStream(new FileOutputStream(targetDir));
        try {
            resultWorkBook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取当年1月1号~当前时间的
     * @return
     * @throws Exception
     */
    private static List<TimeDay> getStudentInfo() throws Exception {
        List<TimeDay> TimeDays = new ArrayList<TimeDay>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate = getCurrYearFirst()+"-01-01";
        String currenDate = sdf.format(new Date());
        List<String> allWeekly2 = getAllWeekly2(startDate, currenDate);
        if(allWeekly2 != null && allWeekly2.size() > 0){
            for (int aa = 0;aa < allWeekly2.size();aa++){
                TimeDays.add(new TimeDay(allWeekly2.get(aa)));
            }
        }
        return TimeDays;
    }



    /**
     * 获取当年的第一天
     * @param
     * @return
     */
    public static String getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return String.valueOf(currentYear);
    }


    /**
     * 获取起始时间startDate 至 结束时间endDate 时间段中所有的为星期五的数据
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<String> getAllWeekly2(String startDate, String endDate) throws Exception {

        // 获取两个日期之间的所有日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(format.parse(startDate));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(format.parse(endDate));
        List<String> Datelist = new ArrayList<String>();

        while (format.parse(endDate).after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Date date = calBegin.getTime();
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            String currSun = dateFm.format(date);
            if (currSun.equals("星期五")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String format1 = sdf.format(date);
                Datelist.add(format1);
            }
        }
        return Datelist;
    }

}
