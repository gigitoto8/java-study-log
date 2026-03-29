import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

public class Main{
    public static void main(String[] args) {
        
        //入力関係
        Scanner sc = new Scanner(System.in,StandardCharsets.UTF_8);
        //InputValidatorクラス用
        InputValidator iv = new InputValidator(sc);
        //CsvServiceクラス用
        CsvService service = new CsvService();

        //データ入力登録
        String date = iv.inputString("日付を入力 : ");
        String subject = iv.inputString("科目を入力 : ");
        int minutes = iv.inputInt("学習時間を入力 : ");
        String memo = iv.inputString("メモを入力 : ");
        //データ登録
        StudyRecord record = new StudyRecord(
            date,subject, minutes, memo);
        service.save(record);
            
        System.out.println("----------------");
            
        //CSVから読み込んだデータをList形式に変換
        List<StudyRecord> list = service.findAll();
        //listに格納されているデータを１件ずつ表示
        for(StudyRecord r : list){
            System.out.println(r);
        }
            
        System.out.println("----------------");
        
        //検索（日付、科目、学習時間、メモ）

        String field;        
        //検索項目名入力
        while(true){
            System.out.print("次の内、検索したい項目を入力\n [ \"date\" , \"subject\" , \"minutes\" , \"memo\" ] : ");
            field = sc.nextLine();
            if((field.equals("subject")) || (field.equals("date")) 
                || (field.equals("minutes")) || (field.equals("memo"))){
                break;
            }
            System.out.println("入力値が不正です。もう一度入力してください。");
        }
        
        //検索ワード入力
        String value = iv.inputString("検索ワード入力　：　");
        list = service.findByCondition(field,value);
        
        //検索結果表示
        if(!(list.isEmpty())){
            for(StudyRecord r : list){
                System.out.println(r);
            }
        }else{
            System.out.println("該当なし");
        }

        System.out.println("----------------");

        //科目別学習時間集計表示
        Map<String,Integer> map = service.sumBySubject();

        for(String key : map.keySet()){
            System.out.println(key + " : " + map.get(key) + "分");
        }

        System.out.println("----------------");

        sc.close();        
    }
}