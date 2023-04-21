package dev.katsute.mal4j;

import dev.katsute.mal4j.anime.property.time.Time;
import dev.katsute.mal4j.property.NullableDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class TestDateTime {

    @Test
    final void testDate(){
        assertNotNull(MyAnimeListSchema.parseDate("2020-03-14"));
        assertNotNull(MyAnimeListSchema.parseDate("2020-03"));
        assertNotNull(MyAnimeListSchema.parseDate("2020"));
        assertNull(MyAnimeListSchema.parseDate("-"));
        assertNull(MyAnimeListSchema.parseDate(""));
        assertNull(MyAnimeListSchema.parseDate(null));
    }

    @Test
    final void testNullableDateYMD(){
        final NullableDate date = MyAnimeListSchema.parseNullableDate("2020-03-14");
        assertEquals(2020, date.getYear());
        assertEquals(3, date.getMonth());
        assertEquals(14, date.getDay());
        assertNotNull(date.getMillis());
        assertNotNull(date.getDate());
    }

    @Test
    final void testNullableDateYM(){
        final NullableDate date = MyAnimeListSchema.parseNullableDate("2020-03");
        assertEquals(2020, date.getYear());
        assertEquals(3, date.getMonth());
        assertNull(date.getDay());
        assertNotNull(date.getMillis());
        assertNotNull(date.getDate());
    }

    @Test
    final void testNullableDateY(){
        final NullableDate date = MyAnimeListSchema.parseNullableDate("2020");
        assertEquals(2020, date.getYear());
        assertNull(date.getMonth());
        assertNull(date.getDay());
        assertNotNull(date.getMillis());
        assertNotNull(date.getDate());
    }

    @Test
    final void testNullableDateNull(){
        assertNull(MyAnimeListSchema.parseDate("-"));
        assertNull(MyAnimeListSchema.parseDate(""));
        assertNull(MyAnimeListSchema.parseDate(null));
    }

    @Test
    final void testYMD(){
        assertEquals("2020-03-14", MyAnimeListSchema.asYMD(1584158400000L));
        assertNull(MyAnimeListSchema.asYMD(null));
    }

    @Test
    final void testISO(){
        assertEquals(1584144000000L, MyAnimeListSchema.parseISO8601("2020-03-14T00:00:00+00:00"));
        assertNull(MyAnimeListSchema.parseISO8601(null));
    }

    @Test
    final void testTimeAM(){
        final Time time = MyAnimeListSchema.asTime("01:23");
        assertEquals(1, time.get12Hour());
        assertEquals(1, time.getHour());
        assertEquals(23, time.getMinute());
        assertTrue(time.isAM());
        assertFalse(time.isPM());
    }

    @Test
    final void testTimePM(){
        final Time time = MyAnimeListSchema.asTime("14:56");
        assertEquals(2, time.get12Hour());
        assertEquals(14, time.getHour());
        assertEquals(56, time.getMinute());
        assertFalse(time.isAM());
        assertTrue(time.isPM());
    }

}