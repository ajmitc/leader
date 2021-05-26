package leader.clview;

import leader.Model;

import java.util.Scanner;

public class View {
    private Model model;
    private Scanner scanner;

    public View(Model model){
        this.model = model;

        scanner = new Scanner(System.in);
    }

    public void println(String text){
        System.out.println(text);
    }

    public void print(String text){
        System.out.print(text);
    }

    public String getUserInput(){
        return scanner.nextLine();
    }
}
