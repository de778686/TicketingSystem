
package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * variableOption presents the user with a choice from a set of
     * options and ensures that they choose a valid option.
     * @param prompt - string to use for prompt
     * @param opts - array of string options
     * @param defaultOption - character that represents the default option
     * @pre the first letter of each option string is unique
     * @pre the defaultOption character is the first character of one of the options
     * @post the response character is in upper case

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
     * complexVariableOption validates input where a user may select from a list of string
     * options where the beginning of the list holds string options that ask for integer
     * input and the end of the list holds string options that ask for char input
     * @param prompt prompt given to user
     * @param opts strings representing options
     * @param startValid the first option string that asks for char input
     * @param additionalOpts the set of integers that represent valid input for any of the initial option strings
     * @param defaultOption the default option (must be one of the char options)
     * @pre strings asking for char entry each begin with a unique character
     * @pre char passed in defaultOption is a valid response character
     * @pre startValid is an index in the range of opts
     * @post return value is a String in upper case containing either the char
     *       or integer input.  If the String represents an integer, it is an integer prefixed
     *       with the character "I", and if it represents a char, it is a char prefixed
     *       with the character "C"
     * @return valid response string with a prefix letter of "I" to indicate integer entry
     *         or "C" to indicate char entry
     */
    public static String complexVariableOption(String prompt, String[] opts, int startValid,
            Collection<Integer> additionalOpts, char defaultOption){
        
        //do the validation
        String input;
        String prefix = Character.isDigit(defaultOption) ? "I" : "C";
        String response = prefix + Character.toString(defaultOption).toUpperCase();//this must be reset later
        boolean validResponse = false;
        
        System.out.print(CFormat.NL + prompt + "  [" + defaultOption + "]");
        printOptions(opts);
        
        do{
            input = keyboard.nextLine().trim().toUpperCase();
            
            //if default is selected, return default
            if(input.equals("")){
                return response;
            }
            
            //if input is an integer, test additionalOpts for a match
            if(isInteger(input)){
                Integer i = Integer.parseInt(input);
                if(additionalOpts.contains(i)){
                    response = "I" + input;
                    validResponse = true;
                }
                
            //if input is not an integer, test against valid string options
            } else {
                for(int i = startValid; i < opts.length; i++){
                    if(opts[i].toUpperCase().charAt(0)==input.charAt(0)){
                        response = "C"+opts[i].substring(0,1).toUpperCase();
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
     * autoIntegerOption prompts users to select from a set of choices, and
     * each choice is numbered automatically.  The method loops until valid
     * input has been entered and returns an integer value paired with
     * the option string in the optionMap map
     * @param prompt - prompt string
     * @param opts - map of option descriptions (strings) onto integer indicators
     * @return 
     */
    public static int autoIntegerOption(String prompt, Map<String, Integer> optionMap){
        
        //do the validation
        String input; //string to hold input
        String[] opts = new String[optionMap.keySet().size()];
        opts = optionMap.keySet().toArray(opts);
        int response = optionMap.get(opts[0]);
        boolean validResponse = false;
        Map<Integer,String> choiceMap;
        
        System.out.print(CFormat.NL + prompt + "  [" + opts[0] + "]");
        
        choiceMap = printOptions(opts, true);
        
        do{
            input = keyboard.nextLine().trim().toUpperCase();

            //if default is selected, return default
            if(input.equals("")){
                return response;
            }
            
            //if input is an integer, see if it represents a valid choice
            if(isInteger(input)){
                Integer iValue = Integer.parseInt(input);                
                if(choiceMap.containsKey(iValue)){
                    
                    //choice is valid, return the appropriate value
                    response = optionMap.get(choiceMap.get(iValue));
                    validResponse = true;
                    
                }
            }
            
            //if invalid, repeat process
            if(!validResponse){
                System.out.println("Invalid Entry.  "
                        + "Please choose from the following:");
                printOptions(opts, true);
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
    
    /**
     * printOptions prints out an option list at the end of the current line
     * and returns null.  If the "addNumbers" flag is set, each option string is
     * prefixed with an integer, and a map of the prefix to the original string
     * is returned
     * @param opts
     * @param addNumers
     * @return 
     */
    private static Map<Integer, String> printOptions(String[] opts, boolean addNumbers){
        
        if(addNumbers){

            //map of option strings to integers
            Map<Integer, String> map = new HashMap<>();

            System.out.println(CFormat.NL);
            for(int i = 0; i < opts.length; i++){
                System.out.print(CFormat.NL + (i+1) + " - " + CFormat.addIndent(opts[i], 1));
                map.put((i+1), opts[i]);
            }
            System.out.println(CFormat.NL);

            return map;
        
        } else {
            printOptions(opts);
            return null;
        }
        
    }
    
    /**
     * isInteger tests a string to see whether or not it is an integer
     * @param string
     * @return 
     */
    private static boolean isInteger(String string){
        
        Pattern p = Pattern.compile("-?[1-9]\\d*");
        Matcher m = p.matcher(string);
        if(m.matches()){
            return true;
        } else {
            return false;
        }
        
    }
    
}
