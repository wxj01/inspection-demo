package utils;

import bean.Page;

import java.util.ArrayList;
import java.util.List;

public class DataByPage {
    static int pagesize = 3;

    public DataByPage() {
    }

    public static int countPages(List<?> dataList) {
        int recordcount = dataList.size();
        return (recordcount + pagesize - 1) / pagesize;
    }

    public static List<Page> byPage(List<?> dataList) {
        int nowDataListPoint = 0;
        int pagecount = countPages(dataList);
        List<Page> pageList = new ArrayList();

        for(int i = 0; i < pagecount; ++i) {
            ArrayList pagedata = new ArrayList();

            while(nowDataListPoint < dataList.size()) {
                pagedata.add(dataList.get(nowDataListPoint));
                ++nowDataListPoint;
                if (nowDataListPoint != 0 && nowDataListPoint % pagesize == 0) {
                    break;
                }
            }

            Page page = new Page("page_" + (i + 1), String.valueOf(i + 1), String.valueOf(pagecount), pagedata);
            pageList.add(page);
        }

        return pageList;
    }
}