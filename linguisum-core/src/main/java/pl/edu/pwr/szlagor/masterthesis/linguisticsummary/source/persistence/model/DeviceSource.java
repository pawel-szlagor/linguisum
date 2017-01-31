package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.persistence.model;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.model.enums.MediaType;

import javax.persistence.*;

/**
 * Created by Pawel on 2017-01-22.
 */
@Table(name = "DEVICE")
@Entity
public class DeviceSource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_DEVICE")
    private Long id;

    private String name;
    private MediaType mediaType;
    private float mediaUsage;

}
