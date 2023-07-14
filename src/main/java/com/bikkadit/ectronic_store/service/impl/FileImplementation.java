package com.bikkadit.ectronic_store.service.impl;
import com.bikkadit.ectronic_store.exception.BadApiRequest;
import com.bikkadit.ectronic_store.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;
@Service
public class FileImplementation implements FileService {

    public static Logger logger= LoggerFactory.getLogger(FileImplementation.class);

    @Override
    public String uploadFile(MultipartFile file, String path) {
       String originalFileName=file.getOriginalFilename();
       logger.info("Filename : {}",originalFileName);
       String filename= UUID.randomUUID().toString();
       String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
       String filenameWithExtension=filename+extension;
       String fullPathWithFileName=path+File.separator+filenameWithExtension;

       if (extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".jpeg")){

       }else{
           throw new BadApiRequest("File with this "+extension+" is not allowed");

       }
        return null;
    }

    @Override
    public InputStream getResource(String path, String name) {
        return null;
    }
}
