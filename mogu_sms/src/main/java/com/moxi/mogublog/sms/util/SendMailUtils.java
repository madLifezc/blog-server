package com.moxi.mogublog.sms.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * 邮件Util
 *
 * @author xzx19950624@qq.com
 * @date 2018年10月20日下午3:18:25
 */
@Slf4j
@Component
public class SendMailUtils {

    @Value(value = "${spring.mail.username}")
    public String SENDER;

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     *
     * @param receiver
     * @param text
     */
    public void sendEmail(String receiver, String text) throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //multipart:true
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("MadLife博客");

        helper.setText(text, true);
        //邮件接收人
        helper.setTo(receiver);

        //邮件发送者
        helper.setFrom(SENDER);

        mailSender.send(mimeMessage);

        log.info("邮件发送成功");
        /*添加邮件附件
        String path = ""; //文件路径
        String fileName = ""; //文件名
        helper.addAttachment(fileName, new File(path));
        */
    }
} 