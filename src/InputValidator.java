import java.util.Scanner;
import java.lang.Integer;

public class InputValidator{
    //入力元（キーボード）を使い回すための箱
    private Scanner sc;
    //入力値受取変数
    String input;

    public InputValidator(Scanner sc){
        this.sc = sc;
    }

    //文字列チェック
    public String inputString(String message){

        while(true){
            System.out.print(message);
            input = sc.nextLine();
            //空文字チェック
            if(input.trim().isEmpty()){
                System.out.println("空入力は無効です。再入力してください。");
            }else{
                return input;
            }
        }
    }
    
    //整数チェック
    public int inputInt(String message){

        while(true){
            System.out.print(message);
            input = sc.nextLine();
            //空文字チェック
            if(input.trim().isEmpty()){
                System.out.println("空入力は無効です。再入力してください。");
                continue;
            }
            
            try{
                return Integer.parseInt(input);
            }catch(NumberFormatException e){
                System.out.println("整数を入力してください。");
            }
        }
    }
}