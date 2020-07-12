package com.scan4kids.project.controllers;

import com.scan4kids.project.daos.AlbumsRepository;
import com.scan4kids.project.daos.PhotosRepository;
import com.scan4kids.project.models.Photo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PhotoController {

    private AlbumsRepository albumsDao;
    private PhotosRepository photosDao;

    public PhotoController(AlbumsRepository albumsDao, PhotosRepository photosDao) {
        this.albumsDao = albumsDao;
        this.photosDao = photosDao;
    }

    @GetMapping("/albums/photos")
    public String viewAllPhotos(Model model) {
        List<Photo> photos = photosDao.findAll();
        model.addAttribute("photos", photos);
        model.addAttribute("noPhotosFound", photos.size() == 0);
        return "albums/photos";
    }

    @GetMapping("/albums/photos/{id}")
    public String showAPhoto(@PathVariable long id, Model model) {
        Photo photo = photosDao.getOne(id);
        model.addAttribute("photo", photo);
        model.addAttribute("photoId", id); //this is optional, just to have this attribute in case it needs to be used in the view at some point.
        return "albums/show";
    }
}
