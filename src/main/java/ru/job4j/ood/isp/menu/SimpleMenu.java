package ru.job4j.ood.isp.menu;

import java.util.*;

/**
 * @author Svistunov Mikhail
 * @version 1.0
 */
public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        SimpleMenuItem simpleMenuItem = new SimpleMenuItem(childName, actionDelegate);
        if (Objects.equals(parentName, Menu.ROOT)) {
            rsl = rootElements.add(simpleMenuItem);
        } else {
            Optional<ItemInfo> itemInfo = findItem(parentName);
            if (itemInfo.isPresent()) {
                rsl = itemInfo.get().menuItem.getChildren().add(simpleMenuItem);
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> menuItemInfo = Optional.empty();
        Optional<ItemInfo> itemInfo = findItem(itemName);
        if (itemInfo.isPresent()) {
            menuItemInfo = Optional.of(new MenuItemInfo(itemInfo.get().menuItem, itemInfo.get().number));
        }
        return menuItemInfo;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfsIterator = new DFSIterator();
        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo itemInfo = dfsIterator.next();
            if (name.equals(itemInfo.menuItem.getName())) {
                rsl = Optional.of(itemInfo);
                break;
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    /**
     * Это итератор, основанный на поиске в глубину.
     * Но немного модифицированный, поскольку нам удобно читать пункты меню сверху-вниз и слева-направо.
     */
    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (ListIterator<MenuItem> i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    /**
     * Класс служит для того, чтобы скомпоновать пункт меню и номер пункта (1.1., 1.1.1. и т.д.).
     */
    private class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
