package dev.stormgui.pope_api;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.internal.StringUtil.isNumeric;

@Slf4j
public class JsoupApp {

    public static void main(String[] args) throws Exception {
        getPopeTable();

    }

    static void getPopeTable() throws IOException {
        String url = "https://pt.wikipedia.org/wiki/Lista_de_Papas";

        // 🔹 Abre a URL e força a codificação UTF-8
        try (InputStream input = new URL(url).openStream()) {
            Document doc = Jsoup.parse(input, "UTF-8", url);

            Element table = doc.select("table.wikitable.sortable").first();
            List<String> rowsData = new ArrayList<>();

            if (table != null) {
                Elements rows = table.select("tr");

                for (Element row : rows) {
                    Elements columns = row.select("td");
                    if (columns.size() >= 4) { // 🔹 Garante que há pelo menos 4 colunas
                        String id = columns.get(0).text().trim();


                        // 🔹 Verifica se o ID é um número válido
                        if (!isNumeric(id)) {
                            continue; // Pula essa linha
                        }

                        String name = columns.get(1).text().trim();
                        String pontificate = columns.get(3).text().trim();
                        String placeOfBirth = columns.get(4).text().trim();

                        // Formata a string SQL e adiciona à lista
                        String sqlInsert = formatSQLInsert(id, name, pontificate, placeOfBirth);
                        rowsData.add(sqlInsert);
                    }
                }
            }

            // Imprime as instruções SQL geradas
            for (String sql : rowsData) {
                System.out.println(sql);
            }
        }
    }

    // 🔹 Método para formatar a linha como um INSERT SQL
    private static String formatSQLInsert(String id, String name, String pontificate, String placeOfBirth) {
        return String.format("INSERT INTO popes (id, name, pontificate, place_of_birth) VALUES (%s, '%s', '%s', '%s');",
                id, escapeSQL(name), escapeSQL(pontificate), escapeSQL(placeOfBirth));
    }

    // 🔹 Método para escapar aspas simples dentro dos valores SQL
    private static String escapeSQL(String value) {
        return value.replace("'", "''"); // SQL usa duas aspas simples para representar uma aspas simples no texto
    }
}
