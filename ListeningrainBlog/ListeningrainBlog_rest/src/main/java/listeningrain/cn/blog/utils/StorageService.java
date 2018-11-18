package listeningrain.cn.blog.utils;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/6 17:00
 * Description:
 */
public interface StorageService {

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
