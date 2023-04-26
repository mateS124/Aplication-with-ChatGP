package main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ChatGPTHelper chatGPTHelper = new ChatGPTHelper();
        ProductsManager productsManager = new ProductsManager();


        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("1. Pokarz produkty");
            System.out.println("2. Dodaj produkt");
            System.out.println("3. Usuń produkt");
            System.out.println("4. Daj mi yrzy pomysły na smaczne sniadanie");
            System.out.println("5. Daj mi trzy pomysły na pyszny obiad");
            System.out.println("6. Poleć mi zdrowe produkty, którę moge kupić");
            System.out.println("7. Koniec");
            System.out.println();
            System.out.println("Wybierz opcję: ");


            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1 -> {
                    System.out.println("Produkty: ");
                    productsManager.getAllProducts().forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Jaki produkt chcesz dodać?");
                    String product = scanner.nextLine();
                    productsManager.addProduct(product);

                }
                case 3 -> {
                    System.out.println("jaki produkt chcesz usunąć?");
                    String product = scanner.nextLine();
                    productsManager.delteProduct(product);
                }
                case 4 -> {
                    System.out.println("Pomysł na śniadanie od ChatGP:");
                    String breakfastIdea =  chatGPTHelper.getBreakfastIdea(productsManager.getAllProducts());

                    System.out.println(breakfastIdea);
                }
                case 5 -> {
                    System.out.println("Pomysł na obiad od ChatGP:");
                    String lunchIdea =  chatGPTHelper.getLunchIdea(productsManager.getAllProducts());

                    System.out.println(lunchIdea);
                }
                case 6 -> {
                    System.out.println("Zdrowe produkty, które poleca Chat GP:");
                    String recomendation = chatGPTHelper.getHealthyProductRecomendation(productsManager.getAllProducts());
                    System.out.println(recomendation);

                }
                case 7 -> {
                    System.out.println("Dziękuję dozobaczenia!");
                    System.exit(0);
                }

            }
        }
    }
}
