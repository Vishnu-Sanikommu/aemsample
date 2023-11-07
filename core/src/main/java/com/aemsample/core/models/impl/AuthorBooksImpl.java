package com.aemsample.core.models.impl;


import com.aemsample.core.helpers.MultifieldHelper;
import com.aemsample.core.helpers.NestedHelper;
import com.aemsample.core.models.AuthorBooks;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.io.Console;
import java.util.*;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = AuthorBooks.class,
        resourceType = AuthorBooksImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name = "jackson", extensions = "json")

public class AuthorBooksImpl implements AuthorBooks{
    private static final Logger LOG = LoggerFactory.getLogger(AuthorBooksImpl.class);
    static final String RESOURCE_TYPE = "aemsample/components/content/authorbooks";

    @Inject
    Resource resource;

    @ValueMapValue
    private List<String> books;


    @Override
    public List<String> getBooks() {
        if (books != null) {
            return new ArrayList<String>(books);
        } else {
            return Collections.emptyList();
        }
    }
    
    @Override
    public List<Map<String, String>> getBookDetailsWithMap() {
        List<Map<String, String>> bookDetailsMap=new ArrayList<>();

        try {
            Resource bookDetail=resource.getChild("bookdetailswithmap");

            if(bookDetail!=null){
                for (Resource book : bookDetail.getChildren()) {
                    Map<String,String> bookMap=new HashMap<>();
                    
                    bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
                    bookMap.put("booksubject",book.getValueMap().get("booksubject",String.class));
                    bookMap.put("publishyear",book.getValueMap().get("publishyear",String.class));
                    bookDetailsMap.add(bookMap);
                }

            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
        }
        return bookDetailsMap;
        
    }

   @PostConstruct
   public void getInit() {
       LOG.info("Hello============ Init ");
   }

	@Override
	public List<MultifieldHelper> getBookDetailsWithBean() {
		List<MultifieldHelper> bookdetailsbean = new ArrayList<>();
		try {
			Resource bookdetailswithbean = resource.getChild("bookdetailswithbean");
			for(Resource bookBean: bookdetailswithbean.getChildren()) {
				
				LOG.info("\n PATH Bean {} ",bookBean.getPath());
                LOG.info("\n BEAN PRO {} ",bookBean.getValueMap().get("bookname",String.class));
                LOG.info("\n BEAN PRO {} ",bookBean.getValueMap().get("publishdate",Date.class));
                LOG.info("\n BEAN PRO {} ",bookBean.getValueMap().get("copies",Integer.class));

				
				bookdetailsbean.add(new MultifieldHelper(bookBean));
			}
		} catch (Exception e) {
            LOG.info("\n ERROR while getting Book Details With Bean {} ",e.getMessage());
		}
		return bookdetailsbean;
	}

	@Override
	public List<MultifieldHelper> getBookDetailsWithNestedField() {
		List<MultifieldHelper> bookDetailsNested = new ArrayList<>();
		try {
			Resource bookDetailNested = resource.getChild("bookdetailswithnested");
			if (bookDetailNested != null) {
				for(Resource bookNested : bookDetailNested.getChildren()) {
					MultifieldHelper multifieldHelper = new MultifieldHelper(bookNested);
					LOG.info("\n PATH Nested {} ",bookNested.getPath());
	                LOG.info("\n NESTED PRO {} ",bookNested.getValueMap().get("bookname",String.class));

					if(bookNested.hasChildren()) {
						List<NestedHelper> nestedHelpersList = new ArrayList<>();
						Resource nestedResource = bookNested.getChild("bookeditions");
						if(nestedResource !=null) {
							for(Resource nested : nestedResource.getChildren()) {
								nestedHelpersList.add(new NestedHelper(nested));
							}
						}
						multifieldHelper.setBookEditions(nestedHelpersList);
					}
			        LOG.info("\n SIZE Multifield {} ",bookDetailsNested.size());
					bookDetailsNested.add(multifieldHelper);					
				}
				
			}
		} catch (Exception e) {
            LOG.info("\n ERROR while getting Book Details With Nasted Multifield {} ",e.getMessage());
		}
		return bookDetailsNested;
	}

}
