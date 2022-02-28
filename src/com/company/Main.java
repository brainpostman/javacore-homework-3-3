package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
	    openZip("D://Games/Game/savegames/saves.zip", "D://Games/Game/savegames");
        System.out.println(openProgress("D://Games/Game/savegames/save1.dat"));
    }
    public static void openZip(String zipPath, String folderPath) {
        try (FileInputStream fin = new FileInputStream(zipPath);
             ZipInputStream zin = new ZipInputStream(fin)) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream(new File(folderPath, entry.getName()));
                for (int i = zin.read(); i != -1; i = zin.read()) {
                    fout.write(i);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String filePath) {
        GameProgress gp = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            gp = (GameProgress) ois.readObject();
            return gp;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gp;
    }
}
