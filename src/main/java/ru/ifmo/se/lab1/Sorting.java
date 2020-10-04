package ru.ifmo.se.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorting {
    private final List<TracePoint> tracePoints;

    public Sorting() {
        this.tracePoints = new ArrayList<>();
    }

    public void bubbleSort(int[] nums) {
        for (var i = 0; i < nums.length - 1; i++) {
            tracePoints.add(TracePoint.INIT);
            for (var j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    var t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                    tracePoints.add(TracePoint.SWAPED);
                } else {
                    tracePoints.add(TracePoint.CHANGED);
                }
            }
            tracePoints.add(TracePoint.SORTED);
        }
    }

    public List<TracePoint> getTracePoints() {
        return Collections.unmodifiableList(tracePoints);
    }

    public void clearTracePoints() {
        tracePoints.clear();
    }
}
