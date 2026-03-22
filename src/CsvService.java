import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvService{
    private static final String FILE_PATH = "data/study_log.csv";

    public void save(StudyRecord record){

        File file = new File(FILE_PATH);
        boolean isNotFile = !file.exists();     //CSVファイルの有無をチェック

        try (FileWriter fw = new FileWriter(FILE_PATH,true)){
            //ファイルが存在する場合、ファイルを開く。存在しない場合、ファイルを新規作成。

            //ファイルが存在しない場合、一行目にヘッダ挿入
            if(isNotFile){
                fw.write("date,subject,minutes,memo \n");
            }
            fw.write(record.toCsv() + "\n");        //CSVに文字列を一行追加。
            System.out.println("保存しました");
        }catch(IOException e) {
            System.out.println("ファイル書き込みエラー");
            e.printStackTrace();        //エラー詳細を出す
        }
    }
}