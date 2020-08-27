package com.crhms.seabow.service;

import com.crhms.seabow.Entity.Subtitle;
import com.crhms.seabow.model.User;
import com.crhms.seabow.repository.SubtitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SubtitleService {

    @Autowired
    private SubtitleRepository subtitleRepository;

    public List<Subtitle> getSubtitles() {

        List<Subtitle> all = subtitleRepository.findAll();

        return  all;
    }

    public boolean saveSubtitles(int lessionId, List<String> sentences) {

        sentences.forEach(m->{
            if (!StringUtils.isEmpty(m)) {
                Subtitle subtitle = new Subtitle();
                subtitle.setLessionId(lessionId);
                subtitle.setContent(m);
                subtitleRepository.save(subtitle);
            }
        });
        return true;
    }
}
