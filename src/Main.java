import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;

public class Main{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in,StandardCharsets.UTF_8);

        System.out.print("日付を入力　：　");
        String date = sc.nextLine();
        System.out.print("科目を入力　：　");
        String subject = sc.nextLine();
        System.out.print("学習時間（分）を入力　：　");
        int minutes = Integer.parseInt(sc.nextLine());
        System.out.print("メモ　：　");
        String memo = sc.nextLine();

        //入力して保存
        StudyRecord record = new StudyRecord(
            date,subject, minutes, memo);
        CsvService service = new CsvService();
        service.save(record);
        
        sc.close();

        //CSVから読み込んだデータをList形式に変換
        List<StudyRecord> list = service.findAll();

        for(StudyRecord r : list){
            System.out.println(r);
        }
    }
}