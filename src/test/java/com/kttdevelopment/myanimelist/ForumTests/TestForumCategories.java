package com.kttdevelopment.myanimelist.ForumTests;

import com.kttdevelopment.myanimelist.MyAnimeList;
import com.kttdevelopment.myanimelist.TestProvider;
import com.kttdevelopment.myanimelist.forum.*;
import org.junit.jupiter.api.*;

public class TestForumCategories {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static ForumCategory category;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    public static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        category = mal.getForumBoards().get(0);
    }

    @Test
    public void testCategory(){
        Assertions.assertNotNull(category.getTitle());
    }

    @Test
    public void testBoard(){
        final ForumBoard board = category.getForumBoards()[2];
        Assertions.assertNotEquals(-1, board.getID());
        Assertions.assertNotNull(board.getTitle());
        Assertions.assertNotNull(board.getDescription());

        Assertions.assertEquals(category, board.getCategory());
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test // not all forums have subboards
    public void testSubBoard(){
        final ForumSubBoard subBoard = category.getForumBoards()[2].getSubBoards()[0];
        Assertions.assertNotEquals(-1, subBoard.getID());
        Assertions.assertNotNull(subBoard.getTitle());
    }

}
