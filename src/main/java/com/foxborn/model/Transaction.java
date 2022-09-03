package com.foxborn.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Transaction {

    private UUID sender;
    private UUID receiver;
    private BigDecimal amount;
    private String message;
    private Date creationDate;

}
