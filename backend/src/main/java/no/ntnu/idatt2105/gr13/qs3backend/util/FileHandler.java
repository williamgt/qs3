package no.ntnu.idatt2105.gr13.qs3backend.util;

import no.ntnu.idatt2105.gr13.qs3backend.model.filehandler.RegisterStudent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Static class for making csv-file into array-list of students
 */
public class FileHandler { //TODO csv file of new students to be registered
    public static String TYPE = "text/csv";

    /**
     * Generates a random password for new students
     * @return
     */
    public static String getRandomPassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( 15, characters );
    }

    /**
     * Reads file and creates Student for each line
     * @param file
     * @return
     * @throws IOException
     */
    public static ArrayList<RegisterStudent> getArrayList(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            throw new IllegalArgumentException("File can't be empty");
        }

        ArrayList<RegisterStudent> students = new ArrayList<>();
        InputStream is = file.getInputStream();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                RegisterStudent student = new RegisterStudent(
                        csvRecord.get("lastname"),
                        csvRecord.get("firstname"),
                        csvRecord.get("email"),
                        getRandomPassword()
                );
                students.add(student);
            }
            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
