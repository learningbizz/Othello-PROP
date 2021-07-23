/**
 * @file Driver.java
 * @author Alex Rodriguez
 * @brief Driver class specification.
 */
package test.driver;

import java.util.Scanner;

import util.Pair;

/**
 * @class Driver
 * @brief Implements various utilities to create a driver application.
 * By Alex Rodriguez.
 */
public class Driver {
    /* METHODS */

    /**
     * @brief Print to standard output a menu with the list of options given and show a prompt asking to select one.
     * @pre <em>True</em>
     * @post A menu with the options specified is printed to standard output and a prompt asking to select an option is shown. It returns the identifier of the selected option or terminates the driver application if the option was "e".
     * @param title A text inserted before the printed menu.
     * @param name The name of the shown menu.
     * @param options List of options to show.
     * @return The identifier of the selected option.
     */
    public static String menu(String title, String name, Pair<String, String>... options) {
        String selected = new String();

        if (name == null)
            name = "Options";

        do {
            Driver.clear();
            if (title != null)
                System.out.println(title);
            System.out.println(String.format("==== %s ====", name));
            for (Pair<String, String> option : options)
                System.out.println(String.format("[%s]\t%s", option.first, option.second));
            System.out.println("[e]\tExit driver\n");
            selected = Driver.input("What do you want to do?");
            Driver.clear();

            for (Pair<String, String> option : options)
                if (selected.equals(option.first))
                    return selected;

        } while (!selected.equals("e") && !selected.equals("E"));

        System.exit(0);

        return null;
    }

    /**
     * @brief Pause the driver application until enter is pressed.
     * @pre <em>True</em>
     * @post The driver application is paused until enter is pressed.
     */
    public static void pause() {
        Driver.input("Press enter to continue");
    }

    /**
     * @brief Clear the console.
     * @pre <em>True</em>
     * @post The console is cleared.
     */
    public static void clear() {
        System.out.print("\033\143");
    }

    /**
     * @brief Prompt the user and return the entered value as String.
     * @pre <em>True</em>
     * @post A prompt is shown waiting for user input from stdin.
     * @param prompt The text of the shown prompt.
     * @return The entered value as String.
     */
    public static String input(String prompt) {
        String in = new String();
        System.out.print(String.format(">> %s: ", prompt));
        Scanner stdin = new Scanner(System.in);

        try {
            in = stdin.nextLine();
        } catch (Exception e) {
            stdin.close();
        }

        return in;
    }

    /**
     * @brief Prompt the user and return the entered value as Integer.
     * @pre <em>True</em>
     * @post A prompt is shown waiting for user input from stdin.
     * @param prompt The text of the shown prompt.
     * @return The entered value as Integer.
     */
    public static Integer inputInt(String prompt) {
        boolean trick = true; // Necessary or Java won't compile...
        do {
            try {
                return Integer.parseInt(Driver.input(prompt));
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer!");
            }
        } while (trick);

        return 0;
    }

    /**
     * @brief Prompt the user and return the entered value as boolean.
     * @pre <em>True</em>
     * @post A prompt is shown waiting for user input from stdin.
     * @param prompt The text of the shown prompt.
     * @return The entered value as boolean.
     */
    public static boolean inputBool(String prompt) {
        boolean trick = true; // Necessary or Java won't compile...
        String in = new String();
        do {
            in = Driver.input(String.format("%s [y/n]", prompt));
            if (in.toLowerCase().equals("yes") || in.toLowerCase().equals("y"))
                return true;
            else if (in.toLowerCase().equals("no") || in.toLowerCase().equals("n"))
                return false;
            System.out.println("That is not a yes or no!");
        } while (trick);

        return false;
    }
}
