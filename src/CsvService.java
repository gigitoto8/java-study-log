import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvService{
    private static final String FILE_PATH = "data/study_log.csv";

    public void save(StudyRecord record){

        File file = new File(FILE_PATH);
        boolean isNewFile = !file.exists();

        try (FileWriter fw = new FileWriter(FILE_PATH,true)){

            if(isNewFile){
                fw.write("date,subject,minutes,memo \n");
            }
            fw.write(record.toCsv() + "\n");
            System.out.println("保存しました");
        }catch(IOException e) {
            System.out.println("ファイル書き込みエラー");
            e.printStackTrace();
        }
    }
}