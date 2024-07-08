package com.Api.HealthPlus.Controller;

import com.Api.HealthPlus.Payload.EmailRequest;
import com.Api.HealthPlus.Payload.Otp;
import com.Api.HealthPlus.Security.Email.EmailService;
import com.Api.HealthPlus.Service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ServeController {

    @Autowired
    private FileService fileService;
    @Autowired
    private EmailService emailService;

    @Value("${project.image}")
    private String path;

    //method to serve image
    @GetMapping(value = "/serve/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void serveImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    )throws IOException {
        InputStream resource = fileService.getResource(path,imageName);
        StreamUtils.copy(resource,response.getOutputStream());
    }


//    @PostMapping("/sendEmail")
//    public ResponseEntity<?> sendEmail(
//            @RequestBody EmailRequest emailRequest
//            ){
//        emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
//        return ResponseEntity.ok("done");
//    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/sendBookingEmail")
    public ResponseEntity<?> DoctorBookingMail(
            @RequestBody EmailRequest emailRequest
            ){
        emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTo());
        return ResponseEntity.ok(null);
    }

    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtp(
            @RequestBody Otp otp
            ){
        Random random = new Random();
        int userOtp = random.nextInt(1001,9999);
        String message = "Please use the following One Time Password (OTP) to reset your login password : " + String.valueOf(userOtp);
        emailService.sendEmail("OTP Verification",message, otp.getEmail());
        return ResponseEntity.ok(String.valueOf(userOtp));
    }
}
