package com.cruise.thinking.in.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 动态（更新）资源 {@link MessageSource} 实现
 * <p>实现步骤</p>
 * <ul>
 *     <li>定位资源位置</li>
 *     <li>初始化 messageProperties</li>
 *     <li>实现 {@link AbstractMessageSource#resolveCode(String, Locale)} 方法</li>
 *     <li>监听资源文件 Java NIO WatchService</li>
 *     <li>使用线程池处理文件变化</li>
 * </ul>
 *
 * @author Cruise
 * @version 1.0
 * @see MessageSource
 * @see AbstractMessageSource
 * @since 2020/7/8
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    private Resource messageResource;

    private static final String FILE_NAME = "msg.properties";

    private static final String PATH = "META-INF/" + FILE_NAME;

    private static final String ENCODING = "UTF-8";

    private final Properties messageProperties;

    private final ExecutorService executorService;


    public DynamicResourceMessageSource() {
        this.messageResource = loadResource();
        this.messageProperties = loadProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        // 判断是否是文件
        if (this.messageResource.isFile()) {
            try {
                // 获取对应文件系统中的文件
                File file = this.messageResource.getFile();
                Path path = file.toPath();
                // 获取当前 OS 文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                // 创建一个 WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 获取当前文件的所在目录
                Path dir = path.getParent();
                // 注册监听所在目录的修改事件
                dir.register(watchService, ENTRY_MODIFY);
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 处理资源文件变化（异步）
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take();
                try {
                    // 是否有效
                    if (watchKey.isValid()) {
                        for (WatchEvent event : watchKey.pollEvents()) {
                            // 监听的目录路径
                            Path dirPath = (Path) watchKey.watchable();
                            // 文件的相对路径
                            Path fileRelativePath = (Path) event.context();
                            // 文件的绝对路径
                            Path filePath = dirPath.resolve(fileRelativePath);
                            if (FILE_NAME.equals(filePath.getFileName())) {
                                File file = filePath.toFile();
                                //Properties properties = loadProperties(new FileReader(file));
                                Reader reader = new InputStreamReader(new FileInputStream(file), ENCODING);
                                Properties properties = loadProperties(reader);
                                if (properties != null) {
                                    synchronized (this.messageProperties) {
                                        this.messageProperties.clear();
                                        this.messageProperties.putAll(properties);
                                    }
                                }
                            }

                        }
                    }
                } finally {
                    if (watchKey != null) {
                        // 重置
                        watchKey.reset();
                    }
                }
            }
        });
    }


    public Properties loadProperties() {
        EncodedResource encodedResource = new EncodedResource(this.messageResource, ENCODING);
        try {
            Reader reader = encodedResource.getReader();
            return loadProperties(reader);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Properties loadProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    private Resource loadResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        return resourceLoader.getResource(PATH);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        final String pattern = this.messageProperties.getProperty(code);
        if (StringUtils.hasText(pattern)) {
            return new MessageFormat(pattern, locale);
        }
        return null;
    }

    public ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource dynamicResourceMessageSource = new DynamicResourceMessageSource();
        for (int i = 0; i < 1000; i++) {
            String message = dynamicResourceMessageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
