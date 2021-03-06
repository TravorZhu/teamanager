package top.travorzhu.teamanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import top.travorzhu.teamanager.storage.StorageService;

@Controller
public class Resourse {

    @Autowired
    private StorageService storageService;

    @GetMapping("/showimg/{filename:.+}")
    @ResponseBody
    public String PicView(@PathVariable String filename) {
        return String.format("<img src=\"/teaimg/%s\" />", filename);
    }

    @GetMapping("/teaimg/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
