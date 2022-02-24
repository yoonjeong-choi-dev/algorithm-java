package book.websearchengine;

import org.jsoup.nodes.Node;

import java.util.*;

public class DOMNodeIterable implements Iterable<Node> {

    // 루트 노드 저장
    private final Node root;

    public DOMNodeIterable(Node root) {
        this.root = root;
    }

    @Override
    public Iterator<Node> iterator() {
        return new DOMNodeIterator(root);
    }

    // Inner Class for iterator
    private class DOMNodeIterator implements Iterator<Node> {
        Deque<Node> stack;

        public DOMNodeIterator(Node node) {
            stack = new ArrayDeque<>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            // 스택이 비어있으면 호출 예외
            if(stack.isEmpty()) {
                throw new NoSuchElementException();
            }

            // 스택의 top 요소 반환
            Node node = stack.pop();

            // top 요소의 자식들을 스택에 저장 i.e DFS with pre-order
            // 가장 마지막에 첫번째 자식이 저장되어야 하므로 자식 노드 리스트를 뒤집어 준다
            List<Node> childNodes = new ArrayList<>(node.childNodes());
            Collections.reverse(childNodes);
            for(Node child : childNodes) {
                stack.push(child);
            }

            return node;
        }

        @Override
        public void remove() {
            // 크롤러에서는 읽기만 하기 때문에 DOM 트리 조작 X
            throw new UnsupportedOperationException();
        }
    }

}
