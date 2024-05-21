import java.util.concurrent.*;

public class IntegralCalculator {
    private Function function;

    public IntegralCalculator(Function function) {
        this.function = function;
    }

    public double calculate(double a, double b, int n, int numThreads) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        double h = (b - a) / n;
        double sum = function.compute(a) + function.compute(b);

        Future<Double>[] futures = new Future[numThreads];
        int chunkSize = n / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize + 1;
            final int end = (i == numThreads - 1) ? n - 1 : (i + 1) * chunkSize;

            futures[i] = executor.submit(() -> {
                double localSum = 0.0;
                for (int j = start; j <= end; j++) {
                    double xj = a + j * h;
                    localSum += function.compute(xj) * (j % 2 == 0 ? 2 : 4);
                }
                return localSum;
            });
        }

        for (Future<Double> future : futures) {
            sum += future.get();
        }

        executor.shutdown();
        return sum * h / 3.0;
    }
}
