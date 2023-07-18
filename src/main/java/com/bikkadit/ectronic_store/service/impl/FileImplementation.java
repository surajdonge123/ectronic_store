package com.bikkadit.ectronic_store.service.impl;
import com.bikkadit.ectronic_store.exception.BadApiRequest;
import com.bikkadit.ectronic_store.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileImplementation implements FileService {

    public static Logger logger= LoggerFactory.getLogger(FileImplementation.class);

    /**
     * @author Suraj
     * @param file
     * @param path
     * @return
     * @apiNote uploading file
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
       String originalFileName=file.getOriginalFilename();
       logger.info("Filename : {}",originalFileName);
       String filename= UUID.randomUUID().toString();
       String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
       String filenameWithExtension=filename+extension;
       String fullPathWithFileName=path+File.separator+filenameWithExtension;

       if (extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".jpeg")){

           //File save
           File folder=new File(path);
           if (!folder.exists()){
               folder.mkdirs();
           }
           //upload file
           Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
           return filenameWithExtension;


       }else{
           throw new BadApiRequest("File with this "+extension+" is not allowed");
       }

    }

    /**
     * @author suraj
     * @param path
     * @param name
     * @return
     * @apiNote api for get resource
     * @throws FileNotFoundException
     */
    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {

        logger.info("initialising request for getResource :{}"+path);
        String fullPath=path+ File.separator+name;
        InputStream inputStream=new FileInputStream(fullPath);
        logger.info("request completed for getResource :{}"+inputStream);
        return inputStream;
    }
}
