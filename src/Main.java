import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws TooManyBombsException {



        Game g = new Game(10, 10, 2);

        g.start();
        g.displayFrame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter something:");

        String input = scanner.nextLine();  // Read user input
        System.out.println("Input is: " + input);
        try{
        }catch  (Exception e){
            System.out.println(e);


        }
    }

}





























