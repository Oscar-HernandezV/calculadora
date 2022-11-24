package CalculadoraGUI;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;

import javax.swing.*;

public class PrincipalCalculadora {
    public static void main(String[] args) {
        Logica logica = new Logica();
        try {
            UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Calculadora calculadora = new Calculadora(logica);
        calculadora.setVisible(true);
    }
}
