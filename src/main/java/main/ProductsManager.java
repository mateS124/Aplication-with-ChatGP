package main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ProductsManager {
   private final Path productsPath;
    public ProductsManager() throws URISyntaxException {
        ClassLoader classLoader = ProductsManager.class.getClassLoader();
        productsPath = Paths.get(Objects.requireNonNull(classLoader.getResource("products.txt")).toURI());
    }

    public List<String> getAllProducts() throws IOException {


       return Files.readAllLines(productsPath, StandardCharsets.UTF_8);

    }

    public void addProduct(String product) throws IOException {
         HashSet<String> products = new HashSet<>(getAllProducts());

         if (!products.contains(product)){
             Files.writeString(productsPath , System.lineSeparator() + product, StandardOpenOption.APPEND);

         } else {
             System.out.println("Masz już taki produkt");
         }

    }

    public void delteProduct(String product) throws IOException{
        HashSet<String> products = new HashSet<>(getAllProducts());

        if (!products.contains(product)){
            products.remove(product);
            String productToSave = String.join(System.lineSeparator(), product);
            Files.writeString(productsPath, productToSave);

        } else {
            System.out.println("Nie masz takiego produktu w lodówce");
        }
    }

}
