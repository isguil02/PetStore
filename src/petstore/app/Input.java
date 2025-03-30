package petstore.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Input {
    public static Scanner sc = new Scanner(System.in);

    public static String getLine(String prompt) {
		
		System.out.print(prompt);
		return Input.sc.nextLine();
		
	} // end of getLine

    public static String getString(String prompt) {

        String userInput;

        System.out.print(prompt);

        while (true) {
            userInput = Input.sc.nextLine();

            userInput = userInput.trim();

            if (userInput.isEmpty()){
                System.out.print("Invalid input! Please enter a value: ");
            } else {
                break;
            }
        }

        return userInput;

    } // end of getLine

    public static int getInt(String prompt) {
    	int userInput;
		
		System.out.print(prompt);

        // if the data in the buffer is a valid integer
        // then break out of the validation loop
        while (!Input.sc.hasNextInt()) {

            System.out.print("Invalid input! Please enter a number: ");
            Input.sc.next();  // clear the data in the input buffer

        } // end of while
			
		userInput = Input.sc.nextInt();
		Input.sc.nextLine(); // consume newline left-over
		
		return userInput;
		
	} // end of getInt
    
    public static int getIntRange(String prompt, int low, int high) {
    	int userInput;
		
		System.out.print(prompt);
		
		while (true) {
			if (Input.sc.hasNextInt()) {         // if the data in the buffer is a valid integer
				userInput = Input.sc.nextInt();  // then store the integer in userInput
				
				// if the userInput is within the valid range
				// then break out of the validation loop
				if (userInput >= low && userInput <= high) {
					break;
				} // end of if

    		} else {
    			Input.sc.next();  // clear the data in the input buffer
    		} // end of if-else

			System.out.printf("Invalid input! Please enter a number between (%d - %d): ", low, high);

		} // end of while
		
		Input.sc.nextLine(); // consume newline left-over
        return userInput;
    } // end of getIntRange

    public static String getDate(String prompt) {
        String userInput;

        System.out.print(prompt);

        while (true) {

            userInput = Input.sc.nextLine();

            try{
                LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                break;
            } catch (Exception e){
                System.out.print("Invalid input! Please enter a valid date (MM-DD-YYYY): ");
            }
        }

        return userInput;

    } // end of getInt

} // end of library.app.Input class
