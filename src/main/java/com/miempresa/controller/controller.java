package com.miempresa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {

    @GetMapping("/calcular")
    public String calcularForm() {
        return "calcular";
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam("inicio") int inicio, @RequestParam("fin") int fin,
                           @RequestParam("operacion") String operacion, Model model, HttpSession session) {
        if ("primos".equals(operacion)) {
            List<Integer> numerosPrimos = calcularNumerosPrimos(inicio, fin);
            model.addAttribute("numerosPrimos", numerosPrimos);
            model.addAttribute("operacion", "primos");
            model.addAttribute("inicio", inicio);
            model.addAttribute("fin", fin);
            session.setAttribute("numerosPrimos", numerosPrimos);
            session.setAttribute("operacion", "primos");
            session.setAttribute("inicio", inicio);
            session.setAttribute("fin", fin);
            return "resultados";
        } else if ("tabla".equals(operacion)) {
            List<ResultadoTabla> resultadosTabla = generarTablaMultiplicar(inicio);
            model.addAttribute("resultadosTabla", resultadosTabla);
            model.addAttribute("operacion", "tabla");
            model.addAttribute("inicio", inicio);
            session.setAttribute("resultadosTabla", resultadosTabla);
            session.setAttribute("operacion", "tabla");
            session.setAttribute("inicio", inicio);
            return "resultados2";
        } else {
            return "error";
        }
    }

    private List<Integer> calcularNumerosPrimos(int inicio, int fin) {
        // Implementa la lógica para calcular números primos aquí
        // Devuelve una lista de números primos encontrados
        // Implementación de ejemplo (incompleta)
        List<Integer> numerosPrimos = new ArrayList<>();
        for (int num = inicio; num <= fin; num++) {
            boolean esPrimo = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    esPrimo = false;
                    break;
                }
            }
            if (esPrimo && num > 1) {
                numerosPrimos.add(num);
            }
        }
        return numerosPrimos;
    }

    private List<ResultadoTabla> generarTablaMultiplicar(int numero) {
        // Implementa la lógica para generar la tabla de multiplicar aquí
        // Devuelve una lista de objetos ResultadoTabla con los resultados y la información del checkbox
        // Implementación de ejemplo (incompleta)
        List<ResultadoTabla> resultadosTabla = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int resultado = numero * i;
            boolean esPar = resultado % 2 == 0;
            resultadosTabla.add(new ResultadoTabla(resultado, esPar));
        }
        return resultadosTabla;
    }

    public static class ResultadoTabla {
        private int resultado;
        private boolean esPar;

        public ResultadoTabla(int resultado, boolean esPar) {
            this.resultado = resultado;
            this.esPar = esPar;
        }

        public int getResultado() {
            return resultado;
        }

        public boolean isEsPar() {
            return esPar;
        }
    }
}

