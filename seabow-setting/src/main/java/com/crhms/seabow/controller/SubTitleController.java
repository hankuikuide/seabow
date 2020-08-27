package com.crhms.seabow.controller;

import com.crhms.seabow.Entity.Subtitle;
import com.crhms.seabow.service.SubtitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subtitle")
public class SubTitleController {

    @Autowired
    private SubtitleService subtitleService;

    @GetMapping("/getSubtitle")
    public List<Subtitle> getSubtitle(String lessionId){

        return subtitleService.getSubtitles();

        //return subtitles;
    }
}
