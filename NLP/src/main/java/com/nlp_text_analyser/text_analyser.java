package com.nlp_text_analyser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import opennlp.tools.parser.ParserModel;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import org.springframework.web.multipart.MultipartFile;


@Service
public class text_analyser {

    private String content;

    private MultipartFile file;

    public String getContent() {
        return content;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setContent(String content) {
        this.content = content;

    }

    public String countNounVerbPrepAdjWordsSentences() throws IOException, CsvException {

        String paragraph = getContent();
        MultipartFile file = getFile();
        List<List<String>> fileContent = readCsvData(file.getInputStream());

        InputStream modelInParse = null;

        //Add the file en-parser-chunking.bin in the directory
        modelInParse = new FileInputStream("en-parser-chunking.bin"); //from http://opennlp.sourceforge.net/models-1.5/
        ParserModel model = new ParserModel(modelInParse);

        //create parse tree
        Parser parser = ParserFactory.create(model);
        Parse parse1 = ParserTool.parseLine(paragraph, parser, 1)[0];
        Parse[] parses = ParserTool.parseLine(paragraph, parser, 2);

        Map<String, Integer> nounVerbPrepAdjWordsSentMap = countNounVerbPrepAdjWords(parse1);
        nounVerbPrepAdjWordsSentMap.put("num_sentences", countSentences(paragraph));

        Map<String, Integer> fileContentMap = calDifferenceCsvString(nounVerbPrepAdjWordsSentMap, fileContent);
        fileContentMap.remove("HTTP Link");

        return convertToHtmlTable(fileContentMap, nounVerbPrepAdjWordsSentMap);
    }

    public static Map<String, Integer> calDifferenceCsvString(Map<String, Integer> nounVerbPrepAdjWordsSentMap, List<List<String>> fileContent) {
        Map<String, Integer> differenceMap = new HashMap<>();

        for (String key : nounVerbPrepAdjWordsSentMap.keySet()) {
            int columnIndex = findColumnIndex(fileContent.get(0), key);

            if (columnIndex != -1) {

                int fileContentValue = (int) Double.parseDouble(fileContent.get(1).get(columnIndex));
                int difference = fileContentValue;
                differenceMap.put(key, difference);
            } else {
                System.out.println("Key not found in fileContent column names: " + key);
            }
        }
        return differenceMap;
    }

    private static int findColumnIndex(List<String> columnNames, String key) {
        for (int i = 0; i < columnNames.size(); i++) {
            if (columnNames.get(i).equals(key)) {
                return i;
            }
        }
        return -1;
    }


    private static Map<String, Integer> countNounVerbPrepAdjWords(Parse parse) {
        Map<String, Integer> counts = new HashMap<>();

        if (parse.getType().startsWith("VP") || parse.getType().equals("NNS")) {
            counts.put("verb_count", counts.getOrDefault("verb_count", 0) + 1);
        }
        if (parse.getType().startsWith("NNP") || parse.getType().equals("NN")) {
            counts.put("noun_count", counts.getOrDefault("noun_count", 0) + 1);
        }
        if (parse.getType().startsWith("NNP")) {
            counts.put("proper_noun_count", counts.getOrDefault("proper_noun_count", 0) + 1);
        }
        if (parse.getType().startsWith("IN")) {
            counts.put("prepositions_count", counts.getOrDefault("prepositions_count", 0) + 1);
        }
        if (parse.getType().startsWith("JJ")) {
            counts.put("adjective_count", counts.getOrDefault("adjective_count", 0) + 1);
        }

        for (Parse child : parse.getChildren()) {
            Map<String, Integer> childCounts = countNounVerbPrepAdjWords(child);
            for (Map.Entry<String, Integer> entry : childCounts.entrySet()) {
                counts.put(entry.getKey(), counts.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
        if (parse.isPosTag()) {
            counts.put("word_count", counts.getOrDefault("word_count", 0) + 1);
        }
        return counts;
    }

    private static int countSentences(String paragraph) {
        String[] sentences = paragraph.split("[.!?]");
        return sentences.length;
    }

    public static List<List<String>> readCsvData(InputStream inputStream) throws IOException, CsvException {
        List<List<String>> csvData = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {

            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                List<String> rowData = new ArrayList<>();

                for (String cell : row) {
                    rowData.add(cell);
                }
                csvData.add(rowData);
            }
        }
        return csvData;
    }

    public String convertToHtmlTable(Map<String, Integer> map1, Map<String, Integer> map2) {


        System.out.println(map1);
        System.out.println(map2);

        Set<String> allKeys = new HashSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        Map<String, Integer[]> combinedMap = new HashMap<>();

        for (String key : allKeys) {
            Integer value1 = map1.getOrDefault(key, 0);
            Integer value2 = map2.getOrDefault(key, 0);
            Integer difference = value1 - value2;

            combinedMap.put(key, new Integer[]{value1, value2, difference});
        }

        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table border=\"1\">");
        htmlTable.append("<tr><th>Key</th><th>CSV Value</th><th>Input String Value</th><th>Difference(CSV-Input String)</th></tr>");

        for (Map.Entry<String, Integer[]> entry : combinedMap.entrySet()) {
            String key = entry.getKey();
            Integer value1 = entry.getValue()[0];
            Integer value2 = entry.getValue()[1];
            Integer difference = entry.getValue()[2];

            htmlTable.append("<tr>")
                    .append("<td>").append(key).append("</td>")
                    .append("<td>").append(value1).append("</td>")
                    .append("<td>").append(value2).append("</td>")
                    .append("<td>").append(difference).append("</td>")
                    .append("</tr>");
        }

        htmlTable.append("</table>");
        return htmlTable.toString();
    }

}