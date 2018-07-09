package com.task.lotterydemo.utils;

import com.task.lotterydemo.domain.Line;
import com.task.lotterydemo.domain.Ticket;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketTestUtils {

    public static void assertTicketValuesMatch(Ticket given, Optional<Ticket> expected) {
        Assert.assertEquals(given.getId(), expected.get().getId());
        Assert.assertEquals(given.getLines(), expected.get().getLines());
        Assert.assertEquals(given.getModified(), expected.get().getModified());
    }
    public static Ticket generateTestTicket(List<Line> lineList) {
        Ticket ticket = new Ticket();
        ticket.setLines(lineList);
        return ticket;
    }
    public static List<Line> generateTestLineResult10() {
        Line line = new Line();
        List<Line> lineArrayList = new ArrayList<>();
        lineArrayList.add(line);
        line.setNumber_1(0);
        line.setNumber_2(0);
        line.setNumber_3(2);
        return lineArrayList;
    }
    public static List<Line> generateTestLineResult5() {
        Line line = new Line();
        List<Line> lineArrayList = new ArrayList<>();
        lineArrayList.add(line);
        line.setNumber_1(1);
        line.setNumber_2(1);
        line.setNumber_3(1);
        return lineArrayList;
    }
    public static List<Line> generateTestLineResult1() {
        Line line = new Line();
        List<Line> lineArrayList = new ArrayList<>();
        lineArrayList.add(line);
        line.setNumber_1(1);
        line.setNumber_2(2);
        line.setNumber_3(2);
        return lineArrayList;
    }
    public static List<Line> generateTestLineResult0() {
        Line line = new Line();
        List<Line> lineArrayList = new ArrayList<>();
        lineArrayList.add(line);
        line.setNumber_1(2);
        line.setNumber_2(2);
        line.setNumber_3(0);
        return lineArrayList;
    }
    public static Ticket generateModifiedTicket(Ticket ticket) {
        Ticket modified = new Ticket();
        modified.setLines(generateTestLineResult0());
        modified.setModified(ticket.getModified() + 1);
        return modified;
    }

}
