package ru.stqa.jft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.jft.addressbook.model.EntryData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class EntryDataGenerator {

    @Parameter(names = "-c", description = "Entry count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        EntryDataGenerator generator = new EntryDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
            List<EntryData> entries = generateEntries(count);
            saveAsJson (entries, new File(file));
    }

    private void saveAsJson(List<EntryData> entries, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(entries);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private List<EntryData> generateEntries(int count) {
        List<EntryData> entries = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entries.add(new EntryData().withFirstname(String.format("firstname %s", i))
                    .withLastname(String.format("lastname %s", i))
                    .withMiddlename(String.format("middlename %s", i))
                    .withBday("1").withBmonth("September").withByear("2000"));
        }
        return entries;
    }
}
