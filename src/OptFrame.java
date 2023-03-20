import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class OptFrame extends JFrame {
    final private Font mainFont = new Font("Times New Roman", Font.BOLD, 24);
    final private Font subFont = new Font("Times New Roman", Font.BOLD, 16);

    public void inicializa() {

        // painel de introdução;

        JLabel titulo = new JLabel("Conversor de Unidades");
        titulo.setFont(mainFont);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitulo = new JLabel("Escolha sua opção");
        subtitulo.setFont(subFont);
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(5, 2, 5, 5));
        optionPanel.setOpaque(false);
        optionPanel.add(titulo);
        optionPanel.add(subtitulo);

        // painel de botoes;

        JButton btnConversor = new JButton("Conversor de Moedas");
        btnConversor.setFont(mainFont);
        btnConversor.setBackground(new Color(22, 123, 247));

        btnConversor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                FrameConversor frame = new FrameConversor();
                frame.inicializa();
                dispose();
            }            
        });

        JButton btnEscolha = new JButton("OUTRO CONVERSOR");
        btnEscolha.setFont(mainFont);
        btnEscolha.setBackground(new Color(22, 123, 247));
        btnEscolha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // ADICIONAR REFERENCIA PARA OUTRO CONVERSOR
            }
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1,1,1,1));
        btnPanel.setOpaque(false);

        btnPanel.add(btnConversor);
        //btnPanel.add(btnEscolha);

        // painel principal de exibicao

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(241, 241, 241));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(optionPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        // exibindo paineis

        add(mainPanel);
        setTitle("Conversor de Unidades");
        setSize(500, 300);
        setMinimumSize(new Dimension(500, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}