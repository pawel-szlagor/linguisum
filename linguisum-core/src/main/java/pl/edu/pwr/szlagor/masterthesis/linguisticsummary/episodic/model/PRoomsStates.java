package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.Embeddable;

import org.hibernate.search.annotations.IndexedEmbedded;
import org.mongodb.morphia.annotations.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Pawel on 2017-04-07.
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
public class PRoomsStates {

    @IndexedEmbedded
    private RoomState roomState1;
    @IndexedEmbedded
    private RoomState roomState2;
    @IndexedEmbedded
    private RoomState roomState3;
    @IndexedEmbedded
    private RoomState roomState4;
    @IndexedEmbedded
    private RoomState roomState5;
    @IndexedEmbedded
    private RoomState roomState6;
    @IndexedEmbedded
    private RoomState roomState7;
    @IndexedEmbedded
    private RoomState roomState8;
    @IndexedEmbedded
    private RoomState roomState9;
    @IndexedEmbedded
    private RoomState roomState10;

}
