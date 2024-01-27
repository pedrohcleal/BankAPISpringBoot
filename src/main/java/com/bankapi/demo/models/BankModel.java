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
    private Double Balance;

    private Double taxDeposit;
    private Double taxWithdraw;

    public UUID getId() {
        return id;
    }

    public long getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        this.Balance = balance;
    }

    public Double getTaxDeposit() {
        return taxDeposit;
    }

    public void setTaxDeposit(Double taxDeposit) {
        this.taxDeposit = taxDeposit;
    }

    public Double getTaxWithdraw() {
        return taxWithdraw;
    }

    public void setTaxWithDraw(Double taxWithdraw) {
        this.taxWithdraw = taxWithdraw;
    }
}
