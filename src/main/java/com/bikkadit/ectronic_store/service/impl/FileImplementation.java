package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class FileImplementation implements FileService {


    @Override
    public String uploadFile(MultipartFile file, String path) {
        return null;
    }

    @Override
    public InputStream getResource(String path, String name) {
        return null;
    }
}
