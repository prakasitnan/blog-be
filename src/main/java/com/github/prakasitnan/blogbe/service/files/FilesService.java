package com.github.prakasitnan.blogbe.service.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FilesService {
    @Value("${dir.root}")
    String ROOT_DIR;
    @Value("${dir.root.project}")
    String ROOT_PROJECT_DIR;


    public String upload(MultipartFile sourceFile, String dirName) {
        String location = null;
        // Check file
        if (sourceFile != null && !sourceFile.getOriginalFilename().equals("")) {

            // Create Directory
            String[] shotFile = {
                    dirName
            };
            String[] filePaths = {
                    ROOT_DIR,
                    ROOT_PROJECT_DIR,
                    dirName
            };
            new File(String.join(File.separator, filePaths));




            String originFileName = sourceFile.getOriginalFilename();
            String extension = originFileName.substring(originFileName.lastIndexOf("."));
            originFileName = System.currentTimeMillis() + extension;

            // Current File Location
            String fileLocation = String.join(File.separator, filePaths) + File.separator+ originFileName;


            try {
                sourceFile.transferTo(new File(fileLocation));
                location = String.join(File.separator, shotFile) + File.separator+ originFileName;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return location;
    }

    public String getFile(String pathFile) {


        return null;
    }

    public boolean deleteFile(String pathFile) {
        boolean result = false;
        if (pathFile != null) {
            String[] fileLocation = {ROOT_DIR, ROOT_PROJECT_DIR, pathFile};
            File file = new File(String.join(File.separator, fileLocation));
            if (file != null && file.exists()) {
                return file.delete();
            }
        }
        return result;
    }
}
