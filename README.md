# java-study-log  
  
## このリポジトリについて  
2026/03/14、Oracle Certified Associate, Java SE 17 Programmer I  
を取得したが、勤務先等でJavaを使う機会がない。  
このままではスキルを活かすことができず、資格取得が無駄になる。  
そこで、資格取得を通じて得た知識を活用したアプリを制作することで、  
スキルアップを図ることとした。  
補足  
本アプリはChatGPTのアドバイスを参考にしながら開発する。  
ただし、コードの理解・実装は自分で行うことを前提とする。  
  
- アプリ概要  
  
CSV対応・学習ログ管理ツール（CUIベース）  
- 実装予定機能  
学習記録の登録（CSVへ保存）  
 　・日付  
 　・科目（Java / SQL / その他）  
 　・学習時間（分）  
 　・メモ  
一覧表示（CSVから読み込み）  
検索機能  
 　・科目で絞り込み  
 　・日付で絞り込み  
集計機能  
・科目ごとの合計学習時間
-技術制約  
・Java標準APIのみ使用  
・データベースは使わずCSVファイルで管理  
・まずはCUIで実装（GUIやWeb化は後回し）  
- 設計方針  
クラス分割を意識する  
 　・StudyRecord（データクラス）  
 　・CsvService（ファイル操作）  
 　・StudyService（ロジック）  
 　・Main（実行・メニュー）  
  
# STEP1  
- 構成を作成⇒各ファイルをコーディング⇒学習記録をCSVに1件保存する機能を実装  
  
java-study-log/  
 ├ src/  
 │   ├ Main.java  
 │   ├ StudyRecord.java  
 │   └ CsvService.java  
 └ data/  
     └ study_log.csv（自動生成）  
  
