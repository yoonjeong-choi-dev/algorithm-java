package book.ch06TreeSearch;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class JsoupExample {
    public void simpleExample() throws IOException {
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        // GET url 및 DOM 파싱
        Connection conn = Jsoup.connect(url);
        Document doc = conn.get();

        // 내용 추출
        Element content = doc.getElementById("mw-content-text");
        Elements paragraphs = content.select("p");
        Element firstParagraph = paragraphs.get(1);

        // 첫 번째 단락에 대한 DOM 트리 탐색
        System.out.println("===========================================");
        System.out.println("Recursive DFS");
        System.out.println("===========================================");
        recursiveDFS(firstParagraph);

        System.out.println("===========================================");
        System.out.println("Iterative DFS with stack");
        System.out.println("===========================================");
        stackDFS(firstParagraph);

    }

    private void recursiveDFS(Node node) {
        if(node instanceof TextNode) {
            System.out.print(node);
        }

        // 자식이 노드들에 대해서 재귀 호출(pre-order)
        for(Node child : node.childNodes()) {
            recursiveDFS(child);
        }
    }

    private void stackDFS(Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        // 스택에는 pre-order 순서로 순회하면서 방문해야 할 자손 노드 저장
        while(!stack.isEmpty()) {
            // 스택의 top 요소 반환 및 출력
            Node node = stack.pop();
            if(node instanceof TextNode) {
                System.out.print(node);
            }

            // top 요소의 자식들을 스택에 저장 i.e DFS
            // 가장 마지막에 첫번째 자식이 저장되어야 하므로 자식 노드 리스트를 뒤집어 준다
            List<Node> childNodes = new ArrayList<>(node.childNodes());
            Collections.reverse(childNodes);
            for(Node child : childNodes) {
                stack.push(child);
            }
        }
    }

    public static void main(String[] args) {
        JsoupExample ex = new JsoupExample();

        try {
            ex.simpleExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
