public class StudyRecord {
    private String date;        //学習日
    private String subject;     //科目
    private int minutes;        //学習時間
    private String memo;        //メモ

    //コンストラクタ
    public StudyRecord(String date,String subject,int minutes,String memo){
        this.date = date;
        this.subject = subject;
        this.minutes = minutes;
        this.memo = memo;
    }

    //CSV保存
    public String toCsv(){
        return date + "," + subject + "," + minutes + "," + memo;
    }

    //表示用フォーマット定めて返すメソッド。定めない場合、ハッシュ値が表示される。
    @Override
    public String toString() {
        return date + " | " + subject + " | " + minutes + "分 | " + memo;
    }

    //getter
    public String getDate(){
        return date;
    }
    public String getSubject(){
        return subject;
    }
    public int getMinutes(){
        return minutes;
    }
    public String getMemo(){
        return memo;
    }




}
