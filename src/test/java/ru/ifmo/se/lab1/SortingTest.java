package ru.ifmo.se.lab1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortingTest {
    private final Sorting sorting;

    public SortingTest() {
        this.sorting = new Sorting();
    }

    @After
    public void clearTracePoints() {
        sorting.clearTracePoints();
    }


    @Test
    public void testBubbleSortEmptyArray() {
        var nums = new int[]{};
        sorting.bubbleSort(nums);
        Assert.assertArrayEquals(new int[]{}, nums);
        Assert.assertTrue(sorting.getTracePoints().isEmpty());
    }

    @Test
    public void testBubbleSortOneElementArray() {
        var nums = new int[]{42};
        sorting.bubbleSort(nums);
        Assert.assertArrayEquals(new int[]{42}, nums);
        Assert.assertTrue(sorting.getTracePoints().isEmpty());
    }

    @Test
    public void testBubbleSortSortedArray() {
        var nums = new int[]{1, 2, 3};
        sorting.bubbleSort(nums);
        Assert.assertArrayEquals(new int[]{1, 2, 3}, nums);
        assertTracePoints(TracePoint.INIT, TracePoint.CHANGED, TracePoint.CHANGED,
                TracePoint.SORTED, TracePoint.INIT, TracePoint.CHANGED, TracePoint.SORTED);
    }

    @Test
    public void testBubbleSortUnsortedArray() {
        var nums = new int[]{5, 0, -3, 7, 3, -1};
        sorting.bubbleSort(nums);
        Assert.assertArrayEquals(new int[]{-3, -1, 0, 3, 5, 7}, nums);
        assertTracePoints(
                // 5 -> 5 -> 5 -> 7 -> 7 -> 7
                TracePoint.INIT, TracePoint.SWAPED, TracePoint.SWAPED, TracePoint.CHANGED,
                TracePoint.SWAPED, TracePoint.SWAPED, TracePoint.SORTED,
                // 0 -> 0 -> 5 -> 5 -> 5
                TracePoint.INIT, TracePoint.SWAPED, TracePoint.CHANGED, TracePoint.SWAPED,
                TracePoint.SWAPED, TracePoint.SORTED,
                // -3 -> 0 -> 3 -> 3
                TracePoint.INIT, TracePoint.CHANGED, TracePoint.CHANGED, TracePoint.SWAPED,
                TracePoint.SORTED,
                // -3 -> 0 -> 0
                TracePoint.INIT, TracePoint.CHANGED, TracePoint.SWAPED, TracePoint.SORTED,
                // -3 -> -1
                TracePoint.INIT, TracePoint.CHANGED, TracePoint.SORTED
        );
    }


    private void assertTracePoints(TracePoint... expected) {
        Assert.assertEquals(Arrays.asList(expected), sorting.getTracePoints());
    }
}
