import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        String copyTo = "C:\\AllBackUp\\HERE\\";
        String copyFrom = "C:\\Алешино\\GeekBrains\\Выбор специализации\\";

        File dirToCopy = new File(copyTo);
        boolean createDir = dirToCopy.mkdir();
        copyFiles2(copyFrom, copyTo);

    }

    /**
     * Метод копирует данные с одного файла в другой
     * @param from - откуда копирует
     * @param to - куда копирует
     * @throws IOException - возможно и такое)))
     */
    static void copyFiles(String from, String to) throws IOException {

        try (FileOutputStream write = new FileOutputStream(to)) {
            int element;
            try (FileInputStream read = new FileInputStream(from)) {
                while ((element = read.read()) != -1) {
                    write.write(element);
                }
            }
        }

    }

    /**
     * Метод, который копирует содержимое из одной папки в другую, создавая пустые файлы - копии найденных оригиналов
     * @param from - откуда нужно скопировать
     * @param to - куда нужно скопировать
     * @throws IOException - и такое тоже возможно)))
     */
    static void copyFiles2(String from, String to) throws IOException {
        File rootDir = new File(from);
        File[] files = rootDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            //Если натыкаюсь на файл, то просто его копирую
            if (files[i].isFile()) {
                File file = new File(to + files[i].getName());
                boolean createFile = file.createNewFile();
                //Заполняю пустой файл содержимым оригинала
                copyFiles(files[i].getPath(), file.getPath());
            }
            //Если натыкаюсь на папку, то копирую папку и относительно неё вызываю рекурсивно данный метод,
            //делаю её рутовой, просматриваю в ней что есть и т.д.
            if (files[i].isDirectory()) {
                System.out.println(files[i].getName());
                File file = new File(to + files[i].getName() + "\\");
                boolean createFile = file.mkdir();
                System.out.println(file.getPath());
                copyFiles2(files[i].getPath(), (file.getPath()+"\\"));

            }

        }
    }

}