public class DoubleLinkedList {

    private Node list;

    public DoubleLinkedList() {
        list = null;
    }

    public void addNode(Score s) {
        Node newNode = new Node(s);
        Node current = list;
        if (list == null){
            list = newNode;
        }
        else if (newNode.data.getScore() >= current.data.getScore()){
            list = newNode;
            newNode.next = current;
            if (countNodes(list) > 10) {
                removeLastNode();
            }
        }
        else {
            while (current.next != null && newNode.data.getScore() <= current.next.data.getScore()){
                current = current.next;
            }
            if (current.next == null) {
                if (countNodes(list) < 10) {
                    current.next = newNode;
                }
            }
            else {
                newNode.next = current.next;
                current.next = newNode;
                if (countNodes(list) > 10) {
                    removeLastNode();
                }
            }
        }
    }

    public void removeLastNode() {
        Node current = list;
        Node previous = list;
        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
    }

    public void printLinkedList(){
        Node current = list;
        int count = 1;
        while (current != null){
            System.out.println(count + ": " + current.data.getScore());
            current = current.next;
            count++;
        }
    }

    public int countNodes(Node list){
        if (list.next == null) {
            return 1;
        }else{
            return countNodes(list.next) + 1;
        }
    }

    private class Node{

        public Score data;
        private Node next;

        public Node(Score s){
            data = s;
            next = null;
        }
    }
}