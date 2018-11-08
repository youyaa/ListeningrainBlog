package listeningrain.cn.blog.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private static String mdToHtml(String markdown) {
        if (StringUtils.isEmpty(markdown)) {
            return "";
        }
        List<Extension> extensions = Arrays.asList(new Extension[]{TablesExtension.create()});
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String content = renderer.render(document);
        return content;
    }

    //时间格式装换
    public static String formate(Timestamp created){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(created.getTime()));
        return format;
    }
}
