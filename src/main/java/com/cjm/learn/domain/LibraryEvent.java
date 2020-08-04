package com.cjm.learn.domain;

public class LibraryEvent {
	
	
	private Integer libraryEventId;
	private LibraryEventType libraryEventType;
	private Book book;
	
	
	public LibraryEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

		
	public LibraryEvent(Integer libraryEventId, LibraryEventType libraryEventType, Book book) {
		super();
		this.libraryEventId = libraryEventId;
		this.libraryEventType = libraryEventType;
		this.book = book;
	}


	public Integer getLibraryEventId() {
		return libraryEventId;
	}
	public void setLibraryEventId(Integer libraryEventId) {
		this.libraryEventId = libraryEventId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryEventType getLibraryEventType() {
		return libraryEventType;
	}

	public void setLibraryEventType(LibraryEventType libraryEventType) {
		this.libraryEventType = libraryEventType;
	}
	
	

}
