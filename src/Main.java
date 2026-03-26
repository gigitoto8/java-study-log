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
        
        
        //CSVから読み込んだデータをList形式に変換
        List<StudyRecord> list = service.findAll();
        
        for(StudyRecord r : list){
            System.out.println(r);
        }
        
        //検索（科目のみ）
        /*
        list = service.findBySubject("Java");
        for(StudyRecord r : list){
            System.out.println(r);
            }
        */
        
        //検索（日付、科目、メモ）
        String field;

        //科目入力
        while(true){
            System.out.print("次の内、検索したい項目を入力\n [\"subject\" , \"date\" , \"memo\" ] : ");
            field = sc.nextLine();
            if((field.equals("subject")) || (field.equals("date")) 
                || (field.equals("memo"))){
                break;
            }
            System.out.println("入力値が不正です。もう一度入力してください。");
        }
        
        //検索ワード入力
        System.out.print("検索ワード入力　：　");
        String value = sc.nextLine();
        
        list = service.findByCondition(field,value);
        
        //検索結果表示
        if(!(list.isEmpty())){
            for(StudyRecord r : list){
                System.out.println(r);
            }
        }else{
            System.out.println("該当なし");
        }

        sc.close();        
    }
}