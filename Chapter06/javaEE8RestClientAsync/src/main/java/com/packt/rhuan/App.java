package com.packt.rhuan;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.concurrent.CompletionStage;


public class App
{
    private static final String URL = "http://localhost:8080/javaEE8ExampleAsyncRestService/resources/upload";
    private static final String FILE_PATH = "/home/rhuan/rhuan/papers/palestras/alta-performance-hibernate.pdf";

    public static void main( String[] args ) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL);
        try {
            CompletionStage<String> csf = target.request()
                    .rx()
                    .post(Entity.entity(new FileInputStream(new File(FILE_PATH)),"application/pdf"),String.class);

            csf.whenCompleteAsync((path, err)->{

                if( Objects.isNull( err ) )
                    System.out.println("File saved on: " + path);
                else
                    err.printStackTrace();

            });



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
