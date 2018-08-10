package com.packt.rhuan.ejb;

import com.packt.rhuan.bean.FileBean;
import com.packt.rhuan.utils.FileSystemUtils;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Date;

@Stateless
public class JpgHandler {


    public String handler (FileBean file) throws IOException {


        return FileSystemUtils.save(
                        file.getFile(),
                        "jpg",
                        "jpg_"+ new Date().getTime() + ".jpg" );



    }
}
