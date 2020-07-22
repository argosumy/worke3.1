package project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import project.dao.DaoConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerMain {

    private DaoConnection con;

    @Autowired
    public void setCon(DaoConnection con) {
        this.con = con;
    }

    @GetMapping("/start")
    public String headMain(){
        con.connect();
        con.creatTable();
        con.disconnect();
        return "start";
    }


}


