

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import gameProgress.*;

public class Main {

    private static void openZip(String pathFile, String pathFolder) {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(pathFile))) {
            ZipEntry zipEntry;
            String name;
            while ((zipEntry = zin.getNextEntry()) != null) {
                name = zipEntry.getName();

                FileOutputStream fout = new FileOutputStream(pathFolder + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }

                fout.flush();
                zin.closeEntry();
                fout.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }



    private static GameProgress openProgress(String pathFile) {

        GameProgress gameProgress = null;
        try(FileInputStream fis = new FileInputStream(pathFile);
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            gameProgress = (GameProgress) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return gameProgress;

    }
    public static void main(String[] args) {
        openZip("Games/savegames/zip.zip", "Games/savegames/");
        System.out.println(openProgress("Games/savegames/save1.dat"));
        System.out.println(openProgress("Games/savegames/save2.dat"));
        System.out.println(openProgress("Games/savegames/save3.dat"));

    }
}
