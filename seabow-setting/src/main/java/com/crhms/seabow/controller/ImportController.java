package com.crhms.seabow.controller;

import com.crhms.seabow.service.SubtitleService;
import com.crhms.seabow.utils.EncodingDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private SubtitleService subtitleService;

    @RequestMapping("/run")
    public String run(){
        String path = "C:\\Users\\hkk\\Desktop\\日本語\\初级下册\\课文原文";

        File folder = new File(path);
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            List<String> txts = readTxt(file);
            String name = file.getName().replace(".txt","");
            subtitleService.saveSubtitles(Integer.parseInt(name),txts);
        }

        return "ok";
    }


    public static List<String> readTxt(File file) {
        BufferedReader reader = null;
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream((file)),"windows-31J");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            int line = 1;
            List<String> text = new ArrayList<>();
            while ((tempString = reader.readLine()) != null) {
                text.add(tempString);
                line++;
            }
            reader.close();
            return text;

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                }catch (IOException e1){

                }
            }
        }
        return null;
    }
}
