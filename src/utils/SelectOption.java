
package utils;

import java.util.Scanner;

/*
 * SelectOption is a class with convenience methods for getting user
 * responses from console input
 * @Author Quinn Detweiler
 */
public class SelectOption {
    
    private static Scanner keyboard = new Scanner(System.in);//for console input
    
    /**
     * getYesOrNo returns true if the user response to the prompt with 'yes'
     * or false if the user responds with 'no'.  If the user simply presses
     * enter, the default option specified as a parameter is selected
     * @param prompt - question to prompt user with
     * @param defaultOption - default response to question
     * @return 
     */
    public static boolean yesNoOption(String prompt, boolean defaultOption){
        
        boolean response = false;
        
        //do the validation
        String input;
        boolean validResponse = false;
        
        System.out.print(prompt + " (y/n)?");
        String defaultOptionString = (defaultOption) ? "y" : "n";
        System.out.print("  [" + defaultOptionString + "]  ");
        
        do{
            input = keyboard.nextLine();
            switch (input) {
                case "":
                    response = defaultOption;
                    validResponse = true;
                    break;
                case "y":
                    response = true;
                    validResponse = true;
                    break;
                case "n":
                    response = false;
                    validResponse = true;
                    break;
                default:
                    System.out.print("Invalid choice. Select 'y' or 'n'");
                    break;
            }
            
        } while (!validResponse);
        
        return response;
    }
    
    
    /**
     * getOption presents the user with a choice from a set of
     * options and ensures that they choose a valid option.
     * @param prompt - string to use for prompt
     * @param opts - array of string options
     * @param defaultOption - character that represents the default option
     * @pre the first letter of each option string is unique
     * @pre the defaultOption character is the first character of one of the options
     * @post the response character is in upper case
     * 
     * @return - first character of matching option (to upper case)
     */
    public static char variableOption(String prompt, String[] opts,
            char defaultOption){
        
        //do the validation
        String input;
        char response = Character.toUpperCase(defaultOption);//this must be reset later
        boolean validResponse = false;
        
        System.out.print(CFormat.NL + prompt + "  [" + defaultOption + "]");
        printOptions(opts);
        
        do{
            input = keyboard.nextLine().trim().toUpperCase();
            
            //if default is selected, return default
            if(input.equals("")){
                return response;
            }

            //if length is not 1, this is invalid input
            if(input.length() != 1){
                validResponse = false;
            } else {

                //search for a matching character and store in 'response' if found
                for(int i = 0; i < opts.length; i++){
                    if(opts[i].toUpperCase().charAt(0)==input.charAt(0)){
                        response = Character.toUpperCase(opts[i].charAt(0));
                        validResponse = true;
                    }
                }
            }

            //if invalid, repeat process
            if(!validResponse){
                System.out.println("Invalid Entry.  "
                        + "Please choose from the following:");
                printOptions(opts);
            }
            
        }while(!validResponse);
        
        return response;
    }
    
    /**
     * printOptions prints out an options list at the end of the current line
     * @param opts 
     */
    private static void printOptions(String[] opts){
        
       System.out.print(CFormat.NL);
        for(int i = 0; i < opts.length; i++){
            System.out.print(CFormat.NL + CFormat.addIndent(opts[i], 1));
        }
        System.out.println(CFormat.NL);
    }
    
}
