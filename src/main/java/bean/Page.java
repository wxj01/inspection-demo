package bean;

import java.util.List;

public class Page {
    private String sheetName;
    private String currentPage;
    private String tolalPage;
    private List<?> data;
    private Object onlyOne;

    public Page(String sheetName, String currentPage, String tolalPage, List<?> data) {
        this.sheetName = sheetName;
        this.currentPage = currentPage;
        this.tolalPage = tolalPage;
        this.data = data;
    }

    public Page() {
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTolalPage() {
        return this.tolalPage;
    }

    public void setTolalPage(String tolalPage) {
        this.tolalPage = tolalPage;
    }

    public List<?> getData() {
        return this.data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Object getOnlyOne() {
        return this.onlyOne;
    }

    public void setOnlyOne(Object onlyOne) {
        this.onlyOne = onlyOne;
    }
}