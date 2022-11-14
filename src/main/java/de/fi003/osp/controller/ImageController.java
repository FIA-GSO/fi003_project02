package de.fi003.osp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.fi003.osp.entity.Image;

@Controller
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/{image}")
    public String main(@PathVariable String image) {
        return "login";
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> login(@RequestBody Image image){
        return ResponseEntity.ok(image);

    }

    @PostMapping("/delete/{image}")
    public String register(@PathVariable String image){
        return image;

    }
}
