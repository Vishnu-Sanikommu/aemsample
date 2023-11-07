package com.aemsample.core.helpers;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultifieldHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(MultifieldHelper.class);
	
	private String bookName;
	private Date publishDate;
	private String bookSubject;
	private int copies;
	private List<NestedHelper> bookEditions;
	
	public MultifieldHelper(Resource resource) {
		
		try {
			if(StringUtils.isNotBlank(resource.getValueMap().get("bookname", String.class))) {
				this.bookName = resource.getValueMap().get("bookname", String.class);
			}
			
			if(resource.getValueMap().get("publishdate",Date.class)!=null) {
	            this.publishDate=resource.getValueMap().get("publishdate",Date.class);
	        }
			
			if(StringUtils.isNotBlank(resource.getValueMap().get("booksubject", String.class))) {
				this.bookSubject = resource.getValueMap().get("booksubject", String.class);
			}
			
			if(resource.getValueMap().get("copies", Integer.class)!=null) {
				this.copies = resource.getValueMap().get("copies",Integer.class);
			}
			
		} catch (Exception e) {
            LOG.info("\n BEAN ERROR : {}",e.getMessage());
		}
		
	}
	public String getBookName() {
		return bookName;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public String getBookSubject() {
		return bookSubject;
	}
	public int getCopies() {
		return copies;
	}
	
	public List<NestedHelper> getBookEditions() {
		return bookEditions;
	}
	public void setBookEditions(List<NestedHelper> bookEditions) {
		this.bookEditions = bookEditions;
	}
	

}
