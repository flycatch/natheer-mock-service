package com.flycatch.natheer.mock.service.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "natheer_resp")
public class PrimaryIdNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nr_id")
    private int natheerId;

    @Column(name = "n_id")
    private int notificationId;

    @Column(name = "n_code")
    private int notificationCode;

    @Column(name = "citizen_id")
    private long citizenId;
    private String parameter;
}
