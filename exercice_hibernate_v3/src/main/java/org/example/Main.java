package org.example;

import org.example.entities.Produit;
import org.example.services.ProduitService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {


//        // Exercice 1
//
//        // Creation des produits
        ProduitService ps = new ProduitService();
//        ps.create(new Produit("TOSHIBA","zzaa123",new Date("2016/01/08"),6000,150));
//        ps.create(new Produit("HP","EER678",new Date("2016/02/09"),2000,100));
//        ps.create(new Produit("SONY","AQWZSX",new Date("2016/09/23"),6000,75));
//        ps.create(new Produit("DELL","AZERTY",new Date("2016/02/12"),6000,50));
//        ps.create(new Produit("SONY","qsdERT",new Date("2016/02/02"),6000,125));
//
//        // Informations produit id = 2
//
//        Produit p = ps.findById(2);
//        System.out.println(p);
//
//        // Supprimer le produit id = 3
//        ps.delete(ps.findById(3));
//
//        // Modifier produit id = 1
//
//        p = ps.findById(1);
//        if(p != null){
//            p.setMarque("HP");
//            p.setReference("MMMMPPP");
//            p.setDateAchat(new Date("2015/09/08"));
//            p.setPrix(5000);
//            ps.update(p);
//        }

        List<Produit> produitList = ps.getAllProduct();
        for(Produit p : produitList){
            System.out.println(p);
        }

        System.out.println("********************");

        List<Produit> produitList1 = ps.getProductOverPrice(3000);
        for(Produit p : produitList1){
            System.out.println(p);
        }

        System.out.println("********************");
        System.out.println("Veuillez saisir la première date sous le format yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        String date1 = scanner.next();
        System.out.println("Veuillez saisir la deuxieme date sous le format yyyy-MM-dd");
        Scanner scanner1 = new Scanner(System.in);
        String date2 = scanner1.next();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(date1);
        Date date3 = dateFormat.parse(date2);

        List<Produit> produitList2 = ps.getProductBetweenDate(date,date3);
        for(Produit p : produitList2){
            System.out.println(p);
        }

        System.out.println("********************");
        System.out.println("Veuillez saisir une valeur de stock ");
        Scanner scanner3 = new Scanner(System.in);
        int stock = scanner3.nextInt();
        scanner3.nextLine();
        List<Produit> produitList3 = ps.getProductByStock(stock);
        for(Produit p : produitList3){
            System.out.println("ID :" + p.getId() + " reference : " + p.getReference());
        }

    }
}