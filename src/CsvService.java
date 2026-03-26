//ファイル操作
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
//import java.io.FileWriter;        //FileWriterは文字コードを指定できない（環境依存）
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//文字化け
import java.nio.charset.StandardCharsets;

//リスト
import java.util.ArrayList;
import java.util.List;

public class CsvService{
    private static final String FILE_PATH = "data/study_log_02.csv";

    //ファイル保存
    public void save(StudyRecord record){

        File file = new File(FILE_PATH);
        //CSVファイルの有無をチェック
        boolean isNotFile = !file.exists();     

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

            //CSVに文字列を一行追加。
            bw.write(record.toCsv());        
            bw.newLine();

            System.out.println("保存しました");

        }catch(IOException e) {
            System.out.println("ファイル書き込みエラー");
            //エラー詳細を出す
            e.printStackTrace();        
        }
    }

    //ファイル読み込み
    public List<StudyRecord> findAll(){

        //リスト（読み込みデータを入れる箱）準備
        List<StudyRecord> list = new ArrayList<>(); 

        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(FILE_PATH),
                StandardCharsets.UTF_8))){
            /*
                -FileInputStream:バイト読み込み
                -InputStreamReader:UTF-8で文字に変換
                -BufferedReader:一行単位で読む    
            */        
            
            String line;

            //ファイルの終わりまで一行ずつ読む。
            while((line = br.readLine()) != null){

                //1行目（カラム名）を無視
                if(line.startsWith("date")){
                    continue;
                }

                //","区切りで文字列を分解
                String[] data = line.split(",");

                String date = data[0];
                String subject = data[1];
                int minutes = Integer.parseInt(data[2]);
                String memo = data[3];

                //オブジェクト化
                StudyRecord record = new StudyRecord(date, subject, minutes, memo);
                //listに追加
                list.add(record);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    //検索（科目のみ）
    /*
    public List<StudyRecord> findBySubject(String targetSubject) {
        
        List<StudyRecord> all = findAll();
        List<StudyRecord> result = new ArrayList<>();
        
        for (StudyRecord r : all) {
            if (r.getSubject().equals(targetSubject)) {
                result.add(r);
            }
        }
        
        return result;
    }
    */

    //検索（日付、科目、メモ）
    public List<StudyRecord> findByCondition(String field,String value) {
        
        List<StudyRecord> all = findAll();
        List<StudyRecord> result = new ArrayList<>();
        
        for (StudyRecord r : all) {
            if (field.equals("date") && r.getDate().equals(value)) {
                result.add(r);
            }else if (field.equals("subject") && r.getSubject().equals(value)) {
                result.add(r);
            }else if (field.equals("memo") && r.getMemo().contains(value)) {
                result.add(r);
            }else{
                
            }
        }
        
        return result;
    }
    
}