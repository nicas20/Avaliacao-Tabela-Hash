package tree;

public class Tree {

    private Node root;

    public Tree() {
        this.root = null;
    }
    public void insert(int key, Object value) {
        this.root = this.insert(this.root, key, value);
    }

    private Node insert(Node root, int key, Object value) {
        if(root == null) {
            Node node = new Node();
            node.setKey(key);
            node.setValue(value);
            return node;
        } else {
            if(key < root.getKey()) {
                root.setLeft(this.insert(root.getLeft(), key, value));
            } else if(key > root.getKey()) {
                root.setRight(this.insert(root.getRight(), key, value));
            }
            return root;
        }
    }

    public Object get(int key) {
        return this.get(this.root, key);
    }

    private Object get(Node root, int key) {
        if(root != null) {
            if(key < root.getKey()) {
                return this.get(root.getLeft(), key);
            } else if(key > root.getKey()) {
                return this.get(root.getRight(), key);
            } else {
                return root.getValue();
            }
        }
        return null;
    }

    private String print(Node root, int lvl) {
        if (root == null) {
            return "";
        }

        String out = "";
        for (int i = 0; i < lvl; i++) {
            out += "\t";
        }
        out += root.getKey() + ": " + (root.getValue() != null ? root.getValue() : "null");
        out += "\n";
        out += (root.getLeft() != null ? print(root.getLeft(), lvl + 1) : "");
        out += (root.getRight() != null ? print(root.getRight(), lvl + 1) : "");
        return out;
    }


    private Node getNode(Node root, int key){
        if(root != null) {
            if(key < root.getKey()) {
                return this.getNode(root.getLeft(), key);
            } else if(key > root.getKey()) {
                return this.getNode(root.getRight(), key);
            } else {
                return root;
            }
        }
        return null;
    }

    private Node getParentNode(Node root, int key) {
        if (root != null) {
            if (key < root.getKey()) {
                if (root.getLeft() != null && root.getLeft().getKey() == key) {
                    return root;
                } else {
                    return getParentNode(root.getLeft(), key);
                }
            } else if (key > root.getKey()) {
                if (root.getRight() != null && root.getRight().getKey() == key) {
                    return root;
                } else {
                    return getParentNode(root.getRight(), key);
                }
            }
        }
        return null;
    }

    private Node findSuccessor(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private Object remove(int key) {
        Node node = getNode(this.root, key);
        if (node != null) {
            Node parentNode = getParentNode(this.root, key);
            if (parentNode == null) {
                this.root = removeNode(node);
            } else if (parentNode.getLeft() != null && parentNode.getLeft().getKey() == key) {
                parentNode.setLeft(removeNode(node));
            } else {
                parentNode.setRight(removeNode(node));
            }
            return node.getValue(); // Return the value of the removed node
        }
        return null;
    }



    private Node removeNode(Node node) {
        if (node.getLeft() == null && node.getRight() == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        } else {
            Node successor = findSuccessor(node.getRight());
            Node successorParent = getParentNode(node, successor.getKey());
            if (successorParent != node) {
                successorParent.setLeft(successor.getRight());
                successor.setRight(node.getRight());
            }
            successor.setLeft(node.getLeft());
            return successor;
        }
    }




    public void preOrder() {
        preOrderVisit(this.root);
    }

    private void preOrderVisit(Node node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrderVisit(node.getLeft());
            preOrderVisit(node.getRight());
        }
    }

    public void inOrder() {
        inOrderVisit(this.root);
    }

    private void inOrderVisit(Node node) {
        if (node != null) {
            inOrderVisit(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrderVisit(node.getRight());
        }
    }

    public void postOrder() {
        postOrderVisit(this.root);
    }

    private void postOrderVisit(Node node) {
        if (node != null) {
            postOrderVisit(node.getLeft());
            postOrderVisit(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }


    @Override
    public String toString() {
        return this.print(this.root, 0);
    }


    public static void main(String[] args) {
        Tree t = new Tree();

        t.insert(14, "14");
        t.insert(5, "5");
        t.insert(100, "100");
        t.insert(0, "0");
        t.insert(22, "22");
        t.insert(30, "30");
        t.insert(50, "50");

        System.out.println("Pre-order:");
        t.preOrder();
        System.out.println();

        System.out.println("In-order:");
        t.inOrder();
        System.out.println();

        System.out.println("Post-order:");
        t.postOrder();
        System.out.println();

        t.remove(5);
        t.remove(22);

        System.out.println("Pre-order:");
        t.preOrder();
        System.out.println();

        System.out.println("In-order:");
        t.inOrder();
        System.out.println();

        System.out.println("Post-order:");
        t.postOrder();
        System.out.println();

        t.remove(0);
        t.remove(100);
        t.remove(30);
        t.remove(14);
        t.remove(50);
        System.out.println("In-order (should be empty):");
        t.inOrder();
        System.out.println();
    }

}
