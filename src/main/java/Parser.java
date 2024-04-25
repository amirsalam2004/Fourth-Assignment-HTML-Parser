import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        boolean check=true;
        while (check){
            check=false;
            for(int i=1;i<sortedByName.size();i++){
                if(sortedByName.get(i).getName().compareTo(sortedByName.get(i-1).getName())<0){
                    Country country=sortedByName.get(i-1);
                    sortedByName.remove(sortedByName.get(i-1));
                    sortedByName.add(i,country);
                    check=true;
                }
            }
        }
        return  sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        boolean check=true;
        while (check){
            check=false;
            for(int i=1;i<sortedByPopulation.size();i++){
                if(sortedByPopulation.get(i).getPopulation()>sortedByPopulation.get(i-1).getPopulation()){
                    Country country=sortedByPopulation.get(i-1);
                    sortedByPopulation.remove(sortedByPopulation.get(i-1));
                    sortedByPopulation.add(i,country);
                    check=true;
                }
            }
        }
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        boolean check=true;
        while (check){
            check=false;
            for(int i=1;i<sortedByArea.size();i++){
                if(Double.compare(sortedByArea.get(i-1).getArea(),sortedByArea.get(i).getArea())<0){
                    Country country=sortedByArea.get(i-1);
                    sortedByArea.remove(sortedByArea.get(i-1));
                    sortedByArea.add(i,country);
                    check=true;
                }
            }
        }
        return sortedByArea;
    }

    public void setUp() throws IOException {
            File htmlFile=new File("C:\\Users\\ASUS\\IdeaProjects\\Fourth-Assignment-HTML-Parser\\src\\Resources\\country-list.html");
            Document htmlDoc=Jsoup.parse(htmlFile,"UTF-8");
            Elements countriesHTML=htmlDoc.select(".country");
            for (Element countryHTML : countriesHTML){
                String name=countryHTML.select(".country-name").text();
                String capital = countryHTML.select(".country-capital").text();
                Double area=Double.parseDouble(countryHTML.select(".country-area").text());
                int population=Integer.parseInt(countryHTML.select(".country-population").text());
                Country country=new Country(name,capital,population,area);
                countries.add(country);
            }
    }

    public static void main(String[] args) {
        try {
            Parser parser=new Parser();
            parser.setUp();
            while (true) {
                Object[] objects = {"sort by name", "sort by population (descending)","sort by area (descending)","EXIT"};
                Object selection = JOptionPane.showInputDialog(null,
                        "Hello\nWelcome to sorting countries app\nChoose a type of sorting",
                        "Sorting countries", JOptionPane.INFORMATION_MESSAGE, null, objects, objects[0]);
                if (selection.toString().equals("sort by name")) {
                    String sortedByName = "";
                    ArrayList<Country> sortByName = new ArrayList<>(parser.sortByName());
                    for (Country country : sortByName) {
                        sortedByName += (sortByName.indexOf(country) + 1) + "-" + country.toString() + "\n";
                        if((sortByName.indexOf(country)+1)%38==0){
                            sortedByName+="**Click here to see more**";
                            JOptionPane.showMessageDialog(null, sortedByName);
                            sortedByName="";
                        }
                    }
                    JOptionPane.showMessageDialog(null, sortedByName);
                }
                if (selection.toString().equals("sort by population (descending)")) {
                    String sortedByPopulation = "";
                    ArrayList<Country> sortByPopulation = new ArrayList<>(parser.sortByPopulation());
                    for (Country country : sortByPopulation) {
                        sortedByPopulation += (sortByPopulation.indexOf(country) + 1) + "-" + country.toString() + "\n";
                        if((sortByPopulation.indexOf(country)+1)%38==0){
                            sortedByPopulation+="**Click here to see more**";
                            JOptionPane.showMessageDialog(null, sortedByPopulation);
                            sortedByPopulation="";
                        }
                    }
                    JOptionPane.showMessageDialog(null, sortedByPopulation);
                }
                if (selection.toString().equals("sort by area (descending)")) {
                    String sortedByArea = "";
                    ArrayList<Country> sortByArea = new ArrayList<>(parser.sortByArea());
                    for (Country country : sortByArea) {
                        sortedByArea += (sortByArea.indexOf(country) + 1) + "-" + country.toString() + "\n";
                        if((sortByArea.indexOf(country)+1)%38==0){
                            sortedByArea+="**Click here to see more**";
                            JOptionPane.showMessageDialog(null, sortedByArea);
                            sortedByArea="";
                        }
                    }
                    JOptionPane.showMessageDialog(null, sortedByArea);
                }
                if (selection.toString().equals("EXIT"))
                    break;
            }
        }
        catch (IOException exception){
            System.out.println("Something went wrong");
        }

    }
}
