import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

public class IntegralCalculatorTest {
    @Test
    public void testFunction() {
        Function function = new Function();
        assertEquals(0.462117, function.compute(1), 1e-6);
    }

    @Test
    public void testIntegralCalculation() throws InterruptedException, ExecutionException {
        Function function = new Function();
        IntegralCalculator calculator = new IntegralCalculator(function);
        double result = calculator.calculate(1, 2, 1000, 4);
        assertEquals(0.6273326470495001, result, 1e-6);
    }
}
