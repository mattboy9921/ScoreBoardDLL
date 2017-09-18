public class DoubleLinkedList {

    private Node header, trailer;
    private int size = 0;

    public DoubleLinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.setNext(trailer);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Score first() {
        if (isEmpty()) return null;
        return header.getNext().getScore();
    }

    public Score last() {
        if (isEmpty()) return null;
        return trailer.getPrevious().getScore();
    }

    public void removeLast() {
        if (!isEmpty()) remove(trailer.getPrevious());
    }

    private void remove(Node node) {
        Node predecessor = node.getPrevious();
        Node successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrevious(predecessor);
        size--;
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

    public int averageScores() {
        Node current = list;
        int total = 0, count = 0;
        if (list == null) {
            return -1;
        }
        else if (current.next == null) {
            return current.data.getScore();
        }
        else {
            while (current != null) {
                total += current.data.getScore();
                count++;
                current = current.next;
            }
            return total / count;
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
        private Node next, previous;

        public Node(Node next, Node previous, Score s){
            data = s;
            this.next = next;
            this.previous = previous;
        }

        public Score getScore() {
            return data;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}