package servicio;

public class ConsultaServicio {

    /**
     * Determina si n es un numero perfecto: la suma de sus divisores
     * propios (sin incluir n) es igual a n. Ejemplo: 28 -> 1+2+4+7+14=28.
     * Se recorre solo hasta la raiz cuadrada de n para mayor eficiencia.
     */
    public boolean esNumeroPerfecto(long n) {
        if (n <= 1) return false;
        long suma = 1; // 1 siempre es divisor propio de n > 1
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                suma += i;
                long pareja = n / i;
                if (pareja != i) {
                    suma += pareja;
                }
            }
        }
        return suma == n;
    }
}