package com.sudeshkar.SmartWasteManagement.model;

import java.time.LocalDateTime;

import com.sudeshkar.SmartWasteManagement.Enum.AlertType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String message;
	private boolean acknowledged;
	
	private LocalDateTime createdAt;
    private LocalDateTime acknowledgedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertType type;
    
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.acknowledged = false;
    }
}
