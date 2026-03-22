import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("日付を入力　：　");
        String date = sc.nextLine();

        System.out.print("科目を入力　：　");
        String subject = sc.nextLine();

        System.out.print("学習時間（分）を入力　：　");
        int minutes = Integer.parseInt(sc.nextLine());

        System.out.print("メモ　：　");
        String memo = sc.nextLine();

        StudyRecord record = new StudyRecord(
            date,subject, minutes, memo);
        CsvService service = new CsvService();
        service.save(record);
        
        sc.close();
    }
}