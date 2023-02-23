package com.example.fastcampusmysql.util;

import java.util.List;

public record PageCuorsor<T>(
    CursorRequest nextCursorRequest,
    List<T> Body
){

}