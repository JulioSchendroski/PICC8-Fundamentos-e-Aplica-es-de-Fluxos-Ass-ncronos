package com.ms.email.Repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ms.email.Model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{
    
}
