package com.scan4kids.project.controllers;


import com.scan4kids.project.daos.AlbumsRepository;
import com.scan4kids.project.daos.PhotosRepository;
import com.scan4kids.project.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AlbumController {

    private AlbumsRepository albumsDao;
    private PhotosRepository photosDao;

    public AlbumController(AlbumsRepository albumsRepository, PhotosRepository photosDao) {
        this.albumsDao = albumsRepository;
        this.photosDao = photosDao;
    }

    @GetMapping("/albums")
    public String albumIndex(Model model) {
        List<Album> albums = albumsDao.findAll();

        for(Album album: albums) {
            boolean noPhoto = album.getPhotos().size() == 0;
            boolean somePhotos = (album.getPhotos().size() == 1) || (album.getPhotos().size() == 2);
            boolean enoughForCarousel = album.getPhotos().size() >= 3;
            model.addAttribute("noPhoto", noPhoto);
            model.addAttribute("somePhotos", somePhotos);
            model.addAttribute("enoughForCarousel", enoughForCarousel);
        }

        List<Photo> photos = photosDao.findAll();
        System.out.println(photos);
        model.addAttribute("photos", photos);
        model.addAttribute("albums", albums);
        model.addAttribute("noAlbumsFound", albums.size() == 0);
        return "albums/index";
    }

    @GetMapping("/albums/{id}")
    public String showAnAlbum(@PathVariable long id, Model model) {
        Album album = albumsDao.getOne(id);
        model.addAttribute("album", album);
        model.addAttribute("albumId", id); //this is optional, just to have this attribute in case it needs to be used in the view at some point.
        return "albums/show";
    }

    @GetMapping("/albums/create")
    public String createAlbumForm(Model eventCreateModel){
        eventCreateModel.addAttribute("album", new Album());
        return "albums/create";
    }

    @PostMapping("/albums/create")
    public String createAlbum(@ModelAttribute Album albumToCreate) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //so that the logged-in user is associated with album creation.
        albumToCreate.setUser(currentUser);
        Album createdAlbum = albumsDao.save(albumToCreate);
        return "redirect:/albums/" + createdAlbum.getId();
    }

    @GetMapping("/albums/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        Album albumToEdit = albumsDao.getOne(id);
        model.addAttribute("album", albumToEdit);
        return "albums/edit";
    }

    @PostMapping("/albums/{id}/edit")
    public String editEvent(@ModelAttribute Album albumToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        albumToEdit.setUser(currentUser);
        albumsDao.save(albumToEdit);
        return "redirect:/albums/" + albumToEdit.getId();
    }

    @GetMapping("/albums/{id}/delete")
    public String showDeleteForm(Model model, @PathVariable Long id) {
        Album albumToDelete = albumsDao.getOne(id);
        model.addAttribute("album", albumToDelete);
        return "albums/delete";
    }

    @PostMapping("/albums/{id}/delete")
    public String delete(@PathVariable long id) {
        albumsDao.deleteById(id);
        return "redirect:/albums";
    }

}