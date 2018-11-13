package listeningrain.cn.blog.utils;

import listeningrain.cn.blog.output.data.ContentsOutputData;
import listeningrain.cn.blog.output.data.MetasOutputData;
import listeningrain.cn.blog.output.data.UserShowInformationOutputData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/12 11:18
 * Description: 针对首页静态数据的缓存类
 */
public class CacheUtils {

    private static Map<Integer, ContentsOutputData> articleCache = null;
    private static UserShowInformationOutputData userShowInformationOutputData;
    private static Map<Integer,MetasOutputData> mottoCache = null;

    public static Integer times = null;

    public static boolean putMotto(Integer key, MetasOutputData value) {
        if (null == mottoCache) {
            mottoCache = new LinkedHashMap<>(8);
        }
        mottoCache.put(key, value);
        return true;
    }

    public static int getMottoSize(){
        return mottoCache.size();
    }

    public static boolean removeAllMotto(){
        if(null == mottoCache){
            return false;
        }else{
            mottoCache = null;
        }
        return true;
    }

    public static MetasOutputData getMotto() {
        if (null == mottoCache) {
            return null;
        } else {
            if (null == times) {
                times = 1;
            }
                MetasOutputData metasOutputData = mottoCache.get(times);
                times++;
                if (times > mottoCache.size()) {
                    times = null;
                }
                return metasOutputData;
            }
        }




    public static UserShowInformationOutputData getUserShowInformationOutputData() {
        return userShowInformationOutputData;
    }

    public static void setUserShowInformationOutputData(UserShowInformationOutputData userShowInformationOutputData) {
        CacheUtils.userShowInformationOutputData = userShowInformationOutputData;
    }

    public static boolean put(Integer key, ContentsOutputData value) {
        if (null == articleCache) {
            articleCache = new LinkedHashMap<>(8);
        }
        articleCache.put(key, value);
        return true;
    }

    public static ContentsOutputData get(Integer key) {
        if (null == articleCache) {
            return null;
        } else {
            return articleCache.get(key);
        }
    }

    public static List<ContentsOutputData> toList(){
        List<ContentsOutputData> list = new ArrayList<>(3);
        if(null != articleCache && 0 < articleCache.size()){
            for(Map.Entry entry : articleCache.entrySet()){
                list.add((ContentsOutputData) entry.getValue());
            }
        }
        return list;
    }
}
