package ast;
public class Node {
    private String type;  // "operator" or "operand"
    private Node left;    // Left child node
    private Node right;   // Right child node
    private Object value; // Value for operand nodes (e.g., condition)

    public Node(String type, Node left, Node right, Object value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public String getType() { return type; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    public Object getValue() { return value; }
}
