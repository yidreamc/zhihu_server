package tk.xmfaly.zhihu_server.service.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    private final StorageService storageService;

    @Autowired
    public FileUploadService(StorageService storageService){
        this.storageService = storageService;
    }

    public String upload(MultipartFile file){
        storageService.store(file,file.getName());
        return file.getName();
    }
}
