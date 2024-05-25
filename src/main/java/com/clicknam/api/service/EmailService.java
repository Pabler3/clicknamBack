package com.clicknam.api.service;

import com.clicknam.api.model.ReservaModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender sender;

    //Método para enviar un email con un correo de destino, un asunto y el contenido del mensaje
    private boolean sendEmail(String email, String subject, String textMessage) {
        boolean send = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            send = true;
            System.out.println("Mail enviado");
        } catch (MessagingException e) {
            System.err.println("Hubo un error al enviar el mail");
        }
        return send;
    }

    //Método para cargar los datos de la reserva en la plantilla html
    public boolean sendDatosReserva(ReservaModel reservaModel){
        try {
            String subject = "Reserva realizada";
            String contenido = "<p>Hola "+reservaModel.getUsuario().getNombre()+"</p>" +
                    "<p>Los datos de tu reserva son: </p>"+
                    "<p>Reserva en el restaurante: "+reservaModel.getMesa().getRestaurante().getNombre()+"</p>" +
                    "<p>Desde las: "+reservaModel.getHoraInicio()+"</p>" +
                    "<p>Hasta las: "+reservaModel.getHoraFin()+"</p>" +
                    "<p>Disfruta de la experiencia</p>";
            String base64Image = convertToBase64("images/Logo-color-nav.png");
            String message = readHtmlTemplate("templates/emailReservaTemplate.html");
            message = message.replace("{{title}}", "Información de reserva");
            message = message.replace("{{message}}", contenido);
            message = message.replace("{{image}}", base64Image);
            return sendEmail(reservaModel.getUsuario().getEmail(), subject, message);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Método para convertir una imagen a base64
    private String convertToBase64(String imagePath) throws IOException {
        Resource resource = new ClassPathResource(imagePath);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return "data:image/png;base64, " + Base64.getEncoder().encodeToString(bytes);
    }

    //Método para leer la plantilla html y devolver un String que pueda ser interpretado por el email
    private String readHtmlTemplate(String templatePath) throws IOException {
        Resource resource = new ClassPathResource(templatePath);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
