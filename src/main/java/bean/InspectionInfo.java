package bean;

public class InspectionInfo {

    private int number;
    private String everyFriday;
    private String inspectionTime;
    private String inspectionPerson;
    private String smsAlarmPerson;
    private String  remarks;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEveryFriday() {
        return everyFriday;
    }

    public void setEveryFriday(String everyFriday) {
        this.everyFriday = everyFriday;
    }

    public String getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(String inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getInspectionPerson() {
        return inspectionPerson;
    }

    public void setInspectionPerson(String inspectionPerson) {
        this.inspectionPerson = inspectionPerson;
    }

    public String getSmsAlarmPerson() {
        return smsAlarmPerson;
    }

    public void setSmsAlarmPerson(String smsAlarmPerson) {
        this.smsAlarmPerson = smsAlarmPerson;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "InspectionInfo{" +
                "number=" + number +
                ", everyFriday=" + everyFriday +
                ", inspectionTime='" + inspectionTime + '\'' +
                ", inspectionPerson='" + inspectionPerson + '\'' +
                ", smsAlarmPerson='" + smsAlarmPerson + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public InspectionInfo(int number, String everyFriday, String inspectionTime, String inspectionPerson, String smsAlarmPerson, String remarks) {
        this.number = number;
        this.everyFriday = everyFriday;
        this.inspectionTime = inspectionTime;
        this.inspectionPerson = inspectionPerson;
        this.smsAlarmPerson = smsAlarmPerson;
        this.remarks = remarks;
    }
}
