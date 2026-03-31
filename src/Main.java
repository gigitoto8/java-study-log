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

        System.out.println("\n\n\n");
        System.out.println("----------------------------------------");
        //
        while(true){

            System.out.println("   #####################\n" +
                               "   #####学習ログ管理#####\n" +
                               "   #####################\n\n");

            System.out.println("項目を番号で選択");
            System.out.println("----------------------------------------");
            System.out.println("1. 登録");
            System.out.println("2. 一覧");
            System.out.println("3. 検索");
            System.out.println("4. 集計");
            System.out.println("5. 終了");
            System.out.println("----------------------------------------\n");

            switch(iv.inputString("番号 : ")){

                case "1":
                    System.out.println("\n\n");
                    //データ入力登録
                    String date = iv.inputString("日付を入力 : ");
                    String subject = iv.inputString("科目を入力 : ");
                    int minutes = iv.inputInt("学習時間を入力 : ");
                    String memo = iv.inputString("メモを入力 : ");
                    //データ登録
                    StudyRecord record = new StudyRecord(
                        date,subject, minutes, memo);
                        service.save(record);
                        break;
                        
                case "2":                    
                    System.out.println("\n\n");
                    //CSVから読み込んだデータをList形式に変換
                    List<StudyRecord> list = service.findAll();
                    //listに格納されているデータを１件ずつ表示
                    for(StudyRecord r : list){
                        System.out.println(r);
                    }
                    break;

                case "3":                    
                    System.out.println("\n\n");
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
                    break;

                case "4":            
                    System.out.println("\n\n");
                    //科目別学習時間集計表示
                    Map<String,Integer> map = service.sumBySubject();
                    for(String key : map.keySet()){
                        System.out.println(key + " : " + map.get(key) + "分");
                    }
                    break;

                case "5":            
                    System.out.println("\n\n");
                    System.out.println("終了します");
                    sc.close();        
                    System.out.println("----------------------------------------");
                    return;

                default:            
                    System.out.println("\n\n");
                    System.out.println("入力値が不適切です");
            }
        }
    }
}