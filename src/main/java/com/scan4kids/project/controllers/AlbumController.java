package com.scan4kids.project.controllers;


import com.scan4kids.project.daos.AlbumsRepository;
import com.scan4kids.project.models.Album;
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

    public AlbumController(AlbumsRepository albumsRepository) {
        this.albumsDao = albumsRepository;
    }

    @GetMapping("/albums")
    public String albumIndex(Model model) {
        List<Album> albums = albumsDao.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("noAlbumsFound", albums.size() == 0);
        return "albums/index";
    }

    @GetMapping("/create")
    public String createAlbumForm(Model eventCreateModel){
        eventCreateModel.addAttribute("album", new Album());
        return "albums/create";
    }

    @PostMapping("/create")
    public String createAlbum(@ModelAttribute Album albumToCreate) {
        albumsDao.save(albumToCreate);
        return "redirect:/events";
    }

    @GetMapping("/albums/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        Album albumToEdit = albumsDao.getOne(id);
        model.addAttribute("album", albumToEdit);
        return "albums/edit";
    }

    @PostMapping("/albums/{id}/edit")
    public String editEvent(@ModelAttribute Album albumToEdit) {
        albumsDao.save(albumToEdit);
        return "redirect: /albums";
    }

    @PostMapping("/albums/{id}/delete")
    public String delete(@PathVariable long id) {
        albumsDao.deleteById(id);
        return "redirect: /albums";
    }

}
