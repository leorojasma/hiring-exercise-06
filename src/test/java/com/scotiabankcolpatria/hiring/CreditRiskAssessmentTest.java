package com.scotiabankcolpatria.hiring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditRiskAssessmentTest {

    @Test
    public void standardDeviationTest() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        double standardDeviation = riskAssessment.standardDeviation(new int[]{0, 1, 3, 1, 0, 5, 0, 3, 2, 3});
        Assertions.assertEquals(1.6, standardDeviation, 0.0001);
    }

    @Test
    public void standardDeviationTest2() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        double standardDeviation = riskAssessment.standardDeviation(new int[]{-5, 1, 8, 7, 2});
        Assertions.assertEquals(4.673328578, standardDeviation, 0.000000001);
    }

    @Test
    public void maxPeakTest1() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        int index = riskAssessment.paymentDelayMaxPeakIndex(new int[]{5, 4, 2, 2, 0, 10, 10, 3});
        Assertions.assertEquals(0, index);

    }

    @Test
    public void maxPeakTest2() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        int index = riskAssessment.paymentDelayMaxPeakIndex(new int[]{5, 4, 2, 2, 0, 10, 8, 3});
        Assertions.assertEquals(5, index);

    }

    @Test
    public void maxPeakTest3() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        int index = riskAssessment.paymentDelayMaxPeakIndex(new int[]{0, 1, 1, 1, 0, 0, 0, 0});
        Assertions.assertEquals(-1, index);

    }

    @Test
    public void maxPeakTest4() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        int index = riskAssessment.paymentDelayMaxPeakIndex(new int[]{3, 3, 0, 3, 3, 0, 0, 3});
        Assertions.assertEquals(7, index);
    }

    @Test
    public void maxPeakTest5() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        int index = riskAssessment.paymentDelayMaxPeakIndex(new int[]{17, 18, 17, 1, 0, 16, 0, 0});
        Assertions.assertEquals(5, index);
    }

    @Test
    public void latePaymentProbabilityByPeriodTest1() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        double[] probabilities = riskAssessment.latePaymentProbabilityByPeriod(new int[][]{
                {0, 3, 6, 1, 0, 5, 0, 0},
                {0, 3, 0, 2, 0, 0, 0, 2},
                {0, 0, 1, 0, 3, 0, 2, 0},
                {0, 4, 0, 2, 0, 1, 1, 0}
        });
        Assertions.assertArrayEquals(new double[]{0.0, 0.75, 0.5, 0.75, 0.25, 0.5, 0.5, 0.25}, probabilities);
    }

    @Test
    public void latePaymentProbabilityByPeriodTest2() {
        CreditRiskAssessment riskAssessment = new CreditRiskAssessment();
        double[] probabilities = riskAssessment.latePaymentProbabilityByPeriod(new int[][]{
                {0, 1, 0, 0, 5, 1, 0, 1},
                {0, 3, 4, 3, 8, 2, 0, 2},
                {7, 8, 2, 0, 9, 0, 2, 4},
                {0, 0, 0, 5, 0, 0, 0, 9}
        });
        Assertions.assertArrayEquals(new double[]{0.25, 0.75, 0.5, 0.5, 0.75, 0.5, 0.25, 1.00}, probabilities);
    }
}
