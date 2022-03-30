package no.ntnu.idatt2105.gr13.qs3backend.model;

import java.util.List;


public class Queue {
    private int queueId;
    private boolean active;
    private String description; //Message from TA
    private List<StudentQueueInfo> studsInQueue;
}
