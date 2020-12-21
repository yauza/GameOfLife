package Visualizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    private String [] data;
    static int fileNumber = 0;

    public FileCreator(String [] data){
        this.data = data;
    }

    public FileCreator(){ };

    public void createFileWithStats() throws IOException {
        String name = "stats" + fileNumber;
        File newFile = new File(name);

        if(newFile.createNewFile()){
            System.out.println("jest w pyte");
        }else{
            System.out.println("zle");
            fileNumber++;
            name = "stats" + fileNumber;
            newFile.createNewFile();

        }

        FileWriter fileWriter = new FileWriter(name);

        fileWriter.write("Statistics for " + data[0] + " era:\n");
        fileWriter.write("    Average number of Animals: " + data[1] + "\n");
        fileWriter.write("    Average number of Grass: " + data[2] + "\n");
        fileWriter.write("    Dominating genotype: " + data[3] + "\n");
        fileWriter.write("    Average energy level: " + data[4] + "\n");
        fileWriter.write("    Average longevity: " + data[5] + "\n");
        fileWriter.write("    Average number of children: " + data[6] + "\n");

        fileWriter.close();
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
