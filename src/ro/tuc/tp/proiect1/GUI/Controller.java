package ro.tuc.tp.proiect1.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ro.tuc.tp.proiect1.DataModels.PolynomialWithIntegerCoefficient;
import ro.tuc.tp.proiect1.DataModels.PolynomialWithRealCoefficient;

public class Controller {

    public TextField polinom_1;
    public TextField polinom_2;
    public TextField polinom_rez;
    public Button addButton;
    public Button subtractButton;
    public Button multiplyButton;
    public Button divideButton;
    public Button deriveButton;
    public Button integrateButton;

    public void add() {
        PolynomialWithIntegerCoefficient p1 = new PolynomialWithIntegerCoefficient();
        PolynomialWithIntegerCoefficient p2 = new PolynomialWithIntegerCoefficient();
        p1.createPolynomialFromString(polinom_1.getText());
        p2.createPolynomialFromString(polinom_2.getText());
        p1.add(p2);
        polinom_rez.setText(p1.print());
    }

    public void subtract() {
        PolynomialWithIntegerCoefficient p1 = new PolynomialWithIntegerCoefficient();
        PolynomialWithIntegerCoefficient p2 = new PolynomialWithIntegerCoefficient();
        p1.createPolynomialFromString(polinom_1.getText());
        p2.createPolynomialFromString(polinom_2.getText());
        p1.subtract(p2);
        polinom_rez.setText(p1.print());
    }

    public void multiply() {
        PolynomialWithIntegerCoefficient p1 = new PolynomialWithIntegerCoefficient();
        PolynomialWithIntegerCoefficient p2 = new PolynomialWithIntegerCoefficient();
        p1.createPolynomialFromString(polinom_1.getText());
        p2.createPolynomialFromString(polinom_2.getText());
        p1.multiply(p2);
        polinom_rez.setText(p1.print());
    }

    public void divide() {
        PolynomialWithIntegerCoefficient p1 = new PolynomialWithIntegerCoefficient();
        PolynomialWithIntegerCoefficient p2 = new PolynomialWithIntegerCoefficient();
        p1.createPolynomialFromString(polinom_1.getText());
        p2.createPolynomialFromString(polinom_2.getText());
        PolynomialWithRealCoefficient r1 = p1.createPolynomialWithRealCoefficient();
        PolynomialWithRealCoefficient r2 = p2.createPolynomialWithRealCoefficient();
        PolynomialWithRealCoefficient cat = r1.divide(r2);
        if(r1.print().length()==0)
            polinom_rez.setText(cat.print());
        else
        polinom_rez.setText(cat.print() + " rest " + r1.print());
    }

    public void derive() {
        PolynomialWithIntegerCoefficient p1 = new PolynomialWithIntegerCoefficient();
        p1.createPolynomialFromString(polinom_1.getText());
        p1.derive();
        polinom_rez.setText(p1.print());
    }

    public void integrate() {
        PolynomialWithIntegerCoefficient p1 = new PolynomialWithIntegerCoefficient();
        p1.createPolynomialFromString(polinom_1.getText());
        PolynomialWithRealCoefficient r=p1.createPolynomialWithRealCoefficient();
        r.integrate();
        polinom_rez.setText(r.print()+"+c");
    }
}
