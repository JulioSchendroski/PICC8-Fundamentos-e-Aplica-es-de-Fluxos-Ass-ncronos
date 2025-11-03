package com.ms.email.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ms.email.Enum.StatusEmail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable{
    private static final long serialVersion = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID emailId;

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;
    
    private LocalDateTime sendDataTime;
    private StatusEmail statusEmail;
}
