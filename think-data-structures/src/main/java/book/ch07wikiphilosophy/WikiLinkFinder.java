package book.ch07wikiphilosophy;

import book.websearchengine.DOMNodeIterable;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;


import java.util.StringTokenizer;

/*
 * 유효한 첫 번째 링크를 찾는 객체
 * 유효한 링크 기준
 * - TextNode 및 Element 노드에 대한 탐색
 *  - TextNode 객체는 단순히 텍스트만 존재 => 괄호 체크에만 사용
 *  - Element 노드에는 링크가 포함
 * - 링크가 이텔릭체이면 무시
 *  - 링크를 포함한 Element 요소의 부모 노드에 <i> 나 <em> 태그가 존재하면 이텔릭체
 * - 괄호 안에 있는 링크는 무시
 *  - numLeftParenthesis 추적
 * */

public class WikiLinkFinder {
    private final Elements paragraphs;
    private int numLeftParenthesis;

    public WikiLinkFinder(Elements paragraphs) {
        this.paragraphs = paragraphs;
        this.numLeftParenthesis = 0;
    }

    public Element findFirst() {
        for (Element element : paragraphs) {
            Element firstLink = findFirstLinkInParagraph(element);
            if (firstLink != null) return firstLink;

            // 각 단락의 괄호 짝이 맞지 않는 경우 잘못된 단락임을 알려줌
            if (numLeftParenthesis != 0) {
                System.err.println("[WARN] Wrong Parenthesis Pairs in this text node");
            }
        }
        return null;
    }

    private Element findFirstLinkInParagraph(Element paragraph) {
        Element ret;

        // 각 단락의 요소를 순회하는 이터레이터
        DOMNodeIterable itr = new DOMNodeIterable(paragraph);

        // 각 요소 탐색
        for (Node node : itr) {
            // TextNode : 괄호짝이 맞는지 테스트
            if (node instanceof TextNode) {
                checkParenthesis((TextNode) node);
            } else if (node instanceof Element) {
                ret = getFirstLinkInElement((Element) node);
                if (ret != null) return ret;
            }
        }

        return null;
    }

    private void checkParenthesis(TextNode node) {
        // 공백 및 괄호를 기준으로 토큰화
        StringTokenizer stringTokenizer = new StringTokenizer(node.text(), " ()", true);

        String curToken;
        while (stringTokenizer.hasMoreTokens()) {
            curToken = stringTokenizer.nextToken();

            if (curToken.equals("(")) {
                numLeftParenthesis++;
            } else if (curToken.equals(")")) {
                if (numLeftParenthesis == 0) {
                    System.err.println("[WARN] Wrong Parenthesis Pairs in this text node");
                } else {
                    numLeftParenthesis--;
                }
            }
        }
    }

    private Element getFirstLinkInElement(Element element) {
        if (isValidLink(element)) return element;
        return null;
    }

    private boolean isValidLink(Element element) {
        // a 태그가 아닌 경우 : 링크가 아님
        if (!element.tagName().equals("a")) return false;

        // 이텔릭체인 경우
        if (isItalic(element)) return false;

        // 괄호 안에 있는 경우
        if (isNowInParenthesis()) return false;

        // 링크 자체에 대한 검증
        // 레드 링크(#) 및 사이드 바 링크
        String aUrl = element.attr("href");
        if (aUrl.startsWith("#")) return false;
        if (aUrl.startsWith("wiki/Wikipedia:")) return false;

        return true;
    }

    private boolean isItalic(Element child) {
        // child 노드가 이텔릭체이면 무시 => 부모 노드 추적
        Element parent = child.parent();
        while (parent != null) {
            if (parent.tagName().equals("i") || parent.tagName().equals("em")) {
                return true;
            }
            parent = parent.parent();
        }
        return false;
    }

    private boolean isNowInParenthesis() {
        // 왼쪽 괄호가 닫히지 않았으면 괄호 안에 있는 것
        return numLeftParenthesis != 0;
    }

}
