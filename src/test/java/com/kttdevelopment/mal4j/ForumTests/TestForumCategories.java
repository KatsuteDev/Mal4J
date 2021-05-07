package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.*;
import com.kttdevelopment.mal4j.forum.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

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

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("categoryProvider")
    public void testCategory(@SuppressWarnings("unused") final String method, final Function<ForumCategory,Object> function){
        Assertions.assertNotNull(function.apply(category));
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> categoryProvider(){
        return new TestProvider.MethodStream<ForumCategory>()
            .add("Title", ForumCategory::getTitle)
            .add("ForumBoards", ForumCategory::getForumBoards)
            .add("ForumBoards[2]", category -> category.getForumBoards()[2])
            .add("ForumBoards#ID", category -> category.getForumBoards()[2].getID())
            .add("ForumBoards#Title", category -> category.getForumBoards()[2].getTitle())
            .add("ForumBoards#Description", category -> category.getForumBoards()[2].getDescription())
            .add("ForumBoards#SubBoards", category -> category.getForumBoards()[2].getSubBoards())
            .add("ForumBoards#SubBoards[2]", category -> category.getForumBoards()[2].getSubBoards()[2])
            .add("ForumBoards#SubBoards#ID", category -> category.getForumBoards()[2].getSubBoards()[2].getTitle())
            .add("ForumBoards#SubBoards#Title", category -> category.getForumBoards()[2].getSubBoards()[2].getTitle())
            .stream();
    }

    @Test
    public void testForumBoardReference(){
        Assertions.assertSame(category, category.getForumBoards()[2].getCategory());
        Assertions.assertSame(category.getForumBoards()[2] , category.getForumBoards()[2].getSubBoards()[2].getBoard());
    }

}