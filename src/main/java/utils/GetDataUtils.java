package utils;

import bean.InspectionInfo;
import bean.Page;
import bean.TimeDay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GetDataUtils {



    public static List<InspectionInfo> getListInfo() throws Exception {
        List<InspectionInfo> InspectionInfos = new ArrayList<InspectionInfo>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


//        String startDate = getCurrYearFirst()+"-01-01";
        String startDate = "2019-01-01";
        String currenDate = sdf.format(new Date());
        List<String> allWeekly2 = getAllWeekly2(startDate, currenDate);

        if(allWeekly2 != null && allWeekly2.size() > 0){
            for (int aa = 0;aa < allWeekly2.size();aa++){
                InspectionInfos.add(new InspectionInfo(aa+1,allWeekly2.get(aa),"9:30-11:00","乔婉","易君臣、马志杰、乔婉",""));
            }
        }
        return InspectionInfos;
    }





    public static List<TimeDay> getStudentInfo() throws Exception {

        List<TimeDay> TimeDays = new ArrayList<TimeDay>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


//        String startDate = getCurrYearFirst()+"-01-01";
        String startDate = "2019-01-01";
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



    public static List<String> getAllWeekly2(String startDate, String endDate) throws Exception {

        // 获取两个日期之间的所有日期
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(format.parse(startDate));
        Calendar calEnd = Calendar.getInstance();

        // 测试
        startDate  = "2019-01-01";
        endDate = "2019-12-31";

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

    public static ArrayList<String> getSheetName(List<Page> page) {
        ArrayList<String> al = new ArrayList();

        for(int i = 0; i < page.size(); ++i) {
            al.add(((Page)page.get(i)).getSheetName());
        }

        return al;
    }



    /**
     *
     * 方法描述：方法描述：获取节假日 访问接口，根据返回值判断当前日期是否为工作日，
     * 返回结果：检查具体日期是否为节假日，工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2；
     * @author xyl
     */
    public static  String  getHoliday(String  time) {
//        String dc = "http://tool.bitefu.net/jiari/?d=";
        String dc = "http://apis.baidu.com/xiaogg/holiday/holiday";
//        String httpUrl = "http://www.easybots.cn/api/holiday.php";
        String  httpUrl=new StringBuffer().append(dc).append(time).toString();
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {

        }
        return result;
    }



//    /**
//     * @param urlAll :请求接口
//     * @param httpArg :参数
//     * @return 返回结果
//     */
//    public static String request(String httpArg)
//    {
////        String httpUrl = "http://www.easybots.cn/api/holiday.php";
//	String httpUrl = "http://apis.baidu.com/xiaogg/holiday/holiday";
//        HttpClient httpClient = new HttpClient();
//        GetMethod method = new GetMethod(httpUrl+"?"+httpArg);
//        String response = "";
//        try
//        {
//// 需要添加的header数据
//            List<Header> headers = new ArrayList<Header>();
//            Header header = new Header();
//            header.setName("apikey");
//            header.setValue("7bd4e99adcc28e337cea79191fef87fc");
//            headers.add(header);
//            httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
//            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//            int statusCode = httpClient.executeMethod(method);
//            if (statusCode == HttpStatus.SC_OK)
//            {
//// 获取到的回执信息
//                InputStream resStream = method.getResponseBodyAsStream();
//                BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
//                StringBuffer resBuffer = new StringBuffer();
//                String resTemp = "";
//                while ((resTemp = br.readLine()) != null)
//                {
//                    resBuffer.append(resTemp);
//                }
//                response = resBuffer.toString();
//            }
//        }
//        catch (HttpException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            method.releaseConnection();
//        }
//        return response;
//    }




    /**
     * 获取当前日期的前一天
     * @param pattern 需要返回的日期格式，例如：yyyy-MM-dd HH:mm:ss
     * @return 前一天日期字符串
     */
    public static Date beforeDayByNowDay(String pattern){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); //得到前一天
        Date date = calendar.getTime();
        return date;
    }
}
