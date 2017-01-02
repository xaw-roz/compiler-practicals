import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.Iterator;

/**
 * Created by rocks on 12/3/2016.
 */
public class HtmlGenerator {
   /* String csvFile = "/Users/mkyong/csv/country.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

        try {

        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] country = line.split(cvsSplitBy);

            System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

        }

Workbook workbook = getWorkbook(inputStream, excelFilePath)    }
    catch(Exception e)
    {
        e.printStackTrace();;
    }*/

    public static void main(String[] args) {


        String filepath = "C:\\Users\\rocks\\Desktop\\rituraj sir ko kam\\file.xls";

        Workbook wb;


        try {
            FileInputStream fs = new FileInputStream(filepath);
            FileInputStream inputStream = new FileInputStream(new File(filepath));


            Workbook workbook = new HSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();
            iterator.next();
            String html="<table class=\"student_info\" border=\"0\" width=\"100%\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td rowspan=\"10\" valign=\"top\" width=\"21%\">\n" +
                    "\n" +
                    "<a href=\"http://doko.dwit.edu.np/wp-content/uploads/2016/12/";

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();




                Double roll1=nextRow.getCell(0).getNumericCellValue();

                String roll=Integer.toString(roll1.intValue());
                String name=nextRow.getCell(1).getStringCellValue();
                String college=nextRow.getCell(2).getStringCellValue();
                String wantToAccomplish=nextRow.getCell(3).getStringCellValue();
                String hobby=nextRow.getCell(4).getStringCellValue();
                String idol=nextRow.getCell(5).getStringCellValue();
                //String app=nextRow.getCell(5).getStringCellValue();
                String app="";
                if(nextRow.getCell(6).getCellType()==1)
                {
                    app=nextRow.getCell(6).getStringCellValue();
                }

                html=html+roll+"_"+name+".jpg\" rel=\"attachment wp-att-9757\"><img src=\"http://doko.dwit.edu.np/wp-content/uploads/2016/12/"+roll+"_"+name+".jpg\" alt=\"DSC00193\" width=\"150\" height=\"150\" class=\"alignnone size-thumbnail wp-image-9757\" /></a>\n" +
                        "</td>\n" +
                        "<td align=\"left\"><strong>Name</strong></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\" width=\"79%\">"+name+"</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\"><strong>High School</strong></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\">"+college+"</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\"><strong>Wants to accomplish after completing the course</strong></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\">"+wantToAccomplish+"</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\"><strong>Interests / hobbies</strong></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\"><span style=\"line-height: 19px;\">"+hobby+"</span></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\"><strong>Idolizes</strong></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\">"+idol+ "</td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\"><strong>3 most important Apps</strong></td>\n" +
                        "</tr>\n" +
                        "<tr>\n" +
                        "<td align=\"left\">"+app+ "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n";


            }
            System.out.println(html);
            PrintWriter writer = new PrintWriter("html.txt", "UTF-8");
            writer.print(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
