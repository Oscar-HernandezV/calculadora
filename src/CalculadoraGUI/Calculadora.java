package CalculadoraGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

public class Calculadora extends JFrame implements ActionListener {
    private JLabel lblDisplay;
    private final Logica logica;

    public Calculadora(Logica logica){
        this.logica = logica;
        setSize(350,400);
        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        Componentes();
        try{
            Image img= ImageIO.read(new File("miLetra.png"));
            this.setIconImage(img);
        }catch (Exception e){
            System.out.println("Error en IconoImage");
        }

    }
    private void Componentes(){
        Buttons();
        Container();
    }

    private  void Container(){
        JPanel contenedor = new JPanel();
        contenedor.setBackground(new Color(162, 160, 160));
        contenedor.setSize(350,400);
        contenedor.setBorder(BorderFactory.createLineBorder(new Color(162, 160, 160)));
        this.getContentPane().add(contenedor);
    }

    private void Buttons(){

        //Agrega label para mostrar resultados
        lblDisplay = new JLabel();
        lblDisplay.setBackground(new Color(255, 255, 255));
        lblDisplay.setFont(new Font("TimesRoman", Font.PLAIN, 17));
        lblDisplay.setHorizontalAlignment(SwingConstants.LEFT);
        lblDisplay.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        lblDisplay.setOpaque(true);
        getContentPane().add(lblDisplay);
        lblDisplay.setBounds(30, 30, 270, 40);

        //Agrega el panel de los botones
        JPanel pnlBotones = new JPanel();
        pnlBotones.setLayout(new GridLayout(4, 4, 3, 3));
        pnlBotones.setBackground(new Color(162, 160, 160));
        getContentPane().add(pnlBotones);
        pnlBotones.setBounds(30, 90, 270, 200);
        //Coloca los botones
        JButton[] botones = new JButton[16];
        String[] textoBotones = {"+","-","*","/",
                "1","2","3","4",
                "5","6","7","8",
                "9","0",".","="};

        JButton botonClear = new JButton("Clear");
        botonClear.setBounds(115,300,100,40);
        botonClear.setForeground(Color.WHITE);
        botonClear.setFont(new Font("TimesRoman", Font.BOLD, 17));
        getContentPane().add(botonClear);



        for (int i=0; i<botones.length; i++) {
            botones[i] = new JButton();
            botones[i].setText(textoBotones[i]);
            botones[i].setFont(new Font("TimesRoman", Font.BOLD, 17));
            botones[i].setBackground(new Color(162, 160, 160));
            botones[i].setForeground(Color.BLACK);
            botones[i].addActionListener(this);
            botonClear.addActionListener(this);
            botonClear.setForeground(Color.BLACK);
            botonClear.setBackground(new Color(162, 160, 160));
            pnlBotones.add(botones[i]);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton)(e.getSource());
        DecimalFormat format = new DecimalFormat("#.######");
        format.setDecimalSeparatorAlwaysShown(false);

        if("+-*/".contains(boton.getText())){
            float numero1 = Float.parseFloat(lblDisplay.getText());
            logica.addOperando1(numero1);
            char operador = boton.getText().charAt(0);
            logica.addOperandor(operador);
            lblDisplay.setText("");
        } else if("=".contains(boton.getText())){
            float numero2 = Float.parseFloat(lblDisplay.getText());
            float resultado = logica.operacion(numero2);
            lblDisplay.setText("" + format.format(resultado));
        } else if("0123456789".contains(boton.getText())){
            String numero = lblDisplay.getText();
            if(numero.equals("0")){
                numero = "";
            }
            numero = numero + boton.getText();
            lblDisplay.setText(numero);
        } else if(".".contains(boton.getText())){
            String numero = lblDisplay.getText();
            if(!numero.contains(".")){
                numero = numero + boton.getText();
                lblDisplay.setText(numero);
            } else {
                lblDisplay.setText("");
            }
        } else if("Clear".contains(boton.getText())){
            lblDisplay.setText("");
        }
    }
}

