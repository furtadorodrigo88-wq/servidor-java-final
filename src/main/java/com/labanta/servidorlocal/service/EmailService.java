package com.labanta.servidorlocal.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void enviarEmailBoasVindas(String emailDestino, String nomeUtilizador){
        // Criar um email simples(texto limpo)
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom(emailDestino);
        mensagem.setTo(emailDestino);
        mensagem.setSubject("bem vindo ao Marketplace!");
        mensagem.setText("Olá " + nomeUtilizador + "!\n\n" +
                "A tua conta foi criada com sucesso. Já podes fazer login e explorar os nossos serviços. \n\n" +
                "Com os melhores cumprimentos. \nEquipa do MarketPlace");

        mailSender.send(mensagem);
    }

    public void enviarOrcamentoPorEmail(String emailDestino, String nomeServico, double precoConvertido, String moeda){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDestino);
        message.setSubject("Orçamento do serviço no marketplace");
        String corpo = String.format(
                "Olá!\n\nAqui tens o orçamento solicitado para o serviço:\n\n" +
                        "Serviço: %s\n" +
                        "Preço Final: %.2f %s\n\n" +
                        "Este valor foi calculado com a taxa de câmbio em tempo real.\n" +
                        "Obrigado por usares o nosso Marketplace!",
                nomeServico, precoConvertido, moeda
        );
        message.setText(corpo);
        mailSender.send(message);
    }

    public void enviarAlertaSeguranca(String emailDestino, String cidade, String pais) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailDestino);
        mensagem.setSubject("Aviso de Segurança");
        mensagem.setText("Detetámos uma nova atividade na tua conta do Marketplace a partir de "
                + cidade + ", " + pais + ". Se não foste tu, altera a tua password imediatamente!");
        mailSender.send(mensagem);
    }
}