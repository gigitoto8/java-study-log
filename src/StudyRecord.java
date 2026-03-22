public class StudyRecord {
    private String date;        //実施日
    private String subject;     //科目
    private int minutes;        //実施時間
    private String memo;        //メモ、補足事項等

    public StudyRecord(String date,String subject,int minutes,String memo){
        this.date = date;
        this.subject = subject;
        this.minutes = minutes;
        this.memo = memo;
    }

    public String toCsv(){
        return date + "," + subject + "," + minutes + "," + memo;
    }

}
