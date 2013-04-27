
package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

/**
 * CFormat is a utility which provides methods for formatting console output
 * @author Quinn
 */
public class CFormat {
    
    //========================  Constants  =========================//
    public static final String INDENT = "   ";
    public static final String ASTERISK_DIVIDER = 
            "***************************************************";
    public static final String TILDE_DIVIDER = 
            "~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String DASH_DIVIDER = 
            "-----------------------------------------";
    public static final String NL = System.lineSeparator();

    /**
     * indent creates a string with whitespace characters for
     * the number of indents specified in the parameter
     * @param indent number of indents to include in returned string
     * @return string of concatenated whitespace indent characters
     */
    public static String indent(int indent){
        
        StringBuilder ind = new StringBuilder();
        for(int i = 0; i < indent; i++){
            ind.append(INDENT);
        }
        return ind.toString();
        
    }
    
    
    /**
     * concatSequence takes a string and concatenates it with itself
     * the number of times specified to produce a repeating sequence
     * of characters
     * @param repeater string to repeat
     * @param numReps number of times to repeat string
     * @return 
     */
    public static String concatSequence(String repeater, int numReps){
        
        StringBuilder msg = new StringBuilder(repeater);
        for(int i = 0; i < numReps-1; i++){
            msg.append(repeater);
        }
        return msg.toString();
        
    }
    
    /**
     * getLines takes a string and splits it into lines of the maximum
     * length specified
     * @param lineLength maximum number of characters to put on a line
     * @param message message to split into lines
     * @return 
     */
    private static String[] getLines(int lineLength, String message){

        //=====================  Local Data  ======================//
        int numLines = (message.length() / lineLength) + 1;
        int initialLength = message.length();
        String[] lines = new String[numLines];

        //split message into words and find number of words with total
        //length just under the initialLength divided by the number of lines
        String[] words = message.split(" ");
        int word = 0;//current word
        
        //create lines of as even a length as possible
        for(int i = 0; i < numLines-1; i++){
            lines[i] = "";
            while(lines[i].length() < (initialLength / numLines)){
                lines[i] += words[word++] + " ";
            }
        }
        lines[lines.length-1] = "";
        for(;word < words.length; word++){
            lines[lines.length-1] += words[word] + " ";
        }
        
        return lines;
    }
    
    /**
     * titleBoxLine formats a string of text as a line in a titleBox
     * @param line
     * @param lineLength
     * @return 
     */
    private static String titleBoxLine(String line, int lineLength){
        
            StringBuilder formattedLine = new StringBuilder("*");
            int lr_space = ((lineLength - line.length()-1)/2);
            formattedLine.append(concatSequence(" ", lr_space)).append(line);
            formattedLine.append(concatSequence(" ", 
                    lineLength - formattedLine.length() - 1));
            formattedLine.append("*");
            return formattedLine.toString();
        
    }
    
    /**
     * This version of titleBox creates a titlebox with a width of 80
     * characters
     * @param message
     * @return 
     */
    public static String titleBox(String message){
        return titleBox(message, 80);
    }
    
    /**
     * titleBox creates a title box with a format like the following:
     *          *******************************
     *          *        Some Message         *
     *          *******************************
     * @param message - message to put inside box
     * @param width - width of the box in characters
     * @return 
     */
    public static String titleBox(String message, int width){
        
        //=====================  Local Data  ======================//
        int BOX_WIDTH = width;     //character width of box
        int MARGIN = 4;         //character width of left and right margins
        int lineLength = (BOX_WIDTH - (MARGIN *2));
        String TOP_BOTTOM_BORDER = concatSequence("****", BOX_WIDTH / 4);
        StringBuilder msg = new StringBuilder();
        boolean lastLine = false;
        
        //add to message for each line
        while(message.length() != 0){
            int start = 0;
            int newLine = message.indexOf(NL);
            String subString;
            
            if(newLine == -1){
                subString = message.trim();
                message = "";
                lastLine = true;
            } else {
                subString = message.substring(start, newLine).trim();
                message = message.substring(newLine + NL.length());
            }

            if(!subString.equals("")){
                String[] lines = getLines(lineLength, subString);
            
                for(int i = 0; i < lines.length-1; i++){
                    msg.append(titleBoxLine(lines[i], BOX_WIDTH)).append(NL); 
                }
                msg.append(titleBoxLine(lines[lines.length-1], BOX_WIDTH));

                if(!lastLine){
                    msg.append(NL).append(titleBoxLine("", BOX_WIDTH)).append(NL);
                }else{
                    msg.append(NL);
                }
            } else {
                msg.append(titleBoxLine("", BOX_WIDTH)).append(NL);
            }
            
        }
        return new StringBuilder(TOP_BOTTOM_BORDER).append(NL).append(msg)
                .append(TOP_BOTTOM_BORDER).append(NL).toString();
    }
    
    
    /**
     * addIndent takes a string with newline characters and adds the specified
     * number of indentations 
     * @param origString - original string to add indent to
     * @param indent - number of indents to make
     * @return 
     */
    public static String addIndent(String origString, int indent){
        
        String fIndent = indent(indent); //gets the string with the full
                                         //amount of indents
        
        String[] tokens = origString.split(NL);
        
        
        StringBuilder msg = new StringBuilder();
        msg.append(fIndent); //start with indent
        
        //add indent after every token except the last one
        for(int i = 0; i < tokens.length-1; i++){
            
            msg.append(tokens[i]).append(NL);
            msg.append(fIndent);
            
        }
        
        msg.append(tokens[tokens.length-1]);
        
        return msg.toString();
        
    }
    
