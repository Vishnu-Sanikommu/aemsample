package com.aemsample.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aemsample.core.models.Author;
import com.google.common.collect.ImmutableList;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AuthorImplTest {

//    To get the aem related data from server, We need the below object
    private final AemContext aemContext = new AemContext();
    
//    This method executes before every other method executes
    @BeforeEach   
    void setUp() throws Exception {
        aemContext.addModelsForClasses(AuthorImpl.class);
        aemContext.load().json("/com/aemsample/core/models/impl/author.json", "/content");
    }

    @Test
    void testGetFirstName() {
        final String expected = "VISHNU VARDHAN REDDY";
        aemContext.currentResource("/content/author");
        Author author = aemContext.request().adaptTo(Author.class);

        String actual = author.getFirstName();

        assertEquals(expected, actual);
    }

   @Test
   void testGetLastName() {
    final String expected = "SANIKOMMU";
    aemContext.currentResource("/content/author");
    Author author = aemContext.request().adaptTo(Author.class);

    String actual = author.getLastName();

    assertEquals(expected, actual);
   }
   
   @Test
   void testGetBooks() {
       List<String> expected = new ImmutableList.Builder<String>()
               .add("AEM")
               .add("SFCC")
               .build();
       aemContext.currentResource("/content/author");
       Author author = aemContext.request().adaptTo(Author.class);
       List<String> actual = author.getBooks();
       assertEquals(actual, expected);
   }

//    @Test
//    void testGetText() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetName() {
//        fail("Not yet implemented");
//    }
//

//
//    @Test
//    void testGetPageTitle() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetLastModifiedBy() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetHomePage() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testGetRequestAttribute() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testAuthorName() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testInit() {
//        fail("Not yet implemented");
//    }

}
