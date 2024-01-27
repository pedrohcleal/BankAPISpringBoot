package com.bankapi.demo.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name= "Clients")
public class ClientModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "CPF")
    private long CPF;

    @Column(name = "Name" )
    private String Name;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "bankCNPJ")
    private long bankCNPJ;

    public UUID getId() {
        return id;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public long getbankCNPJ() {
        return bankCNPJ;
    }

    public void setbankCNPJ(long bankCNPJ) {
        this.bankCNPJ = bankCNPJ;
    }
}
