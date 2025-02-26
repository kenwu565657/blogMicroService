package com.contentfarm.utils;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class ContentFarmFileTypeConvertUtils {
    private ContentFarmFileTypeConvertUtils() {}

    public static String markdownToHtml(String markdown) {
        var parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}
