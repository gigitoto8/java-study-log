# java-study-log  
  
## このリポジトリについて  
2026/03/14、Oracle Certified Associate, Java SE 17 Programmer I  
を取得したが、勤務先等でJavaを使う機会がない。  
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
現時点では、データはインスタンス生成の際に登録。
  
java-study-log/  
 ├ src/  
 │   ├ Main.java:実行  
 │   ├ StudyRecord.java:データ取扱い  
 │   └ CsvService.java:CSVファイル操作  
 └ data/  
     └ study_log.csv（自動生成）  
  
# STEP2  
- Main.javaにて、データをコンソール入力で登録する処理に修正。
- CsvCervice.javaにて、文字化け対策のため一部修正。
  
# STEP3  
- Csvデータ表示機能追加。
Main.javaにて、CSVデータ内容を表示させる処理を追加。
CsvCervice.javaにて、findAllメソッド（CSVからデータを読み込む機能）を追加。  
StudyRecord.javaにて、表示用フォーマットを定める処理を追加。  
  
# STEP4  
- 検索機能追加。  
Main.javaにて、検索項目と検索ワードをコンソール入力させる処理と、  
該当するレコードを表示させるを追加。  
なお、項目の入力値が不正である場合は再度入力させる処理、  
検索ワードに該当するものがない場合の表示を含む。  
CsvCervice.javaにて、findByConditionメソッド  
（指定項目および検索ワードを抽出する機能）を追加。  
StudyRecord.javaにて、ゲッターを追加。  
  
# STEP5  
- 科目別の学習時間集計機能を追加。  
Main.javaにて、subBySubjectメソッドで得られた結果を表示させる処理を追加。  
CsvCervice.javaにて、sumBySubjectメソッド（科目毎の総学習時間を集計する機能）を追加。  
  
他、StudyRecord.javaにて、findByConditionに学習時間を検索するコードを失念していたため追加。  
  
# STEP6  
- 入力チェック機能追加。  
入力値チェック機能を有するInputValidator.javaをsrcディレクトリに追加。  
当機能活用のため、Main.javaを修正。  
  
# STEP7  
- アプリ起動直後、以下の動作を行うよう修正。  
機能を選択する画面を表示 → 選択した機能を実行 → 選択画面に戻る（終了を選択するまで繰り返す）  
選択項目は、STEP2からSTEP5で実装した機能と、終了する処理からなる。  

