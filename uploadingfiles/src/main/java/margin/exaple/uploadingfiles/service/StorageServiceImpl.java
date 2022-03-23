package margin.exaple.uploadingfiles.service;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService{

    private final Map<String, String> memoryRepository = new HashMap<>();


    @PostConstruct
    @Override
    public void init() {
        memoryRepository.put("path", "D:/example/test/");
        File file = new File(memoryRepository.get("path"));
        if(!file.exists()) file.mkdirs();

        System.out.println("[@PostConstruct]: set file path");
    }

    @Override
    public void store(MultipartFile multipartFile) {
        String path = memoryRepository.get("path");
        String fileName = multipartFile.getOriginalFilename();
        System.out.println("fileName = " + fileName);

        File file = new File(path, fileName);

        try {
            multipartFile.transferTo(file);

            System.out.println("store file successfully!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        Stream<Path> result = null;

        try {
            result = Files.list(Paths.get(memoryRepository.get("path")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Path load(String fileName) {

        return Paths.get(memoryRepository.get("path"));
    }

    @Override
    public Resource loadAsResource(String fileName) {
        File file = new File(memoryRepository.get("path"), fileName);
        ResourceLoader resourceLoader = new FileSystemResourceLoader();

        return resourceLoader.getResource(file.getPath());
    }

    @PreDestroy
    @Override
    public void deleteAll() {
        memoryRepository.clear();
        System.out.println("[PreDestroy]: clear memoryRepository map");
    }
}
