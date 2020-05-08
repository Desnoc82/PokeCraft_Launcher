package fr.deltadesnoc.ui.ram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.deltadesnoc.ui.panels.PanelHome;

public class RamManager {

    public static String getRam() {
        int ram = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(PanelHome.DIR, "ram.properties")));
            String line;
            while ((line = br.readLine()) != null) {
                ram = Integer.parseInt(line.substring(0, line.length() - 2));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = Integer.toString(ram);
        return str;
    }

    public static void setRam(String val) {
        File ramfile = new File(PanelHome.DIR, "ram.properties");
        if (!ramfile.exists()) {
            try {
                ramfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter rama = new FileWriter(new File(PanelHome.DIR, "ram.properties"));
            rama.write(val);
            rama.close();
        } catch (Exception localException) {}
    }
}
