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

    private void addBetween(Score s, Node predecessor, Node successor) {
        Node newest = new Node (s, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrevious(newest);
        size++;
    }

    public void addFirst(Score s) {
        addBetween(s, header, header.getNext());
    }

    public void addLast(Score s) {
        addBetween(s, trailer.getPrevious(), trailer);
    }

    public void addNode(Score s) {
        if (isEmpty()) {
            addFirst(s);
            return;
        }
        if (s.getScore() > 50) {
            Node current = header.getNext();
            if (current.getScore().getScore() <= s.getScore()) {
                addFirst(s);
                if (size() > 10) removeLast();
            }
            else {
                while (current.getNext() != trailer && s.getScore() <= current.getNext().getScore().getScore()) {
                    current = current.getNext();
                }
                if (current.getNext() == trailer) {
                    if (size() < 10) {
                        addLast(s);
                    }
                }
                else {
                    addBetween(s, current, current.getNext());
                    if (size() > 10) removeLast();
                }
            }
        }
        else {
            Node current = trailer.getPrevious();
            if (current.getScore().getScore() >= s.getScore()) {
                if (size() < 10) addLast(s);
            }
            else {
                while (current.getPrevious() != header && s.getScore() >= current.getPrevious().getScore().getScore()) {
                    current = current.getPrevious();
                }
                if (current.getPrevious() == header) {
                    addFirst(s);
                    if (size() > 10) removeLast();
                }
                else {
                    addBetween(s, current.getPrevious(), current);
                    if (size() > 10) removeLast();
                }
            }
        }
    }

    public void printLinkedList(){
        Node current = header.getNext();
        int count = 1;
        while (current != trailer){
            System.out.println(count + ": " + current.data.getScore());
            current = current.next;
            count++;
        }
    }

    private class Node {

        private Score data;
        private Node next, previous;

        public Node(Score s, Node previous, Node next) {
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