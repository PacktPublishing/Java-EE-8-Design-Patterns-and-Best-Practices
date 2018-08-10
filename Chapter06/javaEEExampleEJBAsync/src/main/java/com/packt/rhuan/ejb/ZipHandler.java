package com.packt.rhuan.ejb;

import com.packt.rhuan.bean.FileBean;
import com.packt.rhuan.utils.FileSystemUtils;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Future;

@Stateless
public class ZipHandler {

    @Asynchronous
    public Future<String> handler (FileBean file) throws IOException {


        return new AsyncResult(
                FileSystemUtils.save(
                        file.getFile(),
                        "zip",
                        "zip_"+ new Date().getTime() + ".zip" ));



    }
}
