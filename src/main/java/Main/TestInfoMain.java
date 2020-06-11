package Main;

import bean.InspectionInfo;
import bean.Page;
import utils.DelSheet;
import utils.JxlsUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 列表下载，将一段时间的内周五的数据生成列表
 */
public class TestInfoMain {
    public TestInfoMain() {
    }

    public static void main(String[] args) throws Exception {
//        String templatePath = "E:/template5_2.xls";
        List<InspectionInfo> list = getStudentInfo();
        String templatePath = "src/main/java/templates/InspectionTemplate.xlsx";
        OutputStream os = new FileOutputStream("E:/excel/export/Infoout5_2.xlsx");

        List<Page> page = individual(list);
        Map<String, Object> model = new HashMap();
        model.put("InspectionInfos", page);
        model.put("sheetNames", getSheetName(page));
        JxlsUtils.exportExcel(templatePath, os, model);
        os.close();
//        DelSheet.deleteSheet("E:/excel/export/Infoout5_2.xls", "template");
        System.out.println("完成");
    }


    /**
     * 获取每个分sheet 的sheetName 集合
     * @param page
     * @return
     */
    public static ArrayList<String> getSheetName(List<Page> page) {
        ArrayList<String> al = new ArrayList();

        for(int i = 0; i < page.size(); ++i) {
            al.add(((Page)page.get(i)).getSheetName());
        }

        return al;
    }



    public static List<Page> individual(List<InspectionInfo> list) {
        List<Page> pages = new ArrayList();

        for(int i = 0; i < list.size(); ++i) {
            Page p = new Page();
            p.setOnlyOne(list.get(i));
            p.setSheetName(((InspectionInfo)list.get(i)).getEveryFriday());
            pages.add(p);
        }

        return pages;
    }



    private static List<InspectionInfo> getStudentInfo() throws Exception {
        List<InspectionInfo> InspectionInfos = new ArrayList<InspectionInfo>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String startDate = getCurrYearFirst()+"-01-01";
        String currenDate = sdf.format(new Date());
        List<String> allWeekly2 = getAllWeekly2(startDate, currenDate);

        if(allWeekly2 != null && allWeekly2.size() > 0){
            for (int aa = 0;aa < allWeekly2.size();aa++){
                InspectionInfos.add(new InspectionInfo(aa,allWeekly2.get(aa),"9:30-11:00","小明","小红、小明","系统OK"));
            }
        }
        return InspectionInfos;
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
