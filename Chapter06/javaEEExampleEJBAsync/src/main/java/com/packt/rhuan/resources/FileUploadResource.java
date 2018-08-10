package com.packt.rhuan.resources;


import com.packt.rhuan.bean.FileBean;
import com.packt.rhuan.ejb.JpgHandler;
import com.packt.rhuan.ejb.PdfHandler;
import com.packt.rhuan.ejb.ZipHandler;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;


@Path("upload")
public class FileUploadResource {


    @Inject
    private PdfHandler pdfHandler;

    @Inject
    private JpgHandler jpgHandler;

    @Inject
    private ZipHandler zipHandler;

    @Consumes("application/pdf")
    @POST
    public Response uploadPdf(File file) throws IOException {

        FileBean fileBean = new FileBean(file, "pdf");

        pdfHandler.handler( fileBean );

        return Response.ok().build();

    }


    @Consumes("image/jpeg")
    @POST
    public Response uploadJpg(File file) throws IOException {

        FileBean fileBean = new FileBean(file, "jpg");

        jpgHandler.handler( fileBean );

        return Response.ok().build();
    }

    @Consumes("application/zip")
    @POST
    public Response uploadZip( File file) throws IOException {

        FileBean fileBean = new FileBean( file, "zip" );

        zipHandler.handler( fileBean );

        return Response.ok().build();

    }
}
