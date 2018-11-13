# Host: localhost  (Version 5.6.17)
# Date: 2018-11-13 17:52:51
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "comments"
#

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'comment表主键',
  `cid` int(10) unsigned DEFAULT '0' COMMENT 'content表主键,关联字段',
  `parent` int(10) DEFAULT '0' COMMENT '父级评论',
  `top_level_id` int(11) DEFAULT NULL COMMENT '一级评论的id',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论生成时的GMT unix时间戳',
  `author` varchar(200) DEFAULT NULL COMMENT '评论作者',
  `avator` varchar(100) DEFAULT NULL COMMENT '头像地址',
  `author_id` int(10) unsigned DEFAULT '0' COMMENT '评论所属用户id',
  `owner_id` int(10) unsigned DEFAULT '0' COMMENT '评论所属内容作者id',
  `mail` varchar(200) DEFAULT NULL COMMENT '评论者邮件',
  `url` varchar(200) DEFAULT NULL COMMENT '评论者网址',
  `ip` varchar(64) DEFAULT NULL COMMENT '评论者ip地址',
  `agent` varchar(200) DEFAULT NULL COMMENT '评论者客户端',
  `content` text COMMENT '评论内容',
  `type` varchar(16) DEFAULT 'comment' COMMENT '评论类型',
  `status` varchar(16) DEFAULT 'approved' COMMENT '评论状态',
  `os_name` varchar(20) DEFAULT NULL COMMENT '评论人的操作系统',
  `address` varchar(20) DEFAULT NULL COMMENT '评论人的地址',
  `browser` varchar(10) DEFAULT NULL COMMENT '评论人的浏览器',
  PRIMARY KEY (`coid`),
  KEY `cid` (`cid`),
  KEY `created` (`created`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='评论表';

#
# Data for table "comments"
#

/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,157,0,-1,'2018-11-12 14:21:24','测试人员',NULL,0,0,NULL,NULL,NULL,NULL,'你你你你你是不是傻啊啊啊','comment','approved',NULL,NULL,NULL),(2,157,0,-1,'2018-11-13 15:45:43',' 测试作者',NULL,0,0,NULL,NULL,NULL,NULL,'这是我的测试内容','comment','approved',NULL,NULL,NULL),(3,157,2,2,'2018-11-13 16:39:37','二级评论',NULL,0,0,NULL,NULL,NULL,NULL,'我是测试作者的回复','comment','approved',NULL,NULL,NULL),(4,157,3,2,'2018-11-13 16:50:46','我是三级评论',NULL,0,0,NULL,NULL,NULL,NULL,'我是二级评论的回复','comment','approved',NULL,NULL,NULL),(5,157,1,1,'2018-11-13 17:02:58','我是二二二级评论',NULL,0,0,NULL,NULL,NULL,NULL,'我我我我是二级评论','comment','approved',NULL,NULL,NULL),(6,157,5,1,'2018-11-13 17:05:58','我是三三三级评论',NULL,0,0,NULL,NULL,NULL,NULL,'我是三三三级评论啊啊啊啊','comment','approved',NULL,NULL,NULL),(7,156,0,-1,'2018-11-13 17:09:29','wowowowowo',NULL,0,0,NULL,NULL,NULL,NULL,'wowowowoowow','comment','approved',NULL,NULL,NULL),(8,156,7,7,'2018-11-13 17:18:14','ninininini',NULL,0,0,NULL,NULL,NULL,NULL,'nininininini','comment','approved',NULL,NULL,NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

#
# Structure for table "contents"
#

DROP TABLE IF EXISTS `contents`;
CREATE TABLE `contents` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'post表主键',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `slug` varchar(200) DEFAULT NULL COMMENT '内容缩略名',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '内容生成时的GMT unix时间戳',
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '内容更改时的GMT unix时间戳',
  `content` text COMMENT '内容文字',
  `author_id` int(10) unsigned DEFAULT '0' COMMENT '内容所属用户id',
  `type` varchar(16) DEFAULT 'md' COMMENT '内容类别',
  `status` varchar(16) DEFAULT 'publish' COMMENT '内容状态',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签列表',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类列表',
  `hits` int(10) unsigned DEFAULT '0' COMMENT '点击次数',
  `comments_num` int(10) unsigned DEFAULT '0' COMMENT '内容所属评论数',
  `allow_comment` tinyint(1) DEFAULT '1' COMMENT '是否允许评论',
  `allow_ping` tinyint(1) DEFAULT '1' COMMENT '是否允许ping',
  `allow_feed` tinyint(1) DEFAULT '1' COMMENT '允许出现在聚合中',
  `address` varchar(30) DEFAULT NULL COMMENT '评论时的地址',
  `os_name` varchar(10) DEFAULT NULL COMMENT '评论人的操作系统',
  `browser` varchar(10) DEFAULT NULL COMMENT '评论人的浏览器',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=MyISAM AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='内容表';

#
# Data for table "contents"
#

/*!40000 ALTER TABLE `contents` DISABLE KEYS */;
INSERT INTO `contents` VALUES (149,'测试草稿',NULL,'2018-11-08 09:56:31','2018-11-12 08:57:54','NIO（Non-blocking I/O，在Java领域，也称为New I/O），是一种同步非阻塞的I/O模型，也是I/O多路复用的基础，已经被越来越多地应用到大型应用服务器，成为解决高并发与大量连接、I/O处理问题的有效方式。\n\n那么NIO的本质是什么样的呢？它是怎样与事件模型结合来解放线程、提高系统吞吐的呢？\n\n本文会从传统的阻塞I/O和线程池模型面临的问题讲起，然后对比几种常见I/O模型，一步步分析NIO怎么利用事件模型处理I/O，解决线程池瓶颈处理海量连接，包括利用面向事件的方式编写服务端/客户端程序。最后延展到一些高级主题，如Reactor与Proactor模型的对比、Selector的唤醒、Buffer的选择等。',0,'md','publish','测试测试','34',0,0,1,1,1,NULL,NULL,NULL),(150,'我我我我我',NULL,'2018-11-08 10:16:51','2018-11-08 11:07:32','我就是来写个代码哟哟哟！！！\n\n    package listeningrain.cn.blog;\n    \n    import listeningrain.cn.blog.utils.StorageProperties;\n    import listeningrain.cn.blog.utils.StorageService;\n    import org.springframework.boot.CommandLineRunner;\n    import org.springframework.boot.SpringApplication;\n    import org.springframework.boot.autoconfigure.EnableAutoConfiguration;\n    import org.springframework.boot.autoconfigure.SpringBootApplication;\n    import org.springframework.boot.context.properties.EnableConfigurationProperties;\n    import org.springframework.context.annotation.Bean;\n    import org.springframework.context.annotation.ImportResource;\n    \n    import java.text.SimpleDateFormat;\n    import java.util.Date;\n    \n    /**\n     * author: listeningrain\n     * Date: 2018/9/16\n     * Time: 15:06\n     * Description:Rest工程启动类\n     */\n    @SpringBootApplication\n    @EnableAutoConfiguration\n    @ImportResource({\"classpath:application-consumer.xml\"})\n    @EnableConfigurationProperties(StorageProperties.class)\n    public class Application {\n    \n        public static void main(String[] args){\n            printToConsole(\"开始启动listeningrainBlog-rest模块\");\n            SpringApplication.run(Application.class);\n            printToConsole(\"启动listeningrainBlog-rest模块成功\");\n        }\n    \n        @Bean\n        CommandLineRunner init(StorageService storageService) {\n            return (args) -> {\n                storageService.deleteAll();\n                storageService.init();\n            };\n        }\n    \n    \n        private static void printToConsole(String s) {\n            if (null != s && s.length() > 0) {\n                String time = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").format(new Date());\n                System.out.println(time + \" \" + s);\n            } else {\n                System.out.println(s);\n            }\n        }\n    }\n    ',0,'md','publish','我我我','35',0,0,1,1,1,NULL,NULL,NULL),(151,'测测测测测',NULL,'2018-11-08 10:43:04','2018-11-08 11:06:25','> 代码即人生\n```html\n <div class=\"main\">\n            <div class=\"login\">\n                <div class=\"logo\">\n                    <h2 style=\"padding-top: 40px\">Listeningrain Blog</h2>\n                </div>\n                <div class=\"form\" >\n                    <form id=\"login_form\" method=\"post\", action=\"/admin/login\">\n                        <div class=\"form-group input-group-lg login-input\" style=\"padding-top: 50px;\">\n                            <input name=\"username\" type=\"text\" class=\"form-control\" placeholder=\"请输入账号(>▽<)\">\n                        </div>\n\n                        <div class=\"form-group input-group-lg login-input\">\n                            <input name=\"password\" type=\"password\" class=\"form-control\" placeholder=\"请输入密码(＞﹏＜)\">\n                        </div>\n\n                        <button id=\"bbb\" class=\"btn login-input btn-danger btn-lg btn-rounded btn-block w-lg waves-effect waves-light\" style=\"box-shadow: 0px 0px 4px #868282;\" type=\"submit\">登&nbsp;录\n                        </button>\n                    </form>\n                </div>\n\n            </div>\n    </div>\n\n```',0,'md','publish','哈哈哈哈','34',0,0,1,1,1,NULL,NULL,NULL),(152,'测试下HTML的编辑',NULL,'2018-10-08 11:03:55','2018-11-08 12:38:08','<p>在这里开始写您的文章哦</p><pre><code>//分页查询<br>    public PageOutputDTO&lt;ContentsOutputData&gt; getContentsByPage(PageInputDTO&lt;ContentsInputData&gt; pageInputDTO){<br>        Contents content = new Contents();<br>        if(null != pageInputDTO &amp;&amp; null != pageInputDTO.getData()){<br>            BeanUtils.copyProperties(pageInputDTO.getData(),content);<br>        }<br>        PageHelper.startPage(pageInputDTO.getPageNum(),pageInputDTO.getPageSize());<br>        List&lt;Contents&gt; contentsByPage = atomContensService.getContentsByPage(content);<br>        PageInfo&lt;Contents&gt; of = PageInfo.of(contentsByPage);<br>        PageOutputDTO&lt;ContentsOutputData&gt; pageOutputDTO = new PageOutputDTO&lt;&gt;();<br><br>        List&lt;ContentsOutputData&gt; list = new ArrayList&lt;&gt;();</code></pre><p>我我我写个屁</p>',0,'html','publish','123','37',0,0,1,1,1,NULL,NULL,NULL),(153,'测试测试',NULL,'2018-11-08 12:43:21','2018-11-08 12:45:08','![mahua](http://mahua.jser.me/mahua-logo.jpg)\n\n## MaHua是什么?\n一个在线编辑markdown文档的编辑器\n\n向Mac下优秀的markdown编辑器mou致敬\n\n## MaHua有哪些功能？\n\n* 方便的`导入导出`功能\n    *  直接把一个markdown的文本文件拖放到当前这个页面就可以了\n    *  导出为一个html格式的文件，样式一点也不会丢失\n* 编辑和预览`同步滚动`，所见即所得（右上角设置）\n* `VIM快捷键`支持，方便vim党们快速的操作 （右上角设置）\n* 强大的`自定义CSS`功能，方便定制自己的展示\n* 有数量也有质量的`主题`,编辑器和预览区域\n* 完美兼容`Github`的markdown语法\n* 预览区域`代码高亮`\n* 所有选项自动记忆\n\n## 有问题反馈\n在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流\n\n* 邮件(dev.hubo#gmail.com, 把#换成@)\n* QQ: 287759234\n* weibo: [@草依山](http://weibo.com/ihubo)\n* twitter: [@ihubo](http://twitter.com/ihubo)\n\n## 捐助开发者\n在兴趣的驱动下,写一个`免费`的东西，有欣喜，也还有汗水，希望你喜欢我的作品，同时也能支持一下。\n当然，有钱捧个钱场（右上角的爱心标志，支持支付宝和PayPal捐助），没钱捧个人场，谢谢各位。\n\n## 感激\n感谢以下的项目,排名不分先后\n\n* [mou](http://mouapp.com/) \n* [ace](http://ace.ajax.org/)\n* [jquery](http://jquery.com)\n\n## 关于作者\n\n```javascript\n  var ihubo = {\n    nickName  : \"草依山\",\n    site : \"http://jser.me\"\n  }\n```',0,'md','publish','测试测试','34',0,0,1,1,1,NULL,NULL,NULL),(154,'测试测试富文本代码',NULL,'2018-11-09 16:34:21','2018-11-09 16:34:21','<p>在这里开始写您的文章哦</p><p>是是是，谢谢谢，哈哈哈</p><pre><code>package listeningrain.cn.blog.utils;<br><br><br>import com.vladsch.flexmark.Extension;<br>import com.vladsch.flexmark.ast.Node;<br>import com.vladsch.flexmark.ext.tables.TablesExtension;<br>import com.vladsch.flexmark.html.HtmlRenderer;<br>import com.vladsch.flexmark.parser.Parser;<br>import com.vladsch.flexmark.parser.ParserEmulationProfile;<br>import com.vladsch.flexmark.util.options.MutableDataSet;<br>import org.springframework.util.StringUtils;<br><br>import java.util.Arrays;<br><br>/**<br> * author: listeningrain<br> * Date: 2018/10/2<br> * Time: 15:17<br> * Description: 对文章内容进行格式化的工具类<br> */<br>public class ThemeUtils {<br><br>    public static String articleTransfer(String content){<br>        if (!StringUtils.isEmpty(content)){<br>            content = content.replace(\"&lt;!--more--&gt;\", \"\\r\\n\");<br>            return mdToHtml(content);<br>         }<br>        return \"\";<br>    }<br><br>    //将markdown格式的语法转成html<br>    private static String mdToHtml(String content) {<br>        MutableDataSet options = new MutableDataSet();<br>        options.setFrom(ParserEmulationProfile.MARKDOWN);<br>        options.set(Parser.EXTENSIONS, Arrays.asList(new Extension[] { TablesExtension.create()}));<br>        Parser parser = Parser.builder(options).build();<br>        HtmlRenderer renderer = HtmlRenderer.builder(options).build();<br><br>        Node document = parser.parse(content);<br>        String html = renderer.render(document);<br>        return html;<br>    }<br><br>    //对文章内容进行截取,截取80%的字符<br>    public static String cutArticle(String content){<br>        int length = content.length();<br>        return content.substring(0,(int)(length*0.7))+\"   ......\";<br>    }<br>}</code></pre><p><br></p>',0,'html','publish','Java','35',0,0,1,1,1,NULL,NULL,NULL),(156,'测试markdown代码代码',NULL,'2018-11-09 16:40:37','2018-11-12 08:58:17','NIO（Non-blocking I/O，在Java领域，也称为New I/O），是一种同步非阻塞的I/O模型，也是I/O多路复用的基础，已经被越来越多地应用到大型应用服务器，成为解决高并发与大量连接、I/O处理问题的有效方式。\n\n那么NIO的本质是什么样的呢？它是怎样与事件模型结合来解放线程、提高系统吞吐的呢？\n\n本文会从传统的阻塞I/O和线程池模型面临的问题讲起，然后对比几种常见I/O模型，一步步分析NIO怎么利用事件模型处理I/O，解决线程池瓶颈处理海量连接，包括利用面向事件的方式编写服务端/客户端程序。最后延展到一些高级主题，如Reactor与Proactor模型的对比、Selector的唤醒、Buffer的选择等。',0,'md','publish','Java','35',0,0,1,1,1,NULL,NULL,NULL),(157,'测试下标题',NULL,'2018-11-12 16:05:08','2018-11-13 15:18:10','# 测试标题\n## 测试标题\n### 测试标题\n\njava编程中，使用反射来增强灵活性（如各类框架）、某些抽象（如各类框架）及减少样板代码（如Java Bean）。\n因此，反射在实际的java项目中被大量使用。\n\n由于项目里存在反射的性能瓶颈，使用的是ReflectASM高性能反射库来优化。\n因此，在空闲时间研究了下的这个库，并做了简单的Beachmark。\n\n',0,'md','publish','屁屁屁','34',0,1,1,1,1,NULL,NULL,NULL),(158,'测试新模板',NULL,'2018-11-13 17:39:12','2018-11-13 17:39:12','<p>在这里开始写您的文章哦</p><p style=\"text-align: center;\">哈哈</p><p style=\"text-align: center;\">哈哈</p><p style=\"text-align: center;\">哈哈</p><p style=\"text-align: center;\">测试图片</p><p style=\"text-align: center;\"><img src=\"http://localhost:8000/upload-dir/Chrysanthemum.jpg\" style=\"max-width:30%;\"></p>',0,'html','publish','测试测试','35',0,0,1,1,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `contents` ENABLE KEYS */;

#
# Structure for table "metas"
#

DROP TABLE IF EXISTS `metas`;
CREATE TABLE `metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '项目主键',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `slug` varchar(200) DEFAULT NULL COMMENT '项目缩略名',
  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '项目类型',
  `description` varchar(200) DEFAULT NULL COMMENT '选项描述',
  `sort` int(10) unsigned DEFAULT '0' COMMENT '项目排序',
  `parent` int(10) unsigned DEFAULT '0',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '状态',
  `content` text,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='项目';

#
# Data for table "metas"
#

INSERT INTO `metas` VALUES (1,'关于',NULL,'ABOUT',NULL,0,0,NULL,'## Hello World\n\n      这是程序员专用打招呼用语，你懂的！\n各位好，我是一个来自湖北荆州的男生，在武汉上大学，今年大三，当然啦，\n我同时也是一个程序员，也就是大家所熟知的码农，从学习编程到现在也有一段时间了，\n课上课下都有学，不过学得最多的应该就是Java了，以后找工作应该也是java了，我也期待着自己在java的领域中越来越厉害，想想自己的自学之路，其实是很愉快的，但是吧，确实是碰到了一些坑，\n有些跨过去了，有些一直是放着，最近才想着把自己学习的经历记录下来，不管怎么说，\n留作纪念也好，总结也罢，反正从今天起开始写博客了，欢迎大家前来访问,\n如果能给大家带来一些帮助那就更好了，哈哈！另外，这是我的 <a href=\"https://github.com/youyaa\" style=\"color:red\" target=\"_blank\"> github </a> ，我也会写一些东西上传到github，与大家分享。 \n\n## 关于本博客\n       这个博客的名字叫 Tale，意为故事，作者觉得每个写博客的人都是有故事的，所以起名Tale。\n这个博客是 <a href=\"https://github.com/biezhi\" style=\"color:red\" target=\"_blank\">@王爵nice</a> \n开发的，他是个很厉害的程序员，写了很多开源的框架，\n这个Tale（塌了）博客就是基于他写的开源框架Blade开发的，\n仰望高端玩家，同时他的境界也是我追求的目标，虽然还差得远，\n嘿嘿，梦想还是要有的，大家可以去看看他的博客或者github，\n一定可以学到很多东西。','2018-11-12 14:21:24'),(25,'静心听雨的博客','https://listeningrain.cn','LINK',NULL,0,0,NULL,NULL,'2018-11-12 14:21:24'),(26,'静心听雨的github','https://github.com/youyaa','LINK',NULL,1,0,NULL,NULL,'2018-11-12 14:21:24'),(28,'古哥','https://google.com','LINK',NULL,3,0,NULL,NULL,'2018-11-12 14:21:24'),(29,'推特','https://twitter.com','LINK',NULL,4,0,NULL,NULL,'2018-11-12 14:21:24'),(31,'非死不可','https://facebook.com','LINK',NULL,5,0,NULL,NULL,'2018-11-12 14:21:24'),(32,'哥特哈不','https://github.com','LINK',NULL,6,0,NULL,NULL,'2018-11-12 14:21:24'),(33,'默认分类',NULL,'CATEGORY',NULL,0,0,NULL,NULL,'2018-11-12 14:21:24'),(34,'默认分类',NULL,'CLASSIFY','系统自带的分类',0,0,NULL,NULL,'2018-11-12 14:21:24'),(35,'Java学习',NULL,'CLASSIFY','我的自学之路',1,0,NULL,NULL,'2018-11-12 14:21:24'),(36,'感受',NULL,'CLASSIFY','生活的记录',2,0,NULL,NULL,'2018-11-12 14:21:24'),(37,'我的分类',NULL,'CLASSIFY','不知道写啥',3,0,NULL,NULL,'2018-11-12 14:21:24'),(38,'屁屁屁',NULL,'CLASSIFY','写写写写个屁屁屁',4,0,NULL,NULL,'2018-11-12 14:21:24'),(39,'测试分类',NULL,'CLASSIFY','这个测试分类用例',5,0,NULL,NULL,'2018-11-12 14:21:24'),(40,'许嵩',NULL,'MOTTO','最爱的一句话',0,0,NULL,'如果你回头，不要放下我。。。','2018-11-12 18:08:14'),(44,'秦时明月',NULL,'MOTTO','我我我我我11',0,0,NULL,'看流沙聚散。。。','2018-11-12 18:42:14'),(45,'赤炼(秦时明月)','','MOTTO','哇哈哈哈1111',0,0,NULL,'只愿陪你到世界的尽头。。。','2018-11-13 08:26:40'),(47,'歌词',NULL,'MOTTO','哈哈',0,0,NULL,'哦，你的烂借口','2018-11-13 09:43:44'),(48,'测试测试测试',NULL,'MOTTO','1111111111',0,0,NULL,'测死测试测试测试内容','2018-11-13 12:49:37');

#
# Structure for table "tags"
#

DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` varchar(64) NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '标签名',
  `tid` varchar(64) DEFAULT NULL,
  `state` tinyint(4) NOT NULL COMMENT '1:正常 0:删除',
  `created` int(10) DEFAULT NULL COMMENT '标签创建时间',
  `updated` int(10) DEFAULT NULL COMMENT '最后更新的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

#
# Data for table "tags"
#

INSERT INTO `tags` VALUES ('','123123',NULL,0,NULL,NULL);

#
# Structure for table "user_show_information"
#

DROP TABLE IF EXISTS `user_show_information`;
CREATE TABLE `user_show_information` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '用户的id',
  `nickname` varchar(10) DEFAULT NULL COMMENT '昵称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `address` varchar(50) DEFAULT NULL COMMENT '家庭住址',
  `famous_says` varchar(50) DEFAULT NULL COMMENT '座右铭',
  `follower` int(11) DEFAULT NULL COMMENT '粉丝',
  `touxiang` varchar(80) DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='个人信息配置页';

#
# Data for table "user_show_information"
#

INSERT INTO `user_show_information` VALUES (1,123,'静心听雨',NULL,NULL,'做一个阳光的男生',NULL,NULL);

#
# Structure for table "users"
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'user表主键',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(200) DEFAULT NULL COMMENT '用户的邮箱',
  `home_url` varchar(200) DEFAULT NULL COMMENT '用户的主页',
  `screen_name` varchar(32) DEFAULT NULL COMMENT '用户显示的名称',
  `created` int(10) unsigned DEFAULT '0' COMMENT '用户注册时的GMT unix时间戳',
  `activated` int(10) unsigned DEFAULT '0' COMMENT '最后活动时间',
  `logged` int(10) unsigned DEFAULT '0' COMMENT '上次登录最后活跃时间',
  `group_name` varchar(16) DEFAULT 'visitor' COMMENT '用户组',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`username`),
  UNIQUE KEY `mail` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "users"
#

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'root','cm9vdDEyMzQ1',NULL,NULL,NULL,6,1,7,'visitor'),(2,'哇哈哈哈111',NULL,NULL,NULL,NULL,0,0,0,'visitor');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
