package com.kttdevelopment.mal4j.ForumTests;

import com.kttdevelopment.mal4j.MyAnimeList;
import com.kttdevelopment.mal4j.TestProvider;
import com.kttdevelopment.mal4j.forum.ForumCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static dev.katsute.jcore.Workflow.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestForumCategories {

    @SuppressWarnings("FieldCanBeLocal")
    private static MyAnimeList mal;
    private static ForumCategory category;

    @SuppressWarnings("ConstantConditions")
    @BeforeAll
    static void beforeAll(){
        mal = TestProvider.getMyAnimeList();
        category = mal.getForumBoards().get(0);
    }

    @ParameterizedTest(name="[{index}] {0}")
    @MethodSource("categoryProvider")
    final void testCategory(@SuppressWarnings("unused") final String method, final Function<ForumCategory,Object> function){
        annotateTest(() -> assertNotNull(function.apply(category), "Expected ForumCategory#" + method + " to not be null"));
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
    final void testForumCategoryReference(){
        annotateTest(() -> assertSame(category, category.getForumBoards()[2].getCategory()));
    }

    @Test
    final void testForumBoardReference(){
        annotateTest(() -> assertSame(category.getForumBoards()[2] , category.getForumBoards()[2].getSubBoards()[2].getBoard()));
    }

}