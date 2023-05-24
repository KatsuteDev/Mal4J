/*
 * Copyright (C) 2023 Katsute <https://github.com/Katsute>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package dev.katsute.mal4j;

import dev.katsute.mal4j.Json.JsonObject;
import dev.katsute.mal4j.forum.*;
import dev.katsute.mal4j.forum.property.ForumTopicCreator;
import dev.katsute.mal4j.forum.property.Poll;
import dev.katsute.mal4j.forum.property.PollOption;
import dev.katsute.mal4j.forum.property.PostAuthor;
import dev.katsute.mal4j.user.User;

import java.util.Arrays;
import java.util.Date;

@SuppressWarnings("unused")
abstract class MyAnimeListSchema_Forum extends MyAnimeListSchema {

    static ForumTopicCreator asForumTopicCreator(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new ForumTopicCreator(){

            private final Long id       = schema.getLong("id");
            private final String name   = schema.getString("name");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getName(){
                return name;
            }

            // additional methods

            @Override
            public final User getUser(){
                return mal.getUser(name);
            }

            @Override
            public final String toString(){
                return "ForumTopicCreator{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       '}';
            }

        };
    }

    @SuppressWarnings("SpellCheckingInspection")
    static ForumTopic asForumTopicDetail(final MyAnimeList mal, final JsonObject schema, final Long boardid, final Long subboardid){
        return schema == null ? null : new ForumTopic(){

            private final Long boardID                  = boardid;
            private final Long subBoardID               = subboardid;

            private final Long id                       = schema.getLong("id");
            private final String title                  = schema.getString("title");
            private final Long createdAt                = parseISO8601(schema.getString("created_at"));
            private final ForumTopicCreator createdBy   = asForumTopicCreator(mal, schema.getJsonObject("created_by"));
            private final Integer posts                 = schema.getInt("number_of_posts");
            private final Long lastPostedAt             = parseISO8601(schema.getString("last_post_created_at"));
            private final ForumTopicCreator lastPostedBy
                                                        = asForumTopicCreator(mal, schema.getJsonObject("last_post_created_by"));
            private final Boolean locked                = schema.getBoolean("is_locked");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Date getCreatedAt(){
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                return createdAt;
            }

            @Override
            public final ForumTopicCreator getCreatedBy(){
                return createdBy;
            }

            @Override
            public final Integer getPostsCount(){
                return posts;
            }

            @Override
            public final Date getLastPostCreatedAt(){
                return lastPostedAt == null ? null : new Date(lastPostedAt);
            }

            @Override
            public final Long getLastPostCreatedAtEpochMillis(){
                return lastPostedAt;
            }

            @Override
            public final ForumTopicCreator getLastPostCreatedBy(){
                return lastPostedBy;
            }

            @Override
            public final Boolean isLocked(){
                return locked;
            }

            // additional methods

            @Override
            public final Long getBoardID(){
                return boardID;
            }

            @Override
            public final Long getSubBoardID(){
                return subBoardID;
            }

            @Override
            public final String toString(){
                return "ForumTopic{" +
                       "boardID=" + boardID +
                       ", subBoardID=" + subBoardID +
                       ", id=" + id +
                       ", title='" + title + '\'' +
                       ", createdAt=" + createdAt +
                       ", createdBy=" + createdBy +
                       ", posts=" + posts +
                       ", lastPostedAt=" + lastPostedAt +
                       ", lastPostedBy=" + lastPostedBy +
                       ", locked=" + locked +
                       '}';
            }

        };
    }

    static Poll asPoll(final MyAnimeList mal, final JsonObject schema, final ForumTopicDetail forumTopic){
        return schema == null ? null : new Poll(){

            private final Long id               = schema.getLong("id");
            private final String question       = schema.getString("question");
            private final Boolean isClosed      = schema.getBoolean("closed");
            private final PollOption[] options  = adaptList(schema.getJsonArray("options"), o -> asPollOption(mal, o, this), PollOption.class);

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getQuestion(){
                return question;
            }

            @Override
            public final Boolean isClosed(){
                return isClosed;
            }

            @Override
            public final PollOption[] getOptions(){
                return options != null ? Arrays.copyOf(options, options.length) : null;
            }

            // additional methods

            @Override
            public final ForumTopicDetail getForumTopicDetail(){
                return forumTopic;
            }

            @Override
            public final String toString(){
                return "Poll{" +
                       "id=" + id +
                       ", question='" + question + '\'' +
                       ", isClosed=" + isClosed +
                       ", options=" + Arrays.toString(options) +
                       '}';
            }

        };
    }

    static PollOption asPollOption(final MyAnimeList mal, final JsonObject schema, final Poll poll){
        return schema == null ? null : new PollOption(){

            private final Long id       = schema.getLong("id");
            private final String text   = schema.getString("text");
            private final Integer votes = schema.getInt("votes");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getText(){
                return text;
            }

            @Override
            public final Integer getVotes(){
                return votes;
            }

            // additional methods

            @Override
            public final Poll getPoll(){
                return poll;
            }

            @Override
            public final String toString(){
                return "PollOption{" +
                       "id=" + id +
                       ", text='" + text + '\'' +
                       ", votes=" + votes +
                       '}';
            }

        };
    }

    static PostAuthor asPostAuthor(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new PostAuthor(){

            private final Long id               = schema.getLong("id");
            private final String name           = schema.getString("name");
            @SuppressWarnings("SpellCheckingInspection")
            private final String forumAvatarURL = schema.getString("forum_avator");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getName(){
                return name;
            }

            @Override
            public final String getForumAvatarURL(){
                return forumAvatarURL;
            }

            // additional methods

            @Override
            public final User getUser(){
                return mal.getUser(name);
            }

            @Override
            public final String toString(){
                return "PostAuthor{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", forumAvatarURL='" + forumAvatarURL + '\'' +
                       '}';
            }

        };
    }

    static ForumBoard asForumBoard(final MyAnimeList mal, final JsonObject schema, final ForumCategory forumCategory){
        return schema == null ? null : new ForumBoard(){

            private final Long id                   = schema.getLong("id");
            private final String title              = schema.getString("title");
            private final String description        = schema.getString("description");
            @SuppressWarnings("SpellCheckingInspection")
            private final ForumSubBoard[] subBoards = adaptList(schema.getJsonArray("subboards"), b -> asForumSubBoard(mal, b, this), ForumSubBoard.class);

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final String getDescription(){
                return description;
            }

            @Override
            public final ForumSubBoard[] getSubBoards(){
                return subBoards != null ? Arrays.copyOf(subBoards, subBoards.length) : null;
            }

            // additional methods

            @Override
            public final ForumCategory getCategory(){
                return forumCategory;
            }

            @Override
            public final String toString(){
                return "ForumBoard{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       ", description='" + description + '\'' +
                       ", subBoards=" + Arrays.toString(subBoards) +
                       '}';
            }

        };
    }

    static ForumCategory asForumCategory(final MyAnimeList mal, final JsonObject schema){
        return schema == null ? null : new ForumCategory(){

            private final String title              = schema.getString("title");
            private final ForumBoard[] forumBoards  = adaptList(schema.getJsonArray("boards"), b -> asForumBoard(mal, b, this), ForumBoard.class);

            // API methods

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final ForumBoard[] getForumBoards(){
                return forumBoards != null ? Arrays.copyOf(forumBoards, forumBoards.length) : null;
            }

            // additional methods

            @Override
            public final String toString(){
                return "ForumCategory{" +
                       "title='" + title + '\'' +
                       ", forumBoards=" + Arrays.toString(forumBoards) +
                       '}';
            }

        };
    }

    static ForumSubBoard asForumSubBoard(final MyAnimeList mal, final JsonObject schema, final ForumBoard forumBoard){
        return schema == null ? null : new ForumSubBoard(){

            private final Long id       = schema.getLong("id");
            private final String title  = schema.getString("title");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String getTitle(){
                return title;
            }

            // additional methods

            @Override
            public final ForumBoard getBoard(){
                return forumBoard;
            }

            @Override
            public final String toString(){
                return "ForumSubBoard{" +
                       "id=" + id +
                       ", title='" + title + '\'' +
                       '}';
            }

        };
    }

    @SuppressWarnings("SpellCheckingInspection")
    static ForumTopicDetail asForumTopic(final MyAnimeList mal, final JsonObject schema, final long topicid){
        return schema == null ? null : new ForumTopicDetail(){

            private final long id       = topicid;
            private final String title  = schema.getString("title");
            private final Post[] posts  = adaptList(schema.getJsonArray("posts"), p -> asPost(mal, p, this), Post.class);
            private final Poll poll     = asPoll(mal, schema.getJsonObject("poll"), this);

            // API methods

            @Override
            public final String getTitle(){
                return title;
            }

            @Override
            public final Post[] getPosts(){
                return posts != null ? Arrays.copyOf(posts, posts.length) : null;
            }

            @Override
            public final Poll getPoll(){
                return poll;
            }

            // additional methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final String toString(){
                return "ForumTopicDetail{" +
                       "title='" + title + '\'' +
                       ", posts=" + Arrays.toString(posts) +
                       ", poll=" + poll +
                       '}';
            }

        };
    }

    // make sure matches below
    static Post asPost(final MyAnimeList mal, final JsonObject schema, final ForumTopicDetail forumTopic){
        return schema == null ? null : new Post(){

            private final Long id           = schema.getLong("id");
            private final Integer number    = schema.getInt("number");
            private final Long createdAt    = parseISO8601(schema.getString("created_at"));
            private final PostAuthor author = asPostAuthor(mal, schema.getJsonObject("created_by"));
            private final String body       = schema.getString("body");
            private final String signature  = schema.getString("signature");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final Integer getNumber(){
                return number;
            }

            @Override
            public final Date getCreatedAt(){
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                return createdAt;
            }

            @Override
            public final PostAuthor getAuthor(){
                return author;
            }

            @Override
            public final String getBody(){
                return body;
            }

            @Override
            public final String getSignature(){
                return signature;
            }

            // additional methods

            @Override
            public final ForumTopicDetail getForumTopicDetail(){
                return forumTopic;
            }

            @Override
            public final String toString(){
                return "Post{" +
                       "id=" + id +
                       ", number=" + number +
                       ", createdAt=" + createdAt +
                       ", author=" + author +
                       ", body='" + body + '\'' +
                       ", signature='" + signature + '\'' +
                       '}';
            }

        };
    }

    @SuppressWarnings("SpellCheckingInspection")
    static Post asPost(final MyAnimeList mal, final JsonObject schema, final long ftdid){
        return schema == null ? null : new Post(){

            private final Long id           = schema.getLong("id");
            private final Integer number    = schema.getInt("number");
            private final Long createdAt    = parseISO8601(schema.getString("created_at"));
            private final PostAuthor author = asPostAuthor(mal, schema.getJsonObject("created_by"));
            private final String body       = schema.getString("body");
            private final String signature  = schema.getString("signature");

            // API methods

            @Override
            public final Long getID(){
                return id;
            }

            @Override
            public final Integer getNumber(){
                return number;
            }

            @Override
            public final Date getCreatedAt(){
                return createdAt == null ? null : new Date(createdAt);
            }

            @Override
            public final Long getCreatedAtEpochMillis(){
                return createdAt;
            }

            @Override
            public final PostAuthor getAuthor(){
                return author;
            }

            @Override
            public final String getBody(){
                return body;
            }

            @Override
            public final String getSignature(){
                return signature;
            }

            // additional methods

            @Override
            public final ForumTopicDetail getForumTopicDetail(){
                return mal.getForumTopicDetail(ftdid);
            }

            @Override
            public final String toString(){
                return "Post{" +
                       "id=" + id +
                       ", number=" + number +
                       ", createdAt=" + createdAt +
                       ", author=" + author +
                       ", body='" + body + '\'' +
                       ", signature='" + signature + '\'' +
                       '}';
            }

        };
    }

}