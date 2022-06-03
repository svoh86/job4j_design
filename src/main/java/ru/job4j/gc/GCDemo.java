package ru.job4j.gc;

/**
 * Класс описывает пример работы GC.
 * Размер хипа для нашей программы можно задать с помощью ключей -XmxNm -XmsNm,
 * соответственно максимальный и начальный размеры хипа.
 * freeMemory(), возвращает количество свободной памяти в байтах.
 * totalMemory(), возвращает общее количество памяти, которое может быть использовано.
 * maxMemory(), возвращает максимальное количество памяти, которое может быть использовано.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10000; i++) {
            new User(i, "N" + i);
        }
        info();
    }
}
