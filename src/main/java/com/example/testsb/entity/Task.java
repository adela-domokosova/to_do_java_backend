package com.example.testsb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotNull(message = "title required")
        private String title;
        private boolean done;

        @CreatedDate
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Column(updatable = false)
        private LocalDateTime createdAt;

        @LastModifiedDate
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Column(updatable = true)
        private LocalDateTime updatedAt;

        @NotNull
        private Long userId;

    }
