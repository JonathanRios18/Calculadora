package com.example.micalculadora;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultadoText;
    private StringBuilder entrada = new StringBuilder();
    private String operadorActual;
    private Calculadora calculadora;
    private double valor1 = 0;
    private double valor2 = 0;
    private boolean operadorPresionado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el TextView para mostrar el resultado
        resultadoText = findViewById(R.id.resultadoText);
        calculadora = new Calculadora();

        // Asignar listeners a los botones numéricos y operadores
        configurarBotones();
    }

    private void configurarBotones() {
        // Botones numéricos
        int[] botonesNumericos = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        View.OnClickListener listenerNumerico = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button boton = (Button) view;
                if (operadorPresionado) {
                    entrada.setLength(0); // Borrar el contenido de entrada cuando un operador fue presionado
                    operadorPresionado = false;
                }
                entrada.append(boton.getText().toString());
                resultadoText.setText(entrada.toString());
            }
        };

        for (int id : botonesNumericos) {
            findViewById(id).setOnClickListener(listenerNumerico);
        }

        // Botones de operaciones
        findViewById(R.id.buttonMas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("+");
            }
        });

        findViewById(R.id.buttonMenos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("-");
            }
        });

        findViewById(R.id.buttonMultiplicacion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("×");
            }
        });

        findViewById(R.id.buttonDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion("÷");
            }
        });

        // Botón AC para borrar
        findViewById(R.id.buttonAC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        // Botón Igual para calcular el resultado
        findViewById(R.id.buttonIgual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });
    }

    // Método para realizar la operación (guardar operador y valor1)
    private void realizarOperacion(String operador) {
        if (entrada.length() > 0) {
            valor1 = Double.parseDouble(entrada.toString());
            operadorActual = operador;
            operadorPresionado = true;
        }
    }

    // Método para calcular el resultado basado en el operador actual
    private void calcularResultado() {
        if (entrada.length() > 0 && operadorActual != null) {
            valor2 = Double.parseDouble(entrada.toString());
            calculadora.setValor1(valor1);
            calculadora.setValor2(valor2);

            double resultado = 0;
            switch (operadorActual) {
                case "+":
                    resultado = calculadora.sumar();
                    break;
                case "-":
                    resultado = calculadora.restar();
                    break;
                case "×":
                    resultado = calculadora.multiplicar();
                    break;
                case "÷":
                    try {
                        resultado = calculadora.dividir();
                    } catch (ArithmeticException e) {
                        resultadoText.setText("Error");
                        return;
                    }
                    break;
            }
            resultadoText.setText(String.valueOf(resultado));
            entrada.setLength(0);
            operadorActual = null;
        }
    }

    // Método para limpiar la pantalla y los valores
    private void limpiar() {
        entrada.setLength(0);
        operadorActual = null;
        valor1 = 0;
        valor2 = 0;
        resultadoText.setText("0");
        operadorPresionado = false;
    }
}
