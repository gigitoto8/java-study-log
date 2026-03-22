public class StudyRecord {
    private String date;
    private String subject;
    private int minutes;
    private String memo;

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
