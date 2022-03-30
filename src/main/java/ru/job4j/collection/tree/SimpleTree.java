package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

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
        boolean rsl = false;
        Optional<Node<E>> nodeOptional = findBy(parent);
        if (nodeOptional.isPresent() && findBy(child).isEmpty()) {
            nodeOptional.get().children.add(new Node<>(child));
            rsl = true;
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
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
