package analyzer;
import java.util.concurrent.TimeUnit;

class UI {

    static void draw(){
        Analyzer analyze = new Analyzer();
        boolean running = true;
        while (running){
            System.out.println("1: Run the analyzer");
            System.out.println("9: Exit");
            int choice = User.intInput();
            switch (choice){
                // Run the analyzer
                case 1:
                    System.out.println("To use the language analyzer, input a string and press enter.");
                    String userText = User.input();
                    System.out.println();
                    System.out.println("Analyzing the text!");
                    analyze.run(userText);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                    // Stop the loop
                case 9:
                    running = false;
                    System.out.println("I hope you found the answer.");
                    break;
                    // Ask for a new input
                    default:
                        System.out.println("No choice found, please enter a new choice.");
                        break;
            }


        }
    }

}
