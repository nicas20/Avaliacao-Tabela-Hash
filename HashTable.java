package Arvores;


public class HashTable {

    private Tree[] buckets;

    public HashTable() {
        this.buckets = new Tree[3];
        for (int i = 0; i < this.buckets.length; i++) {
            this.buckets[i] = new Tree();
        }
    }

    private int hash_function(int key) {
        return key % this.buckets.length;
    }

    public void put(int key, String value) {
        int b = this.hash_function(key);
        this.buckets[b].insert(key, value);
    }

    public int getLength(){return this.buckets.length;}

    public String get(int key) {
        int b = this.hash_function(key);
        return (String) this.buckets[b].get(key);
    }

    public Tree getTree(int key) {
        int b = this.hash_function(key);
        return this.buckets[b];
    }

    @Override
    public String toString() {
        String out = "{\n";
        for (int i = 0; i < this.buckets.length; i++) {
            out += "\t" + (this.buckets[i] != null ? this.buckets[i].toString() : "") + "\n";
        }
        return out + "}";
    }


    public static void main(String[] args) {
        HashTable ht = new HashTable();
        ht.put(0, "L");
        System.out.println(ht.getLength());
        System.out.println(ht.toString());
        ht.put(1, "U");
        ht.put(2, "C");
        ht.put(3, "A");
        ht.put(4, "S");
        ht.put(5, "H");
        System.out.println(ht.get(3));
        System.out.println(ht.toString());

        System.out.println("-------");
        Tree tree = ht.getTree(3);
        tree.inOrder();

    }
}
