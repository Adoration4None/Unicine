package co.edu.uniquindio.unicine.servicios;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServicio {

    private final JavaMailSender javaMailSender;

    public EmailServicio(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean enviarEmail(String asunto, String contenido, String destinatario) {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setSubject(asunto);
            helper.setText(contenido, true);
            helper.setTo(destinatario);
            helper.setFrom("no_reply@unicine.com");

            javaMailSender.send(mensaje);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
