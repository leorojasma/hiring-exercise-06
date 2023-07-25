package com.scotiabankcolpatria.hiring;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class CreditRiskAssessment {

    public double standardDeviation(int[] paymentDelays) {
        //Valor base para el calculo de la desviacion Standard, valor final a retornar
        double stdDeviation = 0;
        double avgValue = Arrays.stream(paymentDelays).asDoubleStream().average().orElse(0);
        for (int paymentDelay : paymentDelays) {
            //Sumatoria de los cuadrados de las distancias de cada punto al valor promedio
            stdDeviation += Math.pow(paymentDelay - avgValue, 2);
        }
        stdDeviation = Math.sqrt(stdDeviation / paymentDelays.length);
        return stdDeviation;
    }

    public int paymentDelayMaxPeakIndex(int[] paymentDelays) {
        int[] unusualLatePayments = new int[paymentDelays.length];
        int maxPeakIndex = -1;
        int maxPeakValue = -1;

        for (int i = 0; i < paymentDelays.length; i++) {
            //Obtenemos los dias de atraso del ciclo anterior, o 0 si no hay informacion del mismo.
            int paymentCycleBefore = i > 0 ? paymentDelays[i - 1] : 0;
            //Obtenemos los dias de atraso del ciclo siguiente, o 0 si no hay informacion del mismno.
            int paymentCycleAfter = i + 1 < paymentDelays.length ? paymentDelays[i + 1] : 0;
            //Analizando condicion de negocio
            if (paymentDelays[i] > paymentCycleBefore)//Analizamos si el ciclo en analisis tuvo un delay mayor al cicclo pasado
                if (paymentDelays[i] > paymentCycleAfter)//Analizamos si el ciclo en analisis tuvo un delay mayor al cicclo posterior
                    //Analizamos si el pico actual en analisis es el mayor analizado hasta el momento
                    if (paymentDelays[i] > maxPeakValue) {
                        maxPeakValue = paymentDelays[i];
                        maxPeakIndex = i;
                    }
        }
        return maxPeakIndex;
    }

    public double[] latePaymentProbabilityByPeriod(int[][] paymentDelays) {
        //Obtenemos el numero de productos y periodos a analizar
        int periodQuantity = paymentDelays[0].length;
        int productQuantity = paymentDelays.length;

        //Creamos un a arreglo para el almacenamiento del calculo y el retorno de porcentajes por periodo
        double[] probabilityArray = new double[periodQuantity];

        //Recorremos cada uno de los periodos
        for (int per = 0; per < periodQuantity; per++) {
            //Analizamos por cada periodo, todos los productos
            for (int pro = 0; pro < productQuantity; pro++) {
                //Si el producto en ese periodo presenta mora, asignarlo al arreglo
                if (paymentDelays[pro][per] > 0) {
                    probabilityArray[per]++;
                }
            }
            //Promediando sumatorias de numero de periodos con mora
            probabilityArray[per] /= productQuantity;
        }
        return probabilityArray;
    }
}
