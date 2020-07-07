package controller;

import dao.DaoConnection;
import dao.DataSourse;
import dao.OracleDaoConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerMain {
    @Autowired
    DaoConnection con;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String start(){
        con.connect();
        con.creatTable();
        con.disconnect();
        System.out.println("START");
        return "start";
    }


}

