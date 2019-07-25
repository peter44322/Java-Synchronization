package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int connections = Integer.parseInt(scan("What is number of WI-FI Connections?",scanner));
        int clients = Integer.parseInt(scan("What is number of devices Clients want to connect?",scanner));
        scanner.close();

        Connection connection = new Connection(connections);

        Client[] clientObjects = new Client[clients];

        for (int i = 0; i < clients; i++) {
            clientObjects[i] = new Client(connection);
        }

        for (int i = 0; i < clients; i++) {
            clientObjects[i].start();
        }
    }

    public static String scan(String phrase,Scanner scanner) {
        String input;
        System.out.println(phrase + " ");
        input = scanner.nextLine();
        System.out.println("Value : "+input);
        return input;
    }
}
