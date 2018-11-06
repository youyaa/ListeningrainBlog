package listeningrain.cn.blog.exception;

/**
 * User:        sunqingfeng6
 * Date:        2018/11/6 17:12
 * Description:
 */
public class StorageException extends RuntimeException {

    private static final long serialVersionUID = 2430191988074222554L;

    public StorageException(String message) {

        super(message);

    }

    public StorageException(String message, Throwable cause) {

        super(message, cause);

    }

}

