package tk.xmfaly.zhihu_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.xmfaly.zhihu_server.dto.Response;
import tk.xmfaly.zhihu_server.entity.Exception;
import tk.xmfaly.zhihu_server.repository.ExceptionRepository;

@RestController
public class ExceptionController {

    @Autowired
    private ExceptionRepository exceptionRepository;


    @PostMapping("/exception")
    public Response ex(String text){
        Exception exception = new Exception(text);
        exceptionRepository.save(exception);
        return new Response(0,"");
    }

    @GetMapping("/exception")
    public Object exs(){
        return exceptionRepository.findAll();
    }
}
