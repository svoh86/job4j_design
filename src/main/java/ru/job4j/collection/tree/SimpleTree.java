package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Класс описывает элементарную структуру дерева.
 * По заданию в дереве не могут храниться дубликаты.
 *
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод находит узел по значению parent и
     * добавляет в него дочерний узел со значением child.
     * Нужно проверить, что parent есть, а child еще нет. Иначе вернуть false.
     * Проверка идет через метод findBy, который возвращает Optional<Node<E>>.
     *
     * @param parent родитель
     * @param child  потомок
     * @return true or false
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> nodeOptional = findBy(parent);
        boolean rsl = nodeOptional.isPresent() && findBy(child).isEmpty();
        if (rsl) {
            nodeOptional.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод использует алгоритм обхода в ширину.
     * breadth first search
     *
     * @param value значение
     * @return Optional<Node < E>>
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(n -> n.value.equals(value));
    }

    /**
     * Метод проверяет количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное
     *
     * @return true or false
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(n -> n.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
