package com.scan4kids.project.controllers;


import com.scan4kids.project.daos.UsersRepository;
import com.scan4kids.project.exception.RecordNotFoundException;
import com.scan4kids.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.util.concurrent.Callable;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(value = "/users/{id}/submission")
@PropertySource("classpath:application.properties")
public class FileUploadController {

    @Autowired
    private UsersRepository repository;

    @Value("${file-upload-path}")
    private File uploadDirRoot;

    @Autowired
    FileUploadController(@Value("${image.upload.dir}") String uploadDir,
                            UsersRepository repository) {
        this.uploadDirRoot = new File(uploadDir);
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<Resource> read(@PathVariable Long id)
    {
        return this.repository.findById(id)
                .map(user->
                {
                    File file = fileFor(user);
                    Resource fileSystemResource = new FileSystemResource(file);
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(fileSystemResource);
                })
                .orElseThrow(() -> new RecordNotFoundException("Image for available"));
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT },
            consumes = { "multipart/form-data" })
    Callable<ResponseEntity<?>> write(@PathVariable Long id,
                                      @RequestParam("file") MultipartFile file) throws Exception
    {
        return () -> this.repository.findById(id)
                .map(user ->
                {
                    File fileForUser = fileFor(user);

                    try (InputStream in = file.getInputStream();
                         OutputStream out = new FileOutputStream(fileForUser))
                    {
                        FileCopyUtils.copy(in, out);
                    }
                    catch (IOException ex)
                    {
                        throw new RuntimeException(ex);
                    }

                    URI location = fromCurrentRequest().buildAndExpand(id).toUri();

                    return ResponseEntity.created(location).build();
                })
                .orElseThrow(() -> new RecordNotFoundException("Employee id is not present in database"));
    }

    private File fileFor(User e) {
        return new File(this.uploadDirRoot, Long.toString(e.getId()));
    }
}

