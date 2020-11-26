package com.kttdevelopment.myanimelist.property;

public interface UserListStatus<Status extends Enum<?>> extends ListStatus<Status> {

    long getStartDate();

    long getFinishDate();

    long getUpdatedAt();

}
