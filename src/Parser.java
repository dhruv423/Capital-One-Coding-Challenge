import java.io.*;

public class Parser {
    public static void main(String args []) {
        String line = "";
        int TODO = 0;
        int numberOfCommentLines = 0;
        int totalLines = 0;
        int numberOfSingleLine = 0;
        int commentInBlock = 0;
        int blockComment = 0;

        try {
            // Read each line of the file
            BufferedReader reader = new BufferedReader(new FileReader("comments.java"));
            while ((line = reader.readLine()) != null) {

                // Starting with multi line comment /*
                if (line.contains("/*")) {
                    commentInBlock++;
                    numberOfCommentLines++;
                    if (line.contains("TODO")) {
                        TODO++;
                    }
                    // One line comment with /* */
                    if (line.endsWith("*/")) {
                        break;
                    }
                    // Since multi line comment find the end of multi line comments
                    while (!(line = reader.readLine()).contains("*/") ) {
                        totalLines++;
                        numberOfCommentLines++;
                        commentInBlock++;
                    }
                    blockComment++;
                }
                // Single Line Comment
                else if (line.contains("//")) {
                    if (line.contains("TODO")) {
                        TODO++;
                    }
                    totalLines++;
                    numberOfCommentLines++;
                    numberOfSingleLine++;
                }
                totalLines++;


            }
            reader.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Total # of Lines  = " + totalLines);
        System.out.println("Total # of Comment Lines= " + numberOfCommentLines);
        System.out.println("Total # of Single line Comments= " + numberOfSingleLine );
        System.out.println("Total # of Comment lines with Block Comments = " + commentInBlock );

        System.out.println("Total # of Block line Comments = " + blockComment);

        System.out.println("No of TODO's  = " + TODO);
    }
}
