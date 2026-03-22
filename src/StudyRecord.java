public class StudyRecord {
    private String date;        //学習日
    private String subject;     //科目
    private int minutes;        //学習時間
    private String memo;        //メモ

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
