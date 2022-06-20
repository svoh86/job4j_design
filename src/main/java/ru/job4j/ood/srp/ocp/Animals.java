package ru.job4j.ood.srp.ocp;

/**
 * Класс нарушает принцип ОСР, т.к.
 * поле cat в классе Pet не представляет тип абстракции (Animal).
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class Animals {

    public interface Animal {
        void voice();
    }

    public static class Cat implements Animal {
        @Override
        public void voice() {
            System.out.println("may");
        }
    }

    public static class Pet {
        Cat cat = new Cat();

        public void walk(Animal animal) {
        }
    }


}

