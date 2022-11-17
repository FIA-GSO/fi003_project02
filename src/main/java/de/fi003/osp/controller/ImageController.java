package de.fi003.osp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import de.fi003.osp.entity.Image;

@Controller
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/{image}")
    public ResponseEntity<MultipartFile> main(@PathVariable String image) {
        return null;
        
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> login(@RequestBody Image image, @RequestParam("image") MultipartFile multipartFile) throws IOException{
        Path uploadPath = Paths.get("../upload/image/" + image.getName() + image.getType());
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(image.getName() + image.getType());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + image.getName() + image.getType(), ioe);
        }      
        return ResponseEntity.ok(image);
    }

    @PostMapping("/delete/{image}")
    public String register(@PathVariable String image){
        return image;

    }
}
