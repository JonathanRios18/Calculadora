package com.example.micalculadora;

public class Calculadora {
    
    private double valor1;
    private double valor2;

    public double getValor1() {
        return valor1;
    }

    public void setValor1(double valor1) {
        this.valor1 = valor1;
    }

    public double getValor2() {
        return valor2;
    }

    public void setValor2(double valor2) {
        this.valor2 = valor2;
    }



    public double sumar() {
        Suma suma = new Suma();
        return suma.operar(valor1, valor2);
    }

    public double restar() {
        Resta resta = new Resta();
        return resta.operar(valor1, valor2);
    }

    public double multiplicar() {
        Multiplicación multiplicacion = new Multiplicación();
        return multiplicacion.operar(valor1, valor2);
    }

    public double dividir() {
        División division = new División();
        if (valor2 != 0) {
            return division.operar(valor1, valor2);
        } else {
            throw new ArithmeticException("No se puede dividir entre cero");
        }
    }
}
