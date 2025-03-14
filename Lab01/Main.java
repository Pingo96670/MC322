package Lab01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initializing variables
        String name;
        int startX, startY;
        int dX, dY;
        Scanner input = new Scanner(System.in);

        Grid grid;
        Robot rob0;

        // Create a grid object based on user input
        System.out.println("Please input the grid's x and y dimensions.");
        startX = input.nextInt();
        startY = input.nextInt();
        grid = new Grid(startX, startY);

        input.nextLine();   // Consume the line break from nextInt

        // Create a robot object based on user input
        System.out.println("Please input the first robot's name.");
        name = input.nextLine();

        System.out.printf("Please input the robot %s's starting x and y coordinates.\n", name);
        startX = input.nextInt();
        startY = input.nextInt();

        if (!grid.isWithinBounds(startX, startY)) {
            System.out.println("Position out of bounds. Exiting program.");
            input.close();
            return;
        }

        rob0 = new Robot(name, startX, startY);

        // Get a coordinate input to where the robot should be moved
        System.out.printf("Please input how much the robot %s should move.\n", rob0.getName());
        dX = input.nextInt();
        dY = input.nextInt();

        // Check if chosen coordinates are within grid bounds
        if (grid.isWithinBounds(rob0.getPosX()+dX, rob0.getPosY()+dY)) {
            rob0.move(dX, dY);
            rob0.printPos();
        } else {
            System.out.println("Position out of bounds. Exiting program.");
        }

        // Close input scanner
        input.close();
    }
}
