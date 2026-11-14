import java.util.*;

class Item {
    String code;
    String name;
    double price;
    int qty;

    Item(String code, String name, double price, int qty) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    void show() {
        System.out.println(code + "  " + name + "  " + price + "  " + qty);
    }
}

public class InventoryManagement {

    static HashMap<String, Item> map = new HashMap<>();

    static void addItem(String code, String name, double price, int qty) {
        if (map.containsKey(code)) {
            System.out.println("Item already exists");
            return;
        }
        Item it = new Item(code, name, price, qty);
        map.put(code, it);
        System.out.println("Item added");
    }

    static void showAll() {
        if (map.isEmpty()) {
            System.out.println("No items yet");
            return;
        }
        System.out.println("Code  Name  Price  Qty");
        for (Item it : map.values()) {
            it.show();
        }
    }

    static void sortByName() {
        ArrayList<Item> list = new ArrayList<>(map.values());
        Collections.sort(list, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return a.name.compareToIgnoreCase(b.name);
            }
        });
        System.out.println("Sorted by name:");
        for (Item it : list) it.show();
    }

    static void sortByPrice() {
        ArrayList<Item> list = new ArrayList<>(map.values());
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).price > list.get(j).price) {
                    Item temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.println("Sorted by price:");
        for (Item it : list) it.show();
    }

    static void sortByQty() {
        ArrayList<Item> list = new ArrayList<>(map.values());
        Collections.sort(list, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return a.qty - b.qty;
            }
        });
        System. out.println("Sorted by quantity:");
        for (Item it : list) it.show();
    }

    static void lowStock(int limit) {
        System.out.println("Low stock items:");
        boolean found = false;
        for (Item it : map.values()) {
            if (it.qty < limit) {
                it.show();
                found = true;
            }
        }
        if (!found) {
            System.out.println("All items have enough stock");
        }
    }

    static void search(String code) {
        Item it = map.get(code);
        if (it != null) {
            it.show();
        } else {
            System.out.println("Item not found");
        }
    }

    static void update(String code, int newQty) {
        Item it = map.get(code);
        if (it != null && newQty >= 0) {
            it.qty = newQty;
            System.out.println("Updated");
        } else {
            System.out.println("Invalid code or qty");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch = 0;

        System.out.println("Inventory Management System");

        while (ch != 9) {
            System.out.println("\n1. Add Item");
            System.out.println("2. Show All");
            System.out.println("3. Sort by Name");
            System.out.println("4. Sort by Price");
            System.out.println("5. Sort by Quantity");
            System.out.println("6. Low Stock");
            System.out.println("7. Search");
            System.out.println("8. Update Qty");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                System.out.print("Enter code: ");
                String code = sc.nextLine();
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                System.out.print("Enter qty: ");
                int qty = sc.nextInt();
                sc.nextLine();

                if (price < 0 || qty < 0) {
                    System.out.println("Invalid input");
                } else {
                    addItem(code, name, price, qty);
                }
            }
            else if (ch == 2) {
                showAll();
            }
            else if (ch == 3) {
                sortByName();
            }
            else if (ch == 4) {
                sortByPrice();
            }
            else if (ch == 5) {
                sortByQty();
            }
            else if (ch == 6) {
                System.out.print("Enter limit: ");
                int limit = sc.nextInt();
                lowStock(limit);
            }
            else if (ch == 7) {
                System.out.print("Enter code to search: ");
                String code = sc.nextLine();
                search(code);
            }
            else if (ch == 8) {
                System.out.print("Enter code: ");
                String code = sc.nextLine();
                System.out.print("Enter new qty: ");
                int q = sc.nextInt();
                update(code, q);
            }
            else if (ch == 9) {
                System.out.println("Bye");
            }
            else {
                System.out.println("Wrong choice");
            }
        }
        sc.close();
    }
                        }
