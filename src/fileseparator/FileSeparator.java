/*
por Henrique Loubaque - 02/03/2021
Algoritmo que separa arquivos em pastas de acordo com seu tipo. Ex.: exe, zip, docx 
Para uso, definir o diretório entre aspas nos parâmetros da linha de comando.
*/
package fileseparator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileSeparator {
    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("Parâmetro inválido");
            System.exit(0);
        }
        String dir = args[0];
        
        File f = new File(dir);
        
        if(f.isFile()){
            System.out.println("Caminho não pode ser um arquivo");
            System.exit(0);
        }
        
        for(File file : f.listFiles()){
            if(file.isDirectory()){
                continue;
            }
            
            String fileType = getFileType(file.getName());
            String newPath = dir + "\\" + fileType;
            makeDir(newPath);
            copyFile(file, new File(newPath + "\\" + file.getName()));
        }
    }
    
    public static String getFileType(String fileName){
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
    
    public static void makeDir(String path){
        File newDir = new File(path);
        if(newDir.mkdir()){
            System.out.println("Criando pasta " + newDir.getParent());
        }
    }
    
    public static boolean copyFile(File from, File to){
        try {
            System.out.println("Copiando arquivo " + from.toPath() + " para " + to.toPath());
            Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            System.out.println(" -> Erro ao copiar arquivo: " + ex.getMessage());
        }
        return false;
    }
    
}