    /**
     * head formats the input string as top-level header
     * @param originalString
     * @return 
     */
    public static String head(String originalString){
        
        return NL + originalString;
        
    }
    
    /**
     * subHead formats the input string as a sub-header
     * @param originalString
     * @return 
     */
    public static String subHead(String originalString){
        
        return new StringBuilder().append(NL).append(INDENT).append("+ ")
                .append(originalString).toString();
        
    }
    
    /**
     * This item() method formats the string as an item at the level
     * specified by the input int (level = number of indents)
     * @param originalString
     * @param level
     * @return 
     */
    public static String item(String originalString, int level){
        
        String fIndent = indent(level); //gets full indent
        
        String[] tokens = originalString.split(NL);
        
        //remove leading blank space
        while(tokens[0].equals("")){
            String[] temp = new String[tokens.length -1];
            for(int i = 1; i < tokens.length; i++){
                temp[i-1] = tokens[i];
            }
            tokens = temp;
        }
        
        StringBuilder msg = new StringBuilder();
        msg.append(NL).append(fIndent).append("- ").append(tokens[0]);
        
        for(int i = 1; i < tokens.length; i++){
            
            msg.append(NL).append(fIndent).append("  ").append(tokens[i]);

        }
        return msg.toString();
        
    }
    
    /**
     * the main method in this class is for testing purposes only and should
     * remain commented out unless in use for testing
     * @param args 
//     */
//    public static void main(String[] args){
//        
//        System.out.println(titleBox("Some Message That is too long to fit on one line that will"
//                + " now be added to multiple lines. " + CFormat.NL + CFormat.NL + "  It might " 
//                + CFormat.NL + " even need" + CFormat.NL + " " + CFormat.NL 
//                + " to be on more than 2."));
//        
//    }
    
    /**
     * millisToDateTime converts a date represented in milliseconds after
     * the epoch to a String in the format YYYY-MM-dd_HH-mm
     * @param millis
     * @return 
     */
    public static String millisToDateTime(long millis){
        
        
        DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd_HH-mm");
        String date = formatter.format(new Date(millis));
        return date;
        
        
    }
    
    /**
     * millisToDate converts a date represented in milliseconds after the
     * epoch to a String in the format yyyy-mm-dd
     * @param millis date in milliseconds from the epoch
     * @return 
     */
    public static String millisToDate(long millis){
        
        DateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String date = formatter.format(new Date(millis));
        return date;
        
    }
    
    /**
     * toTable takes a two-dimensional array (in list form) of strings
     * and formats these as a table.  Each inner-array represents
     * a row in the table, with the first inner-array representing the
     * table headings
     * @param array
     * @return 
     */
    public static String toTable(ArrayList<ArrayList<String>> array){
        
        //=====================  Local Data  ======================//
        final int COL_DIVIDE_WIDTH = 8;
        StringBuilder msg = new StringBuilder();
        
        //create array to hold column formatting information
        String[] colFormats = new String[array.get(0).size()];
        int[] maxLengths = new int[array.get(0).size()];
        
        //initialize maxLengths
        for(int i = 0; i < maxLengths.length; i++){
            maxLengths[i] = 0;
        }
        
        //find length of longest string in each column and set formatting
        //information accordingly
        for(List<String> row : array){
            for(int col = 0; col < array.get(0).size(); col++){
                int length = row.get(col).length();
                if(maxLengths[col] <= length){
                    maxLengths[col] = length;
                    colFormats[col] = "%-" + (length + COL_DIVIDE_WIDTH) + "s";
                }
            }        
        }
        
        //initialize message
        msg.append(CFormat.NL).append(CFormat.NL);
        
        //create set of dashes and insert into array
        ArrayList<String> dashes = new ArrayList<>();
        for(int i = 0; i < array.get(0).size(); i++){
            dashes.add(addDashes("", maxLengths[i]));
        }
        array.add(1, dashes);
        
        //add each row to the msg
        StringBuilder sb = new StringBuilder();
        Formatter frm = new Formatter(sb);
        for(int row = 0; row < array.size(); row++){
            //System.out.println(array.get(0).size());
            for(int col = 0; col < array.get(0).size(); col++){
                frm.format(colFormats[col], array.get(row).get(col));
            }
            sb.append(CFormat.NL);
        }
        
        msg.append(sb.toString());
        return msg.toString();
        
    }
    
    /**
     * addSpaces adds the number of spaces passed as a parameter to the end
     * of the string passed as a parameter and returns the result
     * @param numSpaces - number of spaces to add
     * @param originalString - string to add spaces to
     * @return 
     */
    public static String addSpaces(String originalString, int numSpaces){
        
        if(numSpaces < 0){
            return originalString;
        }
        
        StringBuilder sb = new StringBuilder
                (originalString.length() + numSpaces + 3);
        
        sb.append(originalString);
        for(int i = 0; i < numSpaces; i++){
            sb.append(' ');
        }
        
        return sb.toString();
    }
    
    /**
     * addDashes adds the number of dashes passed as a parameter to the end
     * of the string passed as a parameter and returns the result
     * @param originalString - string to add dashes to
     * @param numDashes - number of dashes to add
     * @return 
     */
    private static String addDashes(String originalString, int numDashes){
        
        StringBuilder sb = new StringBuilder
                (originalString.length() + numDashes + 3);
        sb.append(originalString);
        for(int i =0; i < numDashes; i++){
            sb.append("-");
        }
        return sb.toString();
    }
    
}
