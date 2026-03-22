import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
//import java.io.FileWriter;        //FileWriterは文字コードを指定できない（環境依存）
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class CsvService{
    private static final String FILE_PATH = "data/study_log_02.csv";

    public void save(StudyRecord record){

        File file = new File(FILE_PATH);
        boolean isNotFile = !file.exists();     //CSVファイルの有無をチェック

        try (BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(FILE_PATH,true), StandardCharsets.UTF_8))){
            /*
                CSVファイルの文字化け対策。UTF-8で安全に高速にファイルへ追記する処理に修正。
                -FileOutputStream:ファイルが存在する場合、ファイルを開く。存在しない場合、ファイルを新規作成。
                    ファイルにバイトを書き込む。
                -OutputStreamWriter:UTF-8変換
                -BufferedWriter:まとめて書く。そうすることで高速処理を行う。
            */


            //ファイルが存在しない場合、一行目にヘッダ挿入
            if(isNotFile){
                bw.write("date,subject,minutes,memo \n");
            }

            bw.write(record.toCsv());        //CSVに文字列を一行追加。
            bw.newLine();

            //↓文字化け検証
            System.out.println(java.nio.charset.Charset.defaultCharset());
            bw.write("あいうえお");
            bw.newLine();

            System.out.println("保存しました");

        }catch(IOException e) {
            System.out.println("ファイル書き込みエラー");
            e.printStackTrace();        //エラー詳細を出す
        }
    }
}