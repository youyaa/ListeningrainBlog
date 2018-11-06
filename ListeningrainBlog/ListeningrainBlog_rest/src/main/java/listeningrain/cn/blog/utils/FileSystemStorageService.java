package listeningrain.cn.blog.utils;

import listeningrain.cn.blog.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Configuration
public class FileSystemStorageService implements StorageService {

    // 文件存储路径

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {

        this.rootLocation = Paths.get(storageProperties.getLocation());

    }

    @Override
    public void store(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("File is empty： " + filename);
            }
            if (filename.contains("..")) {
                // 文件路径安全校验
               throw new StorageException( "不能将文件保存到相对目录路径中"+ filename);
            }

            // 将上传的文件保存到指定位置
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),

                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new StorageException("上传文件失败 " + filename, e);
        }

    }

    /**
     * 加载所有的文件路径
     */

    @Override

    public Stream<Path> loadAll() {

        try {

            return Files.walk(this.rootLocation, 1)

                    .filter(path -> !path.equals(this.rootLocation))

                    .map(path -> this.rootLocation.relativize(path));

        } catch (IOException e) {

            //throw new StorageException("Failed to read stored files", e);

        }
        return null;
    }

    @Override

    public Path load(String filename) {

        return rootLocation.resolve(filename);

    }

    @Override

    public Resource loadAsResource(String filename) {

        try {

            Path file = load(filename);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {

                return resource;

            } else {

              //  throw new StorageFileNotFoundException(

                   //     "Could not read file: " + filename);
                return null;
            }

        } catch (MalformedURLException e) {

           // throw new StorageFileNotFoundException("Could not read file: " + filename, e);

        }
        return null;
    }

    @Override

    public void deleteAll() {

        FileSystemUtils.deleteRecursively(rootLocation.toFile());

    }

    @Override

    public void init() {

        try {

            Files.createDirectories(rootLocation);

        } catch (IOException e) {

            //throw new StorageException("Could not initialize storage", e);

        }

    }

}
