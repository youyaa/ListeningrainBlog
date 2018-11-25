package listeningrain.cn.blog.utils;


import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import listeningrain.cn.blog.output.data.ContentsOutputData;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * author: listeningrain
 * Date: 2018/10/2
 * Time: 15:17
 * Description: 对文章内容进行格式化的工具类
 */
public class ThemeUtils {

    public static String articleTransfer(String content){
        if (!StringUtils.isEmpty(content)){
            content = content.replace("<!--more-->", "\r\n");
            return mdToHtml(content);
         }
        return "";
    }

    //将markdown格式的语法转成html
    private static String mdToHtml(String content) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[] { TablesExtension.create()}));
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(content);
        String html = renderer.render(document);
        return html;
    }

    //对文章内容进行截取,截取120个字符
    public static String cutArticle(ContentsOutputData data){
        String content = data.getContent();
        content = htmlToText(content);
        int length = content.length();
        if(length <= 120){
            return content;
        }
        return content.substring(0,120)+"  ......";
    }

    /**
     * 提取html中的文字
     */
    public static String htmlToText(String html) {
        if (!StringUtils.isEmpty(html)) {
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }
}
