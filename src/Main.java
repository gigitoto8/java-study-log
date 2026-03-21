public class Main{
    public static void main(String[] args) {
        StudyRecord record = new StudyRecord(
            "2026-03-18", "java", 120, "CSV出力テスト");
        CsvService service = new CsvService();
        service.save(record);    
        }
}