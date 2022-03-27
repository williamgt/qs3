package no.ntnu.idatt2105.gr13.qs3backend.model;

import no.ntnu.idatt2105.gr13.qs3backend.model.location.Location;
import no.ntnu.idatt2105.gr13.qs3backend.model.user.StudentUser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudentInQueue {
    int positionInQueue;
    StudentUser user;
    Location location;
    ArrayList<Task> tasks;
    Need helpOrValidate;
    LocalDateTime timeRegisteredInQueue;
}
