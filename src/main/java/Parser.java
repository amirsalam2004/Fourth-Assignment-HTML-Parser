import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        //TODO
        return  sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        // Sort countries by population (most)
        //TODO
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        // Sort countries by area (most)
        //TODO
        return sortedByArea;
    }

    public void setUp() throws IOException {
        try {
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
        catch (IOException exception){
            System.out.println("Something went wrong");
        }
    }

    public static void main(String[] args) {
        try {
            Parser parser=new Parser();
            parser.setUp();
            for (Country country : countries){
                System.out.println(country.toString());
            }
        }
        catch (IOException exception){

        }

    }
}
