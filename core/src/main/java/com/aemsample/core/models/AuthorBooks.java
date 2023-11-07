package com.aemsample.core.models;

import java.util.List;
import java.util.Map;

import com.aemsample.core.helpers.MultifieldHelper;

public interface AuthorBooks {

    List<String> getBooks();
    List<Map<String, String>> getBookDetailsWithMap();
    List<MultifieldHelper> getBookDetailsWithBean();
    List<MultifieldHelper> getBookDetailsWithNestedField();

}
