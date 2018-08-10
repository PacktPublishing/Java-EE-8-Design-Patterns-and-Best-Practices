package com.packt.rhuan.resources;


import com.packt.rhuan.bean.FileBean;
import com.packt.rhuan.ejb.JpgHandler;
import com.packt.rhuan.ejb.PdfHandler;
import com.packt.rhuan.ejb.ZipHandler;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Path("upload")
public class FileUploadResource {

    @Resource
    private ManagedExecutorService executor;

    @Consumes("application/pdf")
    @POST
    public CompletionStage<String> uploadPdf(File file) {

        BeanManager beanManager = getBeanManager();

        CompletableFuture<String> completionStage = new CompletableFuture<>();
        executor.execute(() -> {
            FileBean fileBean = new FileBean(file, "pdf");

            Bean<PdfHandler> bean = (Bean) beanManager.getBeans(PdfHandler.class).iterator().next();
            CreationalContext cCtx = beanManager.createCreationalContext(bean);
            PdfHandler pdfHandler = (PdfHandler) beanManager.getReference(bean, PdfHandler.class, cCtx);

            try {

                completionStage.complete(pdfHandler.handler( fileBean ));
            } catch (IOException e) {
                e.printStackTrace();
                completionStage.completeExceptionally(e);
            }

        });


        return completionStage;

    }

    private BeanManager getBeanManager(){
        try {
            // manual JNDI lookupCDI for the CDI bean manager (JSR 299)
           return (BeanManager) new InitialContext().lookup(
                    "java:comp/BeanManager");

        } catch (NamingException ex) {
            throw new IllegalStateException(
                    "cannot perform JNDI lookup for CDI BeanManager");
        }
    }


    @Consumes("image/jpeg")
    @POST
    public CompletionStage<String> uploadJpg(File file) throws IOException {

        BeanManager beanManager = getBeanManager();

        CompletableFuture<String> completionStage = new CompletableFuture<>();
        executor.execute(() -> {
            FileBean fileBean = new FileBean(file, "jpg");

            Bean<JpgHandler> bean = (Bean) beanManager.getBeans(JpgHandler.class).iterator().next();
            CreationalContext cCtx = beanManager.createCreationalContext(bean);
            JpgHandler jpgHandler = (JpgHandler) beanManager.getReference(bean, JpgHandler.class, cCtx);

            try {

                completionStage.complete(jpgHandler.handler( fileBean ));
            } catch (IOException e) {
                e.printStackTrace();
                completionStage.completeExceptionally(e);
            }

        });

        return completionStage;

    }

    @Consumes("application/zip")
    @POST
    public CompletionStage<String> uploadZip( File file) throws IOException {

        BeanManager beanManager = getBeanManager();

        CompletableFuture<String> completionStage = new CompletableFuture<>();
        executor.execute(() -> {
            FileBean fileBean = new FileBean(file, "zip");

            Bean<ZipHandler> bean = (Bean) beanManager.getBeans(ZipHandler.class).iterator().next();
            CreationalContext cCtx = beanManager.createCreationalContext(bean);
            ZipHandler zipHandler = (ZipHandler) beanManager.getReference(bean, ZipHandler.class, cCtx);

            try {

                completionStage.complete(zipHandler.handler( fileBean ));
            } catch (IOException e) {
                e.printStackTrace();
                completionStage.completeExceptionally(e);
            }

        });

        return completionStage;

    }
}
