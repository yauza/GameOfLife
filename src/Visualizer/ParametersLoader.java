package Visualizer;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParametersLoader {

    public int mapWidth;
    public int mapHeight;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public int jungleRatio;
    public int startAnimals;

    public ParametersLoader(){};

    public void loadParameters() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/parameters.json"));
        JSONObject params = (JSONObject) obj;
        this.mapWidth = Integer.parseInt( Long.toString((long) params.get("mapWidth")));
        this.mapHeight = Integer.parseInt( Long.toString((long) params.get("mapHeight")));
        this.startEnergy = Integer.parseInt( Long.toString((long) params.get("startEnergy")));
        this.moveEnergy = Integer.parseInt( Long.toString((long) params.get("moveEnergy")));
        this.plantEnergy = Integer.parseInt( Long.toString((long) params.get("plantEnergy")));
        this.jungleRatio = Integer.parseInt( Long.toString((long) params.get("jungleRatio")));
        this.startAnimals = Integer.parseInt( Long.toString((long) params.get("startAnimals")));
    }


}
