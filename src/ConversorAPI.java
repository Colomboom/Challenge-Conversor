

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.Desktop;

import com.google.gson.Gson;

public class ConversorAPI {
    public static Double converte(String deMoeda, String paraMoeda, Double amount) throws Exception {

        String url = "https://api.apilayer.com/fixer/convert?to=" + paraMoeda +
                "&from=" + deMoeda + "&amount=" + amount;

        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.newBuilder(endereco).GET().build();

        HttpRequest request = HttpRequest.newBuilder(endereco)
                .header("apikey", apiKey())
                .GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        Gson gson = new Gson();

        ConversaoMoedas resposta = gson.fromJson(body, ConversaoMoedas.class);

        // System.out.println("Moeda de destino: " + resposta.getQuery().getTo());
        // System.out.println("Taxa de conversão: " + resposta.getInfo().getRate());
        // System.out.println("Resultado da conversão: " + resposta.getResult());

        return resposta.getResult();
    }

    static String apiKey() {
        String apiKey = "";
        String path = System.getProperty("user.dir") + "\\apiKey.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            apiKey = br.readLine();
            br.close();
        } catch (IOException e) {
            e.getMessage();
        }

        if (apiKey == null) {

            JEditorPane ep = new JEditorPane("text/html",
                "<body><a href='https://apilayer.com/marketplace/fixer-api'>Clique aqui se cadastrar e obter sua chave.</a></body></html>");
        ep.setEditable(false);
        ep.setOpaque(false);
        ep.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                    try {
                        Desktop.getDesktop().browse(new URL(e.getURL().toString()).toURI());
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
                ;
            }
        });
        JOptionPane.showMessageDialog(null, ep);            

            try (
                    BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                apiKey = JOptionPane.showInputDialog(null, "Cole aqui sua chave de acesso a API.", "Chave da API", 1);
                bw.write(apiKey);
                bw.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }

        return apiKey;
    }



    class ConversaoMoedas {
        private Query query;
        private Info info;
        private double result;

        public Query getQuery() {
            return query;
        }

        public Info getInfo() {
            return info;
        }

        public double getResult() {
            return result;
        }
    }

    class Query {
        private String from;
        private String to;

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

    }

    class Info {
        private double rate;

        public double getRate() {
            return rate;
        }

    }

}
