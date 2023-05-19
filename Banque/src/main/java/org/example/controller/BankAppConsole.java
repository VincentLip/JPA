package org.example.controller;

import org.example.impl.AccountDAOImpl;
import org.example.impl.AgencyDAOImpl;
import org.example.impl.CustomerDAOImpl;
import org.example.model.Account;
import org.example.model.Agency;
import org.example.model.Customer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class BankAppConsole {

    private static EntityManagerFactory entityManagerFactory;

    private static AccountDAOImpl accountDAO;
    private static CustomerDAOImpl customerDAO;
    private static AgencyDAOImpl agencyDAO;

    public static void main() {
        entityManagerFactory = Persistence.createEntityManagerFactory("todolist");
        accountDAO = new AccountDAOImpl(entityManagerFactory);
        customerDAO = new CustomerDAOImpl(entityManagerFactory);
        agencyDAO = new AgencyDAOImpl(entityManagerFactory);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("#### To Do List ####");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Ajouter un compte");
            System.out.println("3. Ajouter une agence");
            System.out.println("0. Quitter l'application");
            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice){
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    addAccount(scanner);
                    break;
                case 3:
                    addAgency(scanner);
                    break;
                case 0:
                    System.out.println("Bye");
                    entityManagerFactory.close();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

        }while (choice != 0);
    }

    private static void addCustomer(Scanner scanner){
        System.out.println("Entrer le nom du client : ");
        String lastName = scanner.nextLine();
        System.out.println("Entrer le prénom du client : ");
        String firstName = scanner.nextLine();
        System.out.println("Date de naissance : (dd.MM.yyyy)");
        String birthDate = scanner.nextLine();

        LocalDate birth = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        Customer customer = new Customer(lastName,firstName,birth);
        customerDAO.addCustomer(customer);
        System.out.println("Client ajouté");
    }

    private static void addAgency(Scanner scanner){
        System.out.println("Entrer le nom de l'agence : ");
        String agencyName = scanner.nextLine();

        Agency agency = new Agency(agencyName);
        agencyDAO.addAgency(agency);
        System.out.println("Agence ajoutée");
    }

    private static void addAccount(Scanner scanner){
        System.out.println("Entrer le libelle du compte : ");
        String libelleName = scanner.nextLine();
        System.out.println("Entrer l'IBAN du compte : ");
        String iban = scanner.nextLine();
        System.out.println("Entrer le sold du compte : ");
        float sold = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Entrez l'ID de l'agence pour ce compte : ");
        Long agencyId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Entrez l'ID du client pour ce compte : ");
        Long customerId = scanner.nextLong();
        scanner.nextLine();


        Account account = new Account(libelleName,iban,sold);
        accountDAO.addAccount(account);

        if(accountDAO.addAccount(account,agencyId,customerId)){
            System.out.println("Compte ajouté avec succès !");
        }else {
            System.out.println("Erreur");
        }

    }
}
