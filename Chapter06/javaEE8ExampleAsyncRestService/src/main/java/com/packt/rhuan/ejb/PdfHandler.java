package com.packt.rhuan.ejb;

import com.packt.rhuan.bean.FileBean;
import com.packt.rhuan.utils.FileSystemUtils;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Date;

@Stateless
public class PdfHandler {

    public String handler (FileBean file) throws IOException {

        return FileSystemUtils.save(
                file.getFile(),
                "pdf",
                "pdf_"+ new Date().getTime() + ".pdf" );


    }
}
