package analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

class User {
    static String input(){
        String input = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
        } catch (IOException io){
            System.out.println(io.getMessage());
        }
        return input;
    }


    static int intInput(){
        int number = 99;
        Scanner s = new Scanner(System.in);
        try {
            number = s.nextInt();
        } catch (InputMismatchException ime){
            number = intInput();
        }

        return number;
    }
}
