package com.bankapi.demo.models;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name= "Bank")
public class BankModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "CNPJ")
    private long CNPJ;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Balance")
    private Double Balance = 0.0;

    @Column(name = "tax")
    private Double tax;

    public UUID getId() {
        return id;
    }


    public Double getTax() {
        return this.tax;
    }

    public Double getTaxDeposit() {
        return this.tax;
    }
    public Double getTaxWithdraw() {
        return this.tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public long getCNPJ() {
        return this.CNPJ;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getBalance() {
        return this.Balance;
    }

    public void setBalance(Double balance) {
        this.Balance = balance;
    }
}
