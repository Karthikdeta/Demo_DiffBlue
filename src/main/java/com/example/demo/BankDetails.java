package com.example.demo;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(BankDetails.BankId.class)
@Entity
@Builder
public class BankDetails {

    @Id
    private int bankId;

    @Id
    private String bankName;

    private String country;

    private String state;

    private long noOfUsers;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BankId implements Serializable {

        private static final long serialVersionUID = 1L;

        private int bankId;
        private String bankName;
    }

}
