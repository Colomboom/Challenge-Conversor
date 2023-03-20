import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
    public class FrameConversor extends JFrame {
        final private Font mainFont = new Font("Times New Roman", Font.BOLD, 24);
    
        public void inicializa() {
    
            String[] opcoes = { "BRL - Real", "USD - Dólar Americano", "EUR - Euro", "GBP - Libra Esterlina",
                    "ARS - Peso Argentino", "CLP - Peso Chileno" };
    
            JComboBox<String> opt = new JComboBox<>(opcoes);
            JComboBox<String> opt2 = new JComboBox<>(opcoes);
    
            // painel de insercao de dados
    
            JLabel deMoeda = new JLabel("Selecione uma moeda");
            deMoeda.setFont(mainFont);
    
            JLabel paraMoeda = new JLabel("Converter para");
            paraMoeda.setFont(mainFont);
    
            JLabel valor = new JLabel("Informe o valor a ser convertido: ");
            valor.setFont(mainFont);
    
            JTextField valorConversao = new JTextField();
            valorConversao.setFont(mainFont);
    
            JPanel dataPanel = new JPanel();
    
            dataPanel.setLayout(new GridLayout(5, 2, 5, 5));
            dataPanel.add(deMoeda);
            dataPanel.add(opt);
            dataPanel.add(paraMoeda);
            dataPanel.add(opt2);
            dataPanel.add(valor);
            dataPanel.add(valorConversao);
    
            // painel de resultado
    
            JLabel resultado = new JLabel();
            resultado.setFont(mainFont);
    
            // painel de botoes
    
            JButton btnConverter = new JButton("Fazer Conversão");
            btnConverter.setFont(mainFont);
            btnConverter.setBackground(new Color(22, 123, 247));
            btnConverter.addActionListener(new ActionListener() {
    
                @Override
                public void actionPerformed(ActionEvent e) {
    
                    // CHAMAR API PASSANDO AS OPÇOES DE CONVERSAO E VALOR
                    Double resultadoConversao = 0d;
                    if (valorConversao.getText().equals("") || valorConversao.getText().matches("[a-zA-Z]+")) {
                        resultado.setText("insira um valor válido");
                    } else {
    
                        String toCoin = (String) opt.getSelectedItem();
                        toCoin = toCoin.substring(0, 3);
    
                        String fromCoin = (String) opt2.getSelectedItem();
                        fromCoin = fromCoin.substring(0, 3);
    
                        Double valor = (Double) Double.parseDouble(valorConversao.getText());
    
                        try {
                            resultadoConversao = ConversorAPI.converte(toCoin, fromCoin, valor);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
    
                        resultado.setText("Valor da conversão é de:  " + String.format("%.2f ", resultadoConversao)
                                + opt2.getSelectedItem().toString().substring(0, 3));
                    }
                }
    
            });
    
            JButton btnLimpar = new JButton("Limpar");
            btnLimpar.setFont(mainFont);
            btnConverter.setBackground(new Color(22, 123, 247));
            btnLimpar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resultado.setText("");
                    valorConversao.setText("");
                }
            });
    
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(2, 1, 5, 5));
            buttonsPanel.add(btnConverter);
            buttonsPanel.add(btnLimpar);
    
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBackground(new Color(241, 241, 241));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            mainPanel.add(dataPanel, BorderLayout.NORTH);
            mainPanel.add(resultado, BorderLayout.CENTER);
            mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    
            // exibindo paineis
    
            add(mainPanel);
            setTitle("Conversor de Unidades");
            setSize(500, 350);
            setMinimumSize(new Dimension(750, 300));
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
            setLocationRelativeTo(null);
    
        }
    }