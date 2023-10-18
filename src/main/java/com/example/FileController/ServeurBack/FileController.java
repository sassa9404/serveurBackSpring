package com.example.FileController.ServeurBack;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("Le fichier est vide.", HttpStatus.BAD_REQUEST);
            }

            String fileName = file.getOriginalFilename();
            String filePath = "./uploads/" + fileName;

            File dest = new File(filePath);
            file.transferTo(dest);

            return new ResponseEntity<>("Le fichier a été reçu avec succès !", HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Erreur lors de la manipulation du fichier.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
